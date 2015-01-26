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
    <xsl:template match="/">
        <xsl:text>package nl.ru.languageininteraction.language.client;
            
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import nl.ru.languageininteraction.language.client.util.SvgTemplate;
import com.google.gwt.user.client.DOM;
import com.google.gwt.dom.client.Element;
                        
// generated with svg2java.xsl
public class </xsl:text><xsl:value-of select="$classname" /><xsl:text>Provider implements com.google.gwt.i18n.client.Messages {

    private static final SvgTemplate SVG_TEMPLATE = GWT.create(SvgTemplate.class);
    private static final </xsl:text><xsl:value-of select="$classname" /><xsl:text> SVG_DATA = GWT.create(</xsl:text><xsl:value-of select="$classname" /><xsl:text>.class);

    public enum SvgGroupStates {
        </xsl:text>
        <!--<xsl:value-of select="/text()"/>-->
<!--        <xsl:for-each select="text()">
            <xsl:value-of select="translate(@inkscape:label, ' -', '__')"/><xsl:text>,
        </xsl:text>
        </xsl:for-each>
-->        <xsl:text>diagram // the diagram entry is used to identify the svg root element
    }

    <!--public enum SvgTextElements {-->
        <!--</xsl:text>
        <xsl:for-each select="svg:svg//svg:tspan">
            <xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>,
        </xsl:text>
        </xsl:for-each>
        <xsl:text>end // this final element is not used
    -->}
        </xsl:text>
    </xsl:template>
</xsl:stylesheet>
