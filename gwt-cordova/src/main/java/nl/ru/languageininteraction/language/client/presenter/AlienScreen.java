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
package nl.ru.languageininteraction.language.client.presenter;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.view.SimpleView;

/**
 * @since Dec 3, 2014 10:46:31 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class AlienScreen extends AbstractPresenter implements Presenter {

    public AlienScreen(RootPanel widgetTag) {
        super(widgetTag, new SimpleView());
    }

    @Override
    protected void setTitle(PresenterEventListner titleBarListner) {
    }

    @Override
    protected void setContent(AppEventListner appEventListner) {
        simpleView.setContent(new ScrollPanel(new Image("./images/mockup.svg")));
    }

}
