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
package nl.ru.languageininteraction.synaesthesia.client.registration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.ru.languageininteraction.synaesthesia.client.ServiceLocations;

/**
 * @since Oct 29, 2014 11:18:31 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class RegistrationService {

    private static final Logger logger = Logger.getLogger(RegistrationService.class.getName());
    final private ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);

    public void submitRegistration(RegistrationListener registrationListener) {
        final String registratinoUrl = serviceLocations.registrationUrl();
        final RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, registratinoUrl);
        try {
            builder.sendRequest(null, geRequestBuilder(builder, registrationListener, registratinoUrl));
        } catch (RequestException exception) {
            registrationListener.registrationFailed(exception);
            logger.log(Level.SEVERE, "SubmitRegistration", exception);
        }
    }

    private RequestCallback geRequestBuilder(final RequestBuilder builder, final RegistrationListener registrationListener, final String targetUri) {
        return new RequestCallback() {
            @Override
            public void onError(Request request, Throwable exception) {
                registrationListener.registrationFailed(exception);
                logger.warning(builder.getUrl());
                logger.log(Level.WARNING, "RequestCallback", exception);
            }

            @Override
            public void onResponseReceived(Request request, Response response) {
                if (200 == response.getStatusCode()) {
                    final String text = response.getText();
                    logger.info(text);
                    registrationListener.registrationComplete();
                } else {
                    registrationListener.registrationFailed(new RegistrationException("An error occured on the server: " + response.getStatusText()));
                    logger.warning(targetUri);
                    logger.warning(response.getStatusText());
                }
            }
        };
    }
}
