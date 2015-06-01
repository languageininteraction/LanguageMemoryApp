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

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import nl.ru.languageininteraction.language.client.UserDetailsBuilder;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.service.AudioPlayer;

/**
 * @since Feb 4, 2015 1:21:58 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class PlayerDetailsView extends AbstractSvgView {

    protected final UserDetailsBuilder svgBuilder = new UserDetailsBuilder();
    protected PresenterEventListner age1ButtonListner;
    protected PresenterEventListner age2ButtonListner;
    protected PresenterEventListner age3ButtonListner;
    protected PresenterEventListner shareCheckBoxListner;
    protected PresenterEventListner addListner;
    protected PresenterEventListner removeListner;
    protected PresenterEventListner editNameListner;

    public PlayerDetailsView(AudioPlayer audioPlayer) throws AudioException {
        super(audioPlayer);
    }

    @Override
    protected void getSvg(SafeHtmlBuilder builder) {
        svgBuilder.getSvg(builder);
    }

    public void setAge1ButtonListner(PresenterEventListner age1ButtonListner) {
        this.age1ButtonListner = age1ButtonListner;
    }

    public void setAge2ButtonListner(PresenterEventListner age2ButtonListner) {
        this.age2ButtonListner = age2ButtonListner;
    }

    public void setAge3ButtonListner(PresenterEventListner age3ButtonListner) {
        this.age3ButtonListner = age3ButtonListner;
    }

    public void setShareCheckBoxListner(PresenterEventListner shareCheckBoxListner) {
        this.shareCheckBoxListner = shareCheckBoxListner;
    }

    public void setAddListner(PresenterEventListner addListner) {
        this.addListner = addListner;
    }

    public void setRemoveListner(PresenterEventListner removeListner) {
        this.removeListner = removeListner;
    }

    public void setEditNameListner(PresenterEventListner editNameListner) {
        this.editNameListner = editNameListner;
    }

    public void setShareData(Boolean shareOption) {
        if (shareOption) {
//            svgBuilder.showGroup(UserDetailsBuilder.SvgGroupStates.ShareCheckBoxChecked);
//            svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.ShareCheckBoxUnchecked);
        } else {
//            svgBuilder.showGroup(UserDetailsBuilder.SvgGroupStates.ShareCheckBoxUnchecked);
//            svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.ShareCheckBoxChecked);
            svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.ShareBox);
        }
    }

    public void clearAge() {
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.AgeCat1Selected);
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.AgeCat2Selected);
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.AgeCat3Selected);

        svgBuilder.showGroup(UserDetailsBuilder.SvgGroupStates.AgeCat1);
        svgBuilder.showGroup(UserDetailsBuilder.SvgGroupStates.AgeCat2);
        svgBuilder.showGroup(UserDetailsBuilder.SvgGroupStates.AgeCat3);
    }

    public void setAge1() {
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.AgeCat1);
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.AgeCat2Selected);
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.AgeCat3Selected);

        svgBuilder.showGroup(UserDetailsBuilder.SvgGroupStates.AgeCat1Selected);
        svgBuilder.showGroup(UserDetailsBuilder.SvgGroupStates.AgeCat2);
        svgBuilder.showGroup(UserDetailsBuilder.SvgGroupStates.AgeCat3);
    }

    public void setAge2() {
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.AgeCat2);
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.AgeCat1Selected);
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.AgeCat3Selected);

        svgBuilder.showGroup(UserDetailsBuilder.SvgGroupStates.AgeCat2Selected);
        svgBuilder.showGroup(UserDetailsBuilder.SvgGroupStates.AgeCat1);
        svgBuilder.showGroup(UserDetailsBuilder.SvgGroupStates.AgeCat3);
    }

    public void setAge3() {
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.AgeCat3);
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.AgeCat2Selected);
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.AgeCat1Selected);

        svgBuilder.showGroup(UserDetailsBuilder.SvgGroupStates.AgeCat3Selected);
        svgBuilder.showGroup(UserDetailsBuilder.SvgGroupStates.AgeCat2);
        svgBuilder.showGroup(UserDetailsBuilder.SvgGroupStates.AgeCat1);
    }

    public void setUserNameField(String userName) {
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.LangBox);
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.AddLangButton);
        svgBuilder.hideGroup(UserDetailsBuilder.SvgGroupStates.DeleteLangButton);
        svgBuilder.setLabel(UserDetailsBuilder.SvgTextElements.tspan3285, userName);
    }

    public void setUserScoreField(int userScore) {
        svgBuilder.setLabel(UserDetailsBuilder.SvgTextElements.tspan3303, String.valueOf(userScore));
    }

    @Override
    protected boolean performClick(final String svgGroupStateString) {
        boolean consumed = false;
        showDebugLabel(svgGroupStateString);
        UserDetailsBuilder.SvgGroupStates svgGroup = UserDetailsBuilder.SvgGroupStates.valueOf(svgGroupStateString);
        switch (svgGroup) {
            case NameField:
                consumed = true;
                editNameListner.eventFired(null);
                break;
            case AgeCat1:
                consumed = true;
                age1ButtonListner.eventFired(null);
                break;
            case AgeCat1Selected:
                consumed = true;
                break;
            case AgeCat2:
                consumed = true;
                age2ButtonListner.eventFired(null);
                break;
            case AgeCat2Selected:
                consumed = true;
                break;
            case AgeCat3:
                consumed = true;
                age3ButtonListner.eventFired(null);
                break;
            case AgeCat3Selected:
                consumed = true;
                break;
//            case Background:
//                backEventListner.eventFired(null);
//                break;
            case ShareBox:
            case ShareCheckBoxChecked:
            case ShareCheckBoxUnchecked:
                shareCheckBoxListner.eventFired(null);
                consumed = true;
                break;
            case ShareAndContinueButton:
                nextEventListner.eventFired(null);
                consumed = true;
                break;
        }
        return consumed;
    }

    @Override
    public void showAudioEnded() {
    }
}
