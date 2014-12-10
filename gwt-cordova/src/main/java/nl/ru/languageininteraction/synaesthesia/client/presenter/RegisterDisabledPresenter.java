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

import nl.ru.languageininteraction.language.client.presenter.AbstractPresenter;
import nl.ru.languageininteraction.language.client.presenter.Presenter;
import com.google.gwt.user.client.ui.RootPanel;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.view.SimpleView;

/**
 * @since Oct 7, 2014 2:17:51 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class RegisterDisabledPresenter extends AbstractPresenter implements Presenter {

    public RegisterDisabledPresenter(RootPanel widgetTag) {
        super(widgetTag, new SimpleView());
    }

    @Override
    protected void setTitle(PresenterEventListner titleBarListner) {
        simpleView.addTitle(messages.registerScreenTitle(), titleBarListner);
    }

    @Override
    protected void setContent(final AppEventListner appEventListner) {
        simpleView.setDisplayText(messages.registerDisabledScreenText());
    }
}
