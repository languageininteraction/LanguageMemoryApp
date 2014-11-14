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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import nl.ru.languageininteraction.synaesthesia.client.listener.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.Messages;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.view.SimpleView;

/**
 * @since Oct 28, 2014 3:32:10 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public abstract class AbstractPresenter implements Presenter {

    protected final Messages messages = GWT.create(Messages.class);
    protected final RootPanel widgetTag;
    final SimpleView simpleView;
    private PresenterEventListner backEventListner = null;

    public AbstractPresenter(RootPanel widgetTag, SimpleView simpleView) {
        this.widgetTag = widgetTag;
        this.simpleView = simpleView;
    }

    @Override
    public void setState(final AppEventListner appEventListner, final AppEventListner.ApplicationState prevState, final AppEventListner.ApplicationState nextState) {
        widgetTag.clear();
        if (prevState != null) {
            backEventListner = new PresenterEventListner() {

                @Override
                public void eventFired(Button button) {
                    pageClosing();
                    appEventListner.requestApplicationState(prevState);
                }
            };
        } else {
            backEventListner = new PresenterEventListner() {

                @Override
                public void eventFired(Button button) {
                    pageClosing();
                    appEventListner.requestApplicationState(AppEventListner.ApplicationState.menu);
                }
            };
        }
        setTitle(backEventListner);
        if (nextState != null) {
            simpleView.setButton(SimpleView.ButtonType.next, nextState.label, new PresenterEventListner() {

                @Override
                public void eventFired(Button button) {
                    pageClosing();
                    appEventListner.requestApplicationState(nextState);
                }
            });
        }
        setContent(appEventListner);
        simpleView.resizeView();
        widgetTag.add(simpleView);
    }

    @Override
    public void fireBackEvent() {
        if (backEventListner != null) {
            backEventListner.eventFired(null);
        }
    }

    abstract void pageClosing();

    abstract void setTitle(PresenterEventListner titleBarListner);

    abstract void setContent(final AppEventListner appEventListner);
}
