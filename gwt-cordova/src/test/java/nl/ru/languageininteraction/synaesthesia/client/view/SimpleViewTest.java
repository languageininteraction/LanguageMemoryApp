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
package nl.ru.languageininteraction.synaesthesia.client.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @since Jan 9, 2015 10:21:20 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class SimpleViewTest {

    public SimpleViewTest() {
    }

    /**
     * Test that all screen title strings are below a given length as required
     * by the UI style guide.
     *
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testCheckTitleLength() throws FileNotFoundException, IOException {
        System.out.println("CheckTitleLength");
        final int maxTitleLength = 20;
        final File directory = new File("./src/main/resources/nl/ru/languageininteraction/language/client/");
        assertTrue("Properties directory not found.", directory.exists());
        System.out.println(directory.getAbsolutePath());
        for (String fileName : directory.list(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("Messages") && name.endsWith(".properties");
            }
        })) {
            System.out.println(fileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(directory, fileName)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("#")) {
                    String[] parts = line.split("=", 2);
                    if (parts.length > 1) {
                        if (parts[0].toLowerCase().contains("title")) {
                            System.out.println(line);
                            final String part = parts[1].replace("\\u00", ""); // unicode strings should only be counted as their display length
                            assertTrue("The title string is too long: " + line, part.length() < maxTitleLength);
                        }
                    }
                }
            }
        }
    }
}
