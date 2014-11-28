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

import nl.ru.languageininteraction.language.client.presenter.AbstractPresenter;
import nl.ru.languageininteraction.language.client.presenter.Presenter;
import com.google.gwt.user.client.ui.RootPanel;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.view.SimpleView;

/**
 * @since Oct 22, 2014 3:00:25 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ErrorPresenter extends AbstractPresenter implements Presenter {

    private final String errorMessage;

    public ErrorPresenter(RootPanel widgetTag, String errorMessage) {
        super(widgetTag, new SimpleView());
        this.errorMessage = errorMessage;
    }

    @Override
    protected void setTitle(PresenterEventListner titleBarListner) {
        simpleView.addTitle(messages.errorScreenTitle(), titleBarListner);
    }

    @Override
    protected void setContent(final AppEventListner appEventListner) {
        simpleView.setDisplayText(messages.errorScreenText(errorMessage));
    }

    @Override
    protected void pageClosing() {
    }
}
