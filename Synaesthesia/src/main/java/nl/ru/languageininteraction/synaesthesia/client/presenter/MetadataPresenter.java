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
package nl.ru.languageininteraction.synaesthesia.client.presenter;

import nl.ru.languageininteraction.synaesthesia.client.view.MetadataView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import nl.ru.languageininteraction.synaesthesia.client.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.Messages;
import nl.ru.languageininteraction.synaesthesia.client.MetadataFields;
import nl.ru.languageininteraction.synaesthesia.client.Presenter;
import nl.ru.languageininteraction.synaesthesia.client.UserResults;

/**
 * @since Oct 21, 2014 11:50:56 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MetadataPresenter implements Presenter {

    private final Messages messages = GWT.create(Messages.class);
    private final MetadataFields mateadataFields = GWT.create(MetadataFields.class);
    private final RootPanel widgetTag;
    final MetadataView metadataView = new MetadataView(messages.metadatascreentext());
    private final UserResults userResults;

    public MetadataPresenter(RootPanel widgetTag, UserResults userResults) {
        this.widgetTag = widgetTag;
        this.userResults = userResults;
    }

    @Override
    public void setState(final AppEventListner appEventListner) {
        widgetTag.clear();
        metadataView.setButton(messages.nextbutton(), new AppEventListner() {

            @Override
            public void eventFired() {
                for (String fieldName : metadataView.getFieldNames()) {
                    userResults.setMetadataValue(fieldName, metadataView.getFieldValue(fieldName));
                }
                appEventListner.eventFired();
            }

        });
        metadataView.addTitle(messages.metadatascreentitle());
        metadataView.addField(mateadataFields.postName1(), mateadataFields.registrationField1());
        metadataView.addField(mateadataFields.postName2(), mateadataFields.registrationField2());
        metadataView.addField(mateadataFields.postName3(), mateadataFields.registrationField3());
        metadataView.addField(mateadataFields.postName4(), mateadataFields.registrationField4());
//        metadataView.setDisplayText(messages.nl_ru_languageininteraction_synaesthesia_introductionscreentext());
        metadataView.resizeView();
        widgetTag.add(metadataView);
    }
}
