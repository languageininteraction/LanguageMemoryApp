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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.ru.languageininteraction.language.client.GuessRound;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.view.ComplexView;

/**
 * @since Oct 22, 2014 3:00:25 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class TestSvgDuplicateStringsPresenter extends AbstractPresenter implements Presenter {

    private static final GuessRound SVG_DATA = GWT.create(GuessRound.class);

    public TestSvgDuplicateStringsPresenter(RootLayoutPanel widgetTag) {
        super(widgetTag, new ComplexView());
    }

    @Override
    protected void setTitle(PresenterEventListner titleBarListner) {
        simpleView.addTitle("TestSvgDuplicateStringsPresenter", titleBarListner);
    }

    @Override
    protected void setContent(final AppEventListner appEventListner) {
        ((ComplexView) simpleView).addText("Testing string instances.");
        ((ComplexView) simpleView).addText("The purpose of this test is to confirm if duplicate paths in the provided SVG are discovered by the compiler and optimised into one.");
        ((ComplexView) simpleView).addText("SVG_DATA.transformpath5452().equals(SVG_DATA.transformpath5955())");
        ((ComplexView) simpleView).addText(String.valueOf(SVG_DATA.transformpath5452().equals(SVG_DATA.transformpath5955())));

        ((ComplexView) simpleView).addText("SVG_DATA.stylepath5452().equals(SVG_DATA.stylepath5955())");
        ((ComplexView) simpleView).addText(String.valueOf(SVG_DATA.stylepath5452().equals(SVG_DATA.stylepath5955())));

        ((ComplexView) simpleView).addText("SVG_DATA.datapath5452().equals(SVG_DATA.datapath5955())");
        ((ComplexView) simpleView).addText(String.valueOf(SVG_DATA.datapath5452().equals(SVG_DATA.datapath5955())));

        ((ComplexView) simpleView).addText("SVG_DATA.datapath5452() == SVG_DATA.datapath5955()");
        ((ComplexView) simpleView).addText(String.valueOf(SVG_DATA.datapath5452() == SVG_DATA.datapath5955()));
        ((ComplexView) simpleView).addText("If the final test is true then only one copy is compiled into the application which is desired since it saves space.");
    }
}
