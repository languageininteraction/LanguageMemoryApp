/*
 * Copyright (C) 2015 Language In Interaction
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
package nl.ru.languageininteraction.language.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.ru.languageininteraction.language.client.ExplainDataSharingScreenBuilder;
import nl.ru.languageininteraction.language.client.Messages;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.model.UserResults;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;
import nl.ru.languageininteraction.language.client.service.LocalStorage;
import nl.ru.languageininteraction.language.client.service.MetadataFieldProvider;
import nl.ru.languageininteraction.language.client.view.ExplainDataSharingScreenView;

/**
 * @since Feb 4, 2015 11:26:10 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ExplainDataSharingScreenPresenter extends AbstractSvgPresenter implements Presenter {

    protected final ExplainDataSharingScreenBuilder svgBuilder = new ExplainDataSharingScreenBuilder();
    private final Messages messages = GWT.create(Messages.class);

    public ExplainDataSharingScreenPresenter(RootLayoutPanel widgetTag, final UserResults userResults, AudioPlayer audioPlayer, final AppEventListner appEventListner) throws AudioException {
        super(widgetTag, userResults, audioPlayer, new ExplainDataSharingScreenView(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(Button button) {
                // shareAgreed
                final MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();
                userResults.getUserData().setMetadataValue(metadataFieldProvider.shareMetadataField, metadataFieldProvider.shareMetadataField.getControlledVocabulary()[0]);
                new LocalStorage().storeData(userResults);
                appEventListner.requestApplicationState(AppEventListner.ApplicationState.guessround);
            }
        }, new PresenterEventListner() {

            @Override
            public String getLabel() {
                return "";
            }

            @Override
            public void eventFired(Button button) {
                new LocalStorage().storeData(userResults);
                appEventListner.requestApplicationState(AppEventListner.ApplicationState.guessround);
            }
        },
                audioPlayer));
    }

    @Override
    void configureSvg() {
        svgBuilder.setLabel(ExplainDataSharingScreenBuilder.SvgTextElements.tspan6492, messages.permissionLine1());
        svgBuilder.setLabel(ExplainDataSharingScreenBuilder.SvgTextElements.tspan6488, messages.permissionLine2());
        svgBuilder.setLabel(ExplainDataSharingScreenBuilder.SvgTextElements.tspan6352, messages.permissionLine3());
        svgBuilder.setLabel(ExplainDataSharingScreenBuilder.SvgTextElements.tspan6360, messages.permissionLine4());
        svgBuilder.setLabel(ExplainDataSharingScreenBuilder.SvgTextElements.tspan6368, messages.permissionLine5());
        svgBuilder.setLabel(ExplainDataSharingScreenBuilder.SvgTextElements.tspan6376, messages.permissionLine6());
        svgBuilder.setLabel(ExplainDataSharingScreenBuilder.SvgTextElements.tspan6384, messages.permissionLine7());
        svgBuilder.setLabel(ExplainDataSharingScreenBuilder.SvgTextElements.tspan6392, messages.permissionLine8());
        svgBuilder.setLabel(ExplainDataSharingScreenBuilder.SvgTextElements.tspan6400, messages.permissionLine9());
        svgBuilder.setLabel(ExplainDataSharingScreenBuilder.SvgTextElements.tspan6408, messages.permissionLine10());
        svgBuilder.setLabel(ExplainDataSharingScreenBuilder.SvgTextElements.tspan6416, messages.permissionLine11());
        svgBuilder.setLabel(ExplainDataSharingScreenBuilder.SvgTextElements.tspan5602, messages.yesText());
        svgBuilder.setLabel(ExplainDataSharingScreenBuilder.SvgTextElements.tspan6386, messages.noText());
    }

    @Override
    boolean nextEventFired() {
        return true;
    }
}
