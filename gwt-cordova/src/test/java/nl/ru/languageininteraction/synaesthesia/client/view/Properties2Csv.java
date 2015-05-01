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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

/**
 * @since Jan 29, 2015 4:23:38 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class Properties2Csv {

    private static final String FILE_SUFFIX = ".csv";
    private static final String COLUMN_SEPARATOR = ",";
    private static final String DE_COLUMN = "DE";
    private static final String NL_COLUMN = "NL";
    private static final String EN_COLUMN = "EN";
    private static final String KEY_COLUMN = "key";

    final File outputFile = new File("target/translations" + FILE_SUFFIX);

    public void deleteOutputFile() {
        outputFile.delete();
    }

    public void writePropertyValues(String propertiesFileName) throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        Properties properties_nl = new Properties();
        Properties properties_de = new Properties();
        final InputStream resourceAsStream = SimpleViewTest.class.getResourceAsStream("/nl/ru/languageininteraction/language/client/" + propertiesFileName + ".properties");
        properties.load(resourceAsStream);
        final InputStream resourceAsStream_nl = SimpleViewTest.class.getResourceAsStream("/nl/ru/languageininteraction/language/client/" + propertiesFileName + "_nl.properties");
        properties_nl.load(resourceAsStream_nl);
        final InputStream resourceAsStream_de = SimpleViewTest.class.getResourceAsStream("/nl/ru/languageininteraction/language/client/" + propertiesFileName + "_de.properties");
        properties_de.load(resourceAsStream_de);
        OutputStream outputStream = new FileOutputStream(outputFile, true);
        try (OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8")) {
            writer.write(KEY_COLUMN);
            writer.write(COLUMN_SEPARATOR);
            writer.write(EN_COLUMN);
            writer.write(COLUMN_SEPARATOR);
            writer.write(NL_COLUMN);
            writer.write(COLUMN_SEPARATOR);
            writer.write(DE_COLUMN);
            writer.write("\n");
            for (String key : properties.stringPropertyNames()) {
                writer.write(key);
                writer.write(COLUMN_SEPARATOR);
                writer.write(escapeString(properties.getProperty(key, "")));
                writer.write(COLUMN_SEPARATOR);
                writer.write(escapeString(properties_nl.getProperty(key, "")));
                writer.write(COLUMN_SEPARATOR);
                writer.write(escapeString(properties_de.getProperty(key, "")));
                writer.write("\n");
            }
            writer.close();
        }
    }

    private String escapeString(String inputString) {
        return "\"" + inputString.replaceAll("\"", "\"\"") + "\"";
    }

    public static void main(String[] args) throws IOException {
        final Properties2Csv properties2Csv = new Properties2Csv();
        properties2Csv.deleteOutputFile();
        properties2Csv.writePropertyValues("Messages");
//        properties2Csv.writePropertyValues("Stimuli");
        properties2Csv.writePropertyValues("MetadataFields");
    }
}
