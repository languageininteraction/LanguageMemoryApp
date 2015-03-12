/*
 * Copyright (C) 2014 Language In Interaction
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package nl.ru.languageininteraction.language.client.registration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.shared.DateTimeFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.ru.languageininteraction.language.client.Version;
import nl.ru.languageininteraction.language.client.ServiceLocations;
import nl.ru.languageininteraction.language.client.model.HighScoreData;
import nl.ru.languageininteraction.language.client.model.MetadataField;
import nl.ru.languageininteraction.language.client.model.UserResults;
import nl.ru.languageininteraction.language.client.service.LocalStorage;
import nl.ru.languageininteraction.language.client.service.MetadataFieldProvider;
import nl.ru.languageininteraction.language.client.service.ResultsSerialiser;

/**
 * @since Oct 29, 2014 11:18:31 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class HighScoreService {

    private static final Logger logger = Logger.getLogger(HighScoreService.class.getName());
    final private ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);
    final MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();
    private final Version version = GWT.create(Version.class);

    public void submitScores(final boolean isShareData,UserResults userResults, HighScoreListener highScoreListener, final String reportDateFormat) {
        final String highScoresUrl = serviceLocations.highScoresUrl();
        final RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, highScoresUrl);
        builder.setHeader("Content-type", "application/x-www-form-urlencoded");
        StringBuilder stringBuilder = new StringBuilder();        
        if (isShareData) {
            for (MetadataField key : userResults.getUserData().getMetadataFields()) {
                String value = URL.encodeQueryString(userResults.getUserData().getMetadataValue(key));
                if (stringBuilder.length() > 0) {
                    stringBuilder.append("&");
                }
                stringBuilder.append(key.getPostName()).append("=").append(value);
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append("&");
        }
        stringBuilder.append("userid").append("=").append(userResults.getUserData().getUserId()).append("&");
        stringBuilder.append("applicationversion").append("=").append(version.projectVersion()).append("&");
        if (!isShareData) {
            stringBuilder.append(metadataFieldProvider.shareMetadataField.getPostName()).append("=").append(userResults.getUserData().getMetadataValue(metadataFieldProvider.shareMetadataField)).append("&");
        }

        if (isShareData) {
//            String scoreLog = URL.encodeQueryString(userResults.getScoreLog());
//            stringBuilder.append("scorelog").append("=").append(scoreLog).append("&");
            String restultsData = URL.encodeQueryString(new ResultsSerialiser() {
                final DateTimeFormat format = DateTimeFormat.getFormat(reportDateFormat);

                @Override
                protected String formatDate(Date date) {
                    return format.format(date);
                }
            }.serialise(userResults));
            stringBuilder.append("quest_results=").append(new LocalStorage().getStoredGameData(userResults.getUserData().getUserId())).append(restultsData);
        }
        try {
            builder.sendRequest(stringBuilder.toString(), geRequestBuilder(builder, highScoreListener, highScoresUrl, userResults));
        } catch (RequestException exception) {
            highScoreListener.scoreSubmissionFailed(new HighScoreException(HighScoreException.ErrorType.buildererror, exception));
            logger.log(Level.SEVERE, "SubmitHighScore", exception);
        }
    }

    private RequestCallback geRequestBuilder(final RequestBuilder builder, final HighScoreListener highScoreListener, final String targetUri, final UserResults userResults) {
        return new RequestCallback() {
            @Override
            public void onError(Request request, Throwable exception) {
                highScoreListener.scoreSubmissionFailed(new HighScoreException(HighScoreException.ErrorType.connectionerror, exception));
                logger.warning(builder.getUrl());
                logger.log(Level.WARNING, "RequestCallback", exception);
            }

            @Override
            public void onResponseReceived(Request request, Response response) {
                if (200 == response.getStatusCode()) {
                    final String text = response.getText();
                    logger.info(text);
                    highScoreListener.scoreSubmissionComplete(JsonUtils.<JsArray<HighScoreData>>safeEval(response.getText()));
                } else {
                    highScoreListener.scoreSubmissionFailed(new HighScoreException(HighScoreException.ErrorType.non202response, "An error occured on the server: " + response.getStatusText()));
                    logger.warning(targetUri);
                    logger.warning(response.getStatusText());
                }
            }
        };
    }
}
