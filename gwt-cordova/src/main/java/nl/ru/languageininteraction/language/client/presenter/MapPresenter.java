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

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.ru.languageininteraction.synaesthesia.client.listener.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.presenter.AbstractPresenter;
import nl.ru.languageininteraction.synaesthesia.client.presenter.Presenter;
import nl.ru.languageininteraction.synaesthesia.client.view.SimpleView;

/**
 * @since Nov 26, 2014 4:12:27 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MapPresenter extends AbstractPresenter implements Presenter {

    final VerticalPanel verticalPanel = new VerticalPanel();

    public MapPresenter(RootPanel widgetTag) {
        super(widgetTag, new SimpleView());
    }

    @Override
    public void pageClosing() {

    }

    @Override
    public void setTitle(PresenterEventListner titleBarListner) {

    }

    @Override
    public void setContent(AppEventListner appEventListner) {
        String[] items = new String[]{"one", "two", "three"};
        SafeHtmlBuilder builder = new SafeHtmlBuilder();
        for (String item : items) {
            builder.appendEscaped(item).appendHtmlConstant("<br/>");
        }
        builder.append(SafeHtmlUtils.fromTrustedString("<svg><rect height='5px' width='15px' fill='blue'/>"));
        builder.append(SafeHtmlUtils.fromTrustedString("<svg><rect x='10' y='10'height='88px' width='88px' fill='green'/>"));
        verticalPanel.add(new HTML(builder.toSafeHtml()));
        verticalPanel.add(new HTML(builder.toSafeHtml()));
        simpleView.setContent(verticalPanel);
    }
}
