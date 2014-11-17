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
package nl.ru.languageininteraction.synaesthesia.client.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import nl.ru.languageininteraction.synaesthesia.client.model.ColourData;
import nl.ru.languageininteraction.synaesthesia.client.model.GroupScoreData;
import nl.ru.languageininteraction.synaesthesia.client.model.StimuliGroup;
import nl.ru.languageininteraction.synaesthesia.client.model.Stimulus;
import nl.ru.languageininteraction.synaesthesia.client.model.StimulusResponse;
import nl.ru.languageininteraction.synaesthesia.client.model.StimulusResponseGroup;
import nl.ru.languageininteraction.synaesthesia.client.model.UserResults;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @since Nov 7, 2014 5:02:14 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ScoreCalculatorTest {

    private UserResults getUserResults(String userId) {
        final UserResults userResults = new UserResults();
        final String resourcePath = "/nl/ru/languageininteraction/testdata/" + userId;
        System.out.println("resourcePath:" + resourcePath);
        final InputStream testDataStream = ScoreCalculatorTest.class.getClass().getResourceAsStream(resourcePath);
        Scanner scanner = new Scanner(testDataStream);
        scanner.useDelimiter("\t");

        userResults.setMetadataValue("user", userId);
        ArrayList<Stimulus> stimulusList = null;
        StimulusResponseGroup stimulusResponseGroup = null;
        while (scanner.hasNext()) {
            String user = scanner.next();
            if (!"1772837".equals(user)) {
                System.out.print("\n new group: " + user);
                stimulusResponseGroup = new StimulusResponseGroup();
                stimulusList = new ArrayList<>();
                userResults.addStimulusResponseGroup(new StimuliGroup(user, stimulusList), stimulusResponseGroup);
                user = scanner.next().trim();
            }
            assertEquals("1772837", user);
            System.out.print("\n user" + user);
            String grapheme = scanner.next();
            System.out.print(" grapheme" + grapheme);
            final Stimulus stimulus = new Stimulus(grapheme);
            if (!stimulusList.contains(stimulus)) {
                stimulusList.add(stimulus);
            }
//            int trialNumber = scanner.nextInt();
//            System.out.print(" trialNumber" + trialNumber);
            String hexcolor = scanner.next();
            System.out.print(" hexcolor" + hexcolor);
            int decimalRed = scanner.nextInt();
            System.out.print(" decimalRed" + decimalRed);
            int decimalGreen = scanner.nextInt();
            System.out.print(" decimalGreen" + decimalGreen);
            int decimalBlue = Integer.parseInt(scanner.nextLine().trim());
            System.out.print(" decimalBlue" + decimalBlue);
            final ColourData colourData = (decimalRed == -1) ? null : new ColourData(decimalRed, decimalGreen, decimalBlue);
            stimulusResponseGroup.addResponse(stimulus, new StimulusResponse(colourData, new Date(), 1000));
        }
        System.out.print("\n");
        scanner.close();
        return userResults;
    }

    /**
     * Test of getScore method, of class ScoreCalculator.
     */
    @Test
    public void testGetScore_Stimulus() {
        System.out.println("getScore");
        final UserResults userResults = getUserResults("syn1772837");
        final ScoreCalculator scoreCalculator = new ScoreCalculator(userResults);
        final GroupScoreData calculatedScores = scoreCalculator.calculateScores(scoreCalculator.getStimuliGroups().toArray(new StimuliGroup[0])[1]);
        int index = 0;
        assertEquals("0", calculatedScores.getScoreDataList().get(index).getStimulus().getValue());
//        assertEquals(0.03, calculatedScores.get(index).getDistance(), 0.01);
        assertEquals(207, calculatedScores.getScoreDataList().get(index).getAverageLuminance(), 0.01);
        index += 3;
        assertEquals("3", calculatedScores.getScoreDataList().get(index).getStimulus().getValue());
        assertEquals(null, calculatedScores.getScoreDataList().get(index).getDistance());
        assertEquals(189, calculatedScores.getScoreDataList().get(index).getAverageLuminance(), 0.01);
        index += 3;
        assertEquals("6", calculatedScores.getScoreDataList().get(index).getStimulus().getValue());
        assertEquals(null, calculatedScores.getScoreDataList().get(index).getDistance());
        assertEquals(0, calculatedScores.getScoreDataList().get(index).getAverageLuminance(), 0.01);
        index += 4;
        assertEquals("A", calculatedScores.getScoreDataList().get(index).getStimulus().getValue());
//        assertEquals(0.18, calculatedScores.get(index).getDistance(), 0.01);
        assertEquals(112, calculatedScores.getScoreDataList().get(index).getAverageLuminance(), 0.01);
        index += 4;
        assertEquals("E", calculatedScores.getScoreDataList().get(index).getStimulus().getValue());
//        assertEquals(0.29, calculatedScores.get(index).getDistance(), 0.01);
        assertEquals(129, calculatedScores.getScoreDataList().get(index).getAverageLuminance(), 0.01);

        assertEquals(1.12, calculatedScores.getScore(), 0.001);
    }

    /**
     * Test of getScore method, of class ScoreCalculator.
     */
//    @Test
//    public void testGetScore_0args() {
//        System.out.println("getScore");
//        ScoreCalculator instance = null;
//        double expResult = 0.0;
//        double result = instance.getScore();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of getAccuracy method, of class ScoreCalculator.
     */
//    @Test
//    public void testGetAccuracy() {
//        System.out.println("getAccuracy");
//        ScoreCalculator instance = null;
//        double expResult = 0.0;
//        double result = instance.getAccuracy();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    private GroupScoreData getGroupScoreData() {
        final UserResults userResults = new UserResults();
        ScoreCalculator scoreCalculator = new ScoreCalculator(userResults);
        final ArrayList<Stimulus> stimulusList = new ArrayList<Stimulus>();
        stimulusList.add(new Stimulus("A"));
        stimulusList.add(new Stimulus("B"));
        stimulusList.add(new Stimulus("C"));
        stimulusList.add(new Stimulus("D"));
        stimulusList.add(new Stimulus("E"));
        stimulusList.add(new Stimulus("F"));
        final StimulusResponseGroup stimulusResponseGroup = new StimulusResponseGroup();
        stimulusResponseGroup.addResponse(stimulusList.get(0), new StimulusResponse(new ColourData(0, 0, 0), null, 1500));
        stimulusResponseGroup.addResponse(stimulusList.get(1), new StimulusResponse(new ColourData(0, 0, 0), null, 1234));
        stimulusResponseGroup.addResponse(stimulusList.get(2), new StimulusResponse(new ColourData(0, 0, 0), null, 1000));
        stimulusResponseGroup.addResponse(stimulusList.get(3), new StimulusResponse(new ColourData(0, 0, 0), null, 96));
        stimulusResponseGroup.addResponse(stimulusList.get(4), new StimulusResponse(new ColourData(0, 0, 0), null, 1000));
        stimulusResponseGroup.addResponse(stimulusList.get(5), new StimulusResponse(new ColourData(0, 0, 0), null, 500));
        final StimuliGroup stimuliGroup = new StimuliGroup("TestStimulusGroup", stimulusList);
        userResults.addStimulusResponseGroup(stimuliGroup, stimulusResponseGroup);
        return scoreCalculator.calculateScores(stimuliGroup);
    }

    /**
     * Test of getMeanReactionTime method, of class ScoreCalculator.
     */
    @Test
    public void testGetMeanReactionTime() {
        System.out.println("getMeanReactionTime");
        double expResult = 888.333333;
        double result = getGroupScoreData().getMeanReactionTime();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of getReactionTimeDeviation method, of class ScoreCalculator.
     */
    @Test
    public void testGetReactionTimeDeviation() {
        System.out.println("getReactionTimeDeviation");
//        double expResult = 509.34376; // standard deviation 
        double expResult = 464.96511; // population standard deviation
        double result = getGroupScoreData().getReactionTimeDeviation();
        assertEquals(expResult, result, 0.001);
    }
}
