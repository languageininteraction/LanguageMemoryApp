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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Properties;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * @since Jan 29, 2015 4:23:38 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class Csv2Properties {

    private static final String FILE_SUFFIX = ".csv";
    private static final String PROPERTIES_SUFFIX = ".properties";
    private static final String COLUMN_SEPARATOR = ",";
    private static final String PROPERTY_SEPARATOR = "=";
    private static final int DE_COLUMN = 3;
    private static final int NL_COLUMN = 2;
    private static final int EN_COLUMN = 1;
    private static final int KEY_COLUMN = 0;
    private final HashMap<String, String> translationsEN = new HashMap<>();
    private final HashMap<String, String> translationsDE = new HashMap<>();
    private final HashMap<String, String> translationsNL = new HashMap<>();
    final File inputFile = new File("target/translations" + FILE_SUFFIX);

    public void readTranslations() {

    }

    public void parseInputCSV() throws IOException {
        final Reader reader = new InputStreamReader(inputFile.toURL().openStream(), "UTF-8");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);
        for (CSVRecord record : records) {
            String key_name = record.get(KEY_COLUMN);
            String en_value = record.get(EN_COLUMN);
            String nl_value = record.get(NL_COLUMN);
            String de_value = record.get(DE_COLUMN);
            System.out.println(key_name);
            System.out.println(en_value);
            System.out.println(nl_value);
            System.out.println(de_value);
            translationsEN.put(key_name, en_value);
            translationsNL.put(key_name, nl_value);
            translationsDE.put(key_name, de_value);
        }
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

            final InputStream propertiesFileStream = SimpleViewTest.class.getResourceAsStream("/nl/ru/languageininteraction/synaesthesia/client/" + propertiesFileName + ".properties");
            InputStreamReader inputStreamReader = new InputStreamReader(propertiesFileStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String lineString;
            while ((lineString = bufferedReader.readLine()) != null) {
                if (lineString.isEmpty()) {
                    writer.write("\n");
                    writerDE.write("\n");
                    writerNL.write("\n");
                } else if (lineString.startsWith("#")) {
                    writer.write(lineString);
                    writerDE.write(lineString);
                    writerNL.write(lineString);
                    writer.write("\n");
                    writerDE.write("\n");
                    writerNL.write("\n");
                } else {
                    String key = lineString.split("=")[0];
                    writer.write(key);
                    writer.write(PROPERTY_SEPARATOR);
                    writerDE.write(key);
                    writerDE.write(PROPERTY_SEPARATOR);
                    writerNL.write(key);
                    writerNL.write(PROPERTY_SEPARATOR);
                    final String escapedStringEN = (translationsEN.containsKey(key)) ? escapeString(translationsEN.get(key)) : escapePropertiesString(properties.getProperty(key, ""));
                    writer.write(escapedStringEN);
                    writer.write("\n");
                    final String escapedStringDE = (translationsDE.containsKey(key)) ? escapeString(translationsDE.get(key)) : escapePropertiesString(properties_de.getProperty(key, ""));
                    writerDE.write(escapedStringDE);
                    writerDE.write("\n");
                    final String escapedStringNL = (translationsNL.containsKey(key)) ? escapeString(translationsNL.get(key)) : escapePropertiesString(properties_nl.getProperty(key, ""));
                    writerNL.write(escapedStringNL);
                    writerNL.write("\n");
                }
            }
            writer.close();
            writerDE.close();
            writerNL.close();
        }
    }

    private String escapeString(String inputString) {
        return inputString.replaceAll("\n", "\\\\n");
    }

    private String escapePropertiesString(String inputString) {
        return inputString.replaceAll("\n", "\\\\n");
    }

    public static void main(String[] args) throws IOException {
        final Csv2Properties properties2Csv = new Csv2Properties();
        properties2Csv.parseInputCSV();
        properties2Csv.writePropertyValues("Messages");
        properties2Csv.writePropertyValues("Stimuli");
        properties2Csv.writePropertyValues("MetadataFields");
    }
}
