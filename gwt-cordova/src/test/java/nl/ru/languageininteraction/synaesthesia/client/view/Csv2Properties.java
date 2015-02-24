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
import java.util.HashMap;
import java.util.Properties;

/**
 * @since Jan 29, 2015 4:23:38 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class Csv2Properties {

    private static final String FILE_SUFFIX = ".csv";
    private static final String PROPERTIES_SUFFIX = ".properties";
    private static final String COLUMN_SEPARATOR = ",";
    private static final String PROPERTY_SEPARATOR = "=";
    private final HashMap<String, String> translationsEN = new HashMap<>();
    private final HashMap<String, String> translationsDE = new HashMap<>();
    private final HashMap<String, String> translationsNL = new HashMap<>();
    final File inputFile = new File("target/translations" + FILE_SUFFIX);

    public void readTranslations() {

    }

    public void writePropertyValues(String propertiesFileName) throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        Properties properties_nl = new Properties();
        Properties properties_de = new Properties();
        final InputStream resourceAsStream = SimpleViewTest.class.getResourceAsStream("/nl/ru/languageininteraction/synaesthesia/client/" + propertiesFileName + ".properties");
        properties.load(resourceAsStream);
        final InputStream resourceAsStream_nl = SimpleViewTest.class.getResourceAsStream("/nl/ru/languageininteraction/synaesthesia/client/" + propertiesFileName + "_nl.properties");
        properties_nl.load(resourceAsStream_nl);
        final InputStream resourceAsStream_de = SimpleViewTest.class.getResourceAsStream("/nl/ru/languageininteraction/synaesthesia/client/" + propertiesFileName + "_de.properties");
        properties_de.load(resourceAsStream_de);
        final File outputFile = new File("target/" + propertiesFileName + PROPERTIES_SUFFIX);
        final File outputFileDE = new File("target/" + propertiesFileName + "_de" + PROPERTIES_SUFFIX);
        final File outputFileNL = new File("target/" + propertiesFileName + "_nl" + PROPERTIES_SUFFIX);
        OutputStream outputStream = new FileOutputStream(outputFile, false);
        OutputStream outputStreamDE = new FileOutputStream(outputFileDE, false);
        OutputStream outputStreamNL = new FileOutputStream(outputFileNL, false);
        try (OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
                OutputStreamWriter writerDE = new OutputStreamWriter(outputStreamDE, "UTF-8");
                OutputStreamWriter writerNL = new OutputStreamWriter(outputStreamNL, "UTF-8")) {
            for (String key : properties.stringPropertyNames()) {
                writer.write(key);
                writer.write(PROPERTY_SEPARATOR);
                writerDE.write(key);
                writerDE.write(PROPERTY_SEPARATOR);
                writerNL.write(key);
                writerNL.write(PROPERTY_SEPARATOR);
                writer.write(escapeString(translationsEN.get(key)));
                writer.write("\n");
                writerDE.write(escapeString(translationsDE.get(key)));
                writerDE.write("\n");
                writerNL.write(escapeString(translationsNL.get(key)));
                writerNL.write("\n");
            }
            writer.close();
        }
    }

    private String escapeString(String inputString) {
        return "\"" + inputString.replaceAll("\"", "\"\"") + "\"";
    }

    public static void main(String[] args) throws IOException {
        final Csv2Properties properties2Csv = new Csv2Properties();
        properties2Csv.writePropertyValues("Messages");
        properties2Csv.writePropertyValues("Stimuli");
        properties2Csv.writePropertyValues("MetadataFields");
    }
}
