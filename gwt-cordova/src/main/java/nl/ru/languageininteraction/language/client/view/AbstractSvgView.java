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
package nl.ru.languageininteraction.language.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import nl.ru.languageininteraction.language.client.MatchLanguageBuilder;
import nl.ru.languageininteraction.language.client.Messages;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.listener.SingleShotEventListner;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;

/**
 * @since Jan 15, 2015 5:30:25 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public abstract class AbstractSvgView extends AbstractView {

    protected final Messages messages = GWT.create(Messages.class);
    final VerticalPanel verticalPanel = new VerticalPanel();
    protected final AudioPlayer audioPlayer;
    protected PresenterEventListner backEventListner = null;
    protected PresenterEventListner nextEventListner = null;
    protected Label label = new Label("clicked labels show here");
//    private static final String ROTATABLE_GROUP = "rotatableGroup";

    public AbstractSvgView(AudioPlayer audioPlayer) throws AudioException {
        this.audioPlayer = audioPlayer;
    }

    @Override
    protected void parentResized(int height, int width, String units) {
//        final Element diagramElement = DOM.getElementById(MatchLanguageBuilder.SvgGroupStates.svgDiagram.name());
//        final Element diagramElement = DOM.getElementById(ROTATABLE_GROUP);
//        if (diagramElement != null) {
//            diagramElement.setAttribute("transform", "rotate(-90)");
////            diagramElement.setAttribute("width", width + units);
//        }
//        super.parentResized(height, width, units);
    }

//    private void showGroup(SvgGroupStates group) {
////        DOM.getElementById(group.name()).setAttribute("style", "visibility:" + SvgTemplate.Visibility.visible);
//        SafeHtmlBuilder builder = new SafeHtmlBuilder();
//        matchLanguageBuilder.getSvgChoiceArrow1(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgChoiceArrow2(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgChoiceArrow3(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgChoiceArrow4(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgChoiceArrow5(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton1(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton2(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton3(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton4(builder, SvgTemplate.Visibility.visible);
//        matchLanguageBuilder.getSvgSampleButton5(builder, SvgTemplate.Visibility.visible);
////        final HTML html = new HTML(builder.toSafeHtml());
//        DOM.getElementById(SvgGroupStates.diagram.name()).setInnerSafeHtml(builder.toSafeHtml());
//    }
//
//    private void hideGroup(SvgGroupStates group) {
////        DOM.getElementById(group.name()).setAttribute("style", "visibility:" + SvgTemplate.Visibility.hidden);
//        DOM.getElementById(SvgGroupStates.ChoiceArrow2.name()).removeFromParent();
//    }
    public void setupScreen(PresenterEventListner backEventListner, PresenterEventListner nextEventListner) {
        this.backEventListner = backEventListner;
        this.nextEventListner = nextEventListner;
        verticalPanel.add(label);
        SafeHtmlBuilder builder = new SafeHtmlBuilder();
        builder.append(SafeHtmlUtils.fromTrustedString("<style>.overlay {pointer-events: none;}</style>"));
//        int height = Window.getClientHeight() - (HEADER_SIZE * 3);
//        int width = Window.getClientWidth();
        builder.append(SafeHtmlUtils.fromTrustedString("<svg class='svgDiagram' id='" + MatchLanguageBuilder.SvgGroupStates.svgDiagram.name() + "' width=\"100%\" height=\"100%\" viewBox='0 0 568 320' >"));
//<g id=\"" + ROTATABLE_GROUP + "\">
        getSvg(builder);
        builder.append(SafeHtmlUtils.fromTrustedString("</svg>"));
//</g>
        final HTML html = new HTML(builder.toSafeHtml());
        html.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                event.preventDefault();
                eventTriggered(Element.as(event.getNativeEvent().getEventTarget()));
            }
        });
        html.addTouchEndHandler(new TouchEndHandler() {

            @Override
            public void onTouchEnd(TouchEndEvent event) {
                event.preventDefault();
                eventTriggered(Element.as(event.getNativeEvent().getEventTarget()));
            }
        });
        html.setStylePrimaryName("svgPanel");
        verticalPanel.add(html);
        add(verticalPanel);
    }

    private void eventTriggered(Element targetElement) {
        boolean consumed = false;
        while (!consumed) {
            while (targetElement.getParentElement() != null && targetElement.getId().isEmpty()) {
                targetElement = targetElement.getParentElement();
            }
            final String elementId = targetElement.getId();
            if (elementId.equals(MatchLanguageBuilder.SvgGroupStates.svgDiagram.name())) {
                // we have navigated to the root node of the SVG
                return;
            }
            if (!elementId.isEmpty()) {
                try {
                    consumed = performClick(elementId);
                } catch (IllegalArgumentException exception) {
                    // id values can exist outside of group tags, only group tags are put into the enum and so this exception is not exceptional
                }
            }
            targetElement = targetElement.getParentElement();
        }
    }

    public void showWidgetPopup(IsWidget popupContentWidget) {
        showWidgetPopup(null, popupContentWidget);
    }

    public void showWidgetPopup(final PresenterEventListner saveEventListner, IsWidget popupContentWidget) {
        final PopupPanel popupPanel = new PopupPanel(false); // the close action to this panel causes background buttons to be clicked
        popupPanel.setGlassEnabled(true);
        popupPanel.setStylePrimaryName("svgPopupPanel");
        final VerticalPanel popupverticalPanel = new VerticalPanel();
        popupverticalPanel.add(popupContentWidget);

        popupverticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        final SingleShotEventListner cancelSingleShotEventListner = new SingleShotEventListner() {

            @Override
            protected void singleShotFired() {
                popupPanel.hide();
            }
        };
        final HorizontalPanel buttonPanel = new HorizontalPanel();
        final Button cancelButton = new Button(messages.popupCancelButtonLabel());
        cancelButton.addClickHandler(cancelSingleShotEventListner);
        cancelButton.addTouchStartHandler(cancelSingleShotEventListner);
        cancelButton.addTouchMoveHandler(cancelSingleShotEventListner);
        cancelButton.addTouchEndHandler(cancelSingleShotEventListner);
        buttonPanel.add(cancelButton);
        if (saveEventListner != null) {
            final SingleShotEventListner okSingleShotEventListner = new SingleShotEventListner() {

                @Override
                protected void singleShotFired() {
                    popupPanel.hide();
                    saveEventListner.eventFired(null);
                }
            };
            final Button okButton = new Button(messages.popupOkButtonLabel());
            okButton.addClickHandler(okSingleShotEventListner);
            okButton.addTouchStartHandler(okSingleShotEventListner);
            okButton.addTouchMoveHandler(okSingleShotEventListner);
            okButton.addTouchEndHandler(okSingleShotEventListner);
            buttonPanel.add(okButton);
        }
        popupverticalPanel.add(buttonPanel);
        popupPanel.setWidget(popupverticalPanel);
        popupPanel.setPopupPositionAndShow(new PopupPanel.PositionCallback() {

            @Override
            public void setPosition(int offsetWidth, int offsetHeight) {
                final int topPosition = Window.getClientHeight() / 2 - offsetHeight;
                // topPosition is used to make sure the dialogue is above the half way point on the screen to avoid the software keyboard covering the box
                // topPosition is also checked to make sure it does not show above the top of the page
                popupPanel.setPopupPosition(Window.getClientWidth() / 2 - offsetWidth / 2, (topPosition < 0) ? 0 : topPosition);
            }
        });
    }

    abstract protected void getSvg(SafeHtmlBuilder builder);

    abstract protected boolean performClick(final String svgGroupStateString);

    abstract public void showAudioEnded();
}
