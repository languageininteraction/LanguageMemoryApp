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
import java.util.Scanner;
import nl.ru.languageininteraction.synaesthesia.client.model.Stimulus;
import nl.ru.languageininteraction.synaesthesia.client.model.UserResults;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * @since Nov 7, 2014 5:02:14 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ScoreCalculatorTest {

    private UserResults getUserResults(String userId) {
        final UserResults userResults = new UserResults();
        final String resourcePath = "/nl/ru/languageininteraction/synaesthesia/client/util/testdata/" + userId;
        System.out.println("resourcePath:" + resourcePath);
        final InputStream testDataStream = ScoreCalculatorTest.class.getClass().getResourceAsStream(resourcePath);
        Scanner scanner = new Scanner(testDataStream);
        scanner.useDelimiter("[\t\r\n]*");
        while (scanner.hasNext()) {
            String user = scanner.next();
            System.out.print("\n user" + user);
            String grapheme = scanner.next();
            System.out.print(" grapheme" + grapheme);
            int trialNumber = scanner.nextInt();
            System.out.print(" trialNumber" + trialNumber);
            String hexcolor = scanner.next();
            System.out.print(" hexcolor" + hexcolor);
            int decimalRed = scanner.nextInt();
            System.out.print(" decimalRed" + decimalRed);
            int decimalGreen = scanner.nextInt();
            System.out.print(" decimalGreen" + decimalGreen);
            int decimalBlue = scanner.nextInt();
            System.out.print(" decimalBlue" + decimalBlue);
            scanner.nextLine();
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
        getUserResults("syn1772837");
        Stimulus stimulus = null;
        ScoreCalculator instance = null;
        double expResult = 0.0;
        double result = instance.getScore(stimulus);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
    /**
     * Test of getMeanReactionTime method, of class ScoreCalculator.
     */
//    @Test
//    public void testGetMeanReactionTime() {
//        System.out.println("getMeanReactionTime");
//        ScoreCalculator instance = null;
//        double expResult = 0.0;
//        double result = instance.getMeanReactionTime();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of getReactionTimeDeviation method, of class ScoreCalculator.
     */
//    @Test
//    public void testGetReactionTimeDeviation() {
//        System.out.println("getReactionTimeDeviation");
//        ScoreCalculator instance = null;
//        double expResult = 0.0;
//        double result = instance.getReactionTimeDeviation();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
