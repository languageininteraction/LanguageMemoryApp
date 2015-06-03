<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : csv2java.xsl
    Created on : January 26, 2015, 10:28 AM
    Author     : peterwithers
    Description:
        Converts the CSV of language data into a localisable java class.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
       <xsl:output method="text" encoding="UTF-8" />
    <xsl:variable name="filename" select="(tokenize(base-uri(), '/'))[last()]"/>
    <xsl:variable name="classname" select="substring-before($filename, '.xml')"/>
    <xsl:variable name="csvfilepath" select="string-join((substring-before(base-uri(), '.xml'),'.csv'),'')"/>
    <xsl:template match="/">
        <xsl:text>package nl.ru.languageininteraction.language.client;
            
import com.google.gwt.core.client.GWT;
                        
// generated with csv2java.xsl
public class </xsl:text><xsl:value-of select="$classname" /><xsl:text>Provider implements com.google.gwt.i18n.client.Messages {

    private static final </xsl:text><xsl:value-of select="$classname" /><xsl:text> LANGUAGE_DATA = GWT.create(</xsl:text><xsl:value-of select="$classname" /><xsl:text>.class);
    public static final int soundFileCount = 5;

    public enum LanguageSample {
        </xsl:text>
        <xsl:variable name="csvText" select="unparsed-text($csvfilepath)" />
        <xsl:for-each select="tokenize($csvText, '\n')">
            <xsl:analyze-string select="." regex='^"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*).*$'>
                <xsl:matching-substring>
<xsl:value-of select="regex-group(2)"/><xsl:text>(LANGUAGE_DATA.</xsl:text><xsl:value-of select="regex-group(2)"/><xsl:text>_Name(),"</xsl:text><xsl:value-of select="regex-group(2)"/><xsl:text>",</xsl:text><xsl:value-of select="regex-group(3)"/><xsl:text>,"</xsl:text><xsl:value-of select="regex-group(4)"/><xsl:text>","</xsl:text><xsl:value-of select="regex-group(5)"/><xsl:text>",</xsl:text><xsl:value-of select="regex-group(6)"/><xsl:text>==1),
        </xsl:text>
                </xsl:matching-substring>
            </xsl:analyze-string>
        </xsl:for-each>
        <xsl:text>;
    final private String isoCode;
    final private int population;
    final private String area;
    final private String family;
    final private String name;
    final private boolean dobes;

        private LanguageSample(String name, String isoCode, int population, String area, String family, boolean dobes) {
            this.isoCode = isoCode;
            this.population = population;
            this.area = area;
            this.family = family;
            this.dobes = dobes;
            this.name = name;
        }
        
        public String getIsoCode() {
            return isoCode;
        }

        public int getPopulation() {
            return population;
        }

        public String getArea() {
            return area;
        }

        public String getFamily() {
            return family;
        }

        public boolean isDobes() {
            return dobes;
        }

        public String getLanguageName() {
            return name;
        }

        public String[] getSoundFiles() {
            return new String[]{"./media/" + isoCode + "/s1.m4a", "./media/" + isoCode + "/s2.m4a", "./media/" + isoCode + "/s3.m4a", "./media/" + isoCode + "/s4.m4a", "./media/" + isoCode + "/s5.m4a"};
        }
    }
}
        </xsl:text>
    </xsl:template>
</xsl:stylesheet>
