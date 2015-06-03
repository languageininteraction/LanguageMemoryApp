<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : csv2properties.xsl
    Created on : January 26, 2015, 10:28 AM
    Author     : Peter Withers <p.withers@psych.ru.nl>
    Description:
        Converts the CSV of language data into a localisable properties file.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" >
    <xsl:output method="text" encoding="UTF-8" />
    <xsl:param name="nameField" as="xs:integer" />
    <xsl:variable name="filename" select="(tokenize(base-uri(), '/'))[last()]"/>
    <xsl:variable name="classname" select="substring-before($filename, '.xml')"/>
    <xsl:variable name="csvfilepath" select="string-join((substring-before(base-uri(), '.xml'),'.csv'),'')"/>
    <xsl:template match="/">
        <xsl:text># generated from </xsl:text><xsl:value-of select="$filename" /><xsl:text> with csv2properties.xsl
</xsl:text>
        <!--<xsl:value-of select="$csvfilepath" />-->
        <xsl:variable name="csvText" select="unparsed-text($csvfilepath)" />
        <xsl:for-each select="tokenize($csvText, '\n')">
            <!--<xsl:text>line: </xsl:text><xsl:sequence select="."/>-->
            <!--<xsl:text>analyze</xsl:text>-->
            <xsl:analyze-string select="." regex='^"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*)"?,"?([^,^"]*).*$'>
                <xsl:matching-substring>
                    <xsl:if test="regex-group($nameField)">
<xsl:value-of select="regex-group(2)"/><xsl:text>_Name=</xsl:text><xsl:value-of select="regex-group($nameField)"/><xsl:text>
</xsl:text>
<xsl:value-of select="regex-group(2)"/><xsl:text>_Description=</xsl:text><xsl:value-of select="regex-group(1)"/><xsl:text>: no description available yet
</xsl:text>
                 </xsl:if>
                </xsl:matching-substring>
            </xsl:analyze-string>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
