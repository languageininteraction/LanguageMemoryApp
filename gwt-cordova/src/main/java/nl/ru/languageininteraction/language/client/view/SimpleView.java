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
package nl.ru.languageininteraction.language.client.view;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.listener.SingleShotEventListner;
import nl.ru.languageininteraction.synaesthesia.client.view.MenuButton;

/**
 * @since Oct 7, 2014 2:06:28 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class SimpleView extends AbstractView {

    public enum ButtonType {

        menu, back, next
    }
    private final HorizontalPanel footerPanel;
    private final Grid headerPanel;
    private final VerticalPanel borderedContentPanel;
    private final ScrollPanel scrollPanel;

    public SimpleView() {
        headerPanel = new Grid(1, 3);
        headerPanel.setWidth("100%");
        headerPanel.setStylePrimaryName("headerPanel");
        footerPanel = new HorizontalPanel();
        borderedContentPanel = new VerticalPanel();
        borderedContentPanel.setStylePrimaryName("contentPanel");
        footerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        addNorth(headerPanel, HEADER_SIZE);
//        addSouth(footerPanel, 50);
        scrollPanel = new ScrollPanel();
        borderedContentPanel.add(scrollPanel);
        borderedContentPanel.add(footerPanel);
        footerPanel.setVisible(false);
        add(borderedContentPanel);
    }

    public void setContent(Panel panel) {
        panel.setStylePrimaryName("contentBody");
        scrollPanel.setWidget(panel);
    }

    public void setDisplayText(String text) {
        final HTML html = new HTML(new SafeHtmlBuilder().appendEscapedLines(text).toSafeHtml());
        html.setStylePrimaryName("contentBody");
        scrollPanel.setWidget(html);
    }

    public void addTitle(String label, final PresenterEventListner presenterListerner) {
        final Label headerLabel = new Label(label);
        headerLabel.setStylePrimaryName("headerLabel");
        headerPanel.setWidget(0, 0, new MenuButton(presenterListerner));
        headerPanel.setWidget(0, 1, headerLabel);
        headerPanel.setStylePrimaryName("headerPanel");
        headerPanel.getColumnFormatter().setWidth(1, "100%");
    }

    public Button setButton(ButtonType buttonType, final PresenterEventListner presenterListerner) {
        footerPanel.setVisible(true);
        final Button nextButton = new Button(presenterListerner.getLabel());
        nextButton.addStyleName(buttonType.name() + "Button");
        nextButton.setEnabled(true);
        footerPanel.add(nextButton);
        SingleShotEventListner singleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                if (nextButton.isEnabled()) {
                    presenterListerner.eventFired(nextButton);
                } else {
                    resetSingleShot();
                }
            }
        };
        nextButton.addTouchStartHandler(singleShotEventListner);
        nextButton.addTouchMoveHandler(singleShotEventListner);
        nextButton.addTouchEndHandler(singleShotEventListner);
        nextButton.addClickHandler(singleShotEventListner);
        return nextButton;
    }

    public void removeFooterButtons() {
        footerPanel.clear();
        footerPanel.setVisible(footerPanel.getWidgetCount() > 0);
    }

    protected void scrollToPosition(int position) {
        scrollPanel.setVerticalScrollPosition(scrollPanel.getVerticalScrollPosition() + position - scrollPanel.getAbsoluteTop());
    }

    @Override
    protected void parentResized(int height, int width, String units) {
        footerPanel.setWidth(width + "px");
        scrollPanel.setWidth(width + "px");
        if (footerPanel.getWidgetCount() > 0) {
            scrollPanel.setHeight(height - HEADER_SIZE - HEADER_SIZE + "px");
        } else {
            scrollPanel.setHeight(height - HEADER_SIZE + "px");
        }
        setStyleByWidth(width);
    }
    protected static final int HEADER_SIZE = 50;
}
