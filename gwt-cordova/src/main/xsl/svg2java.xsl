<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : map2properties.xsl
    Created on : December 10, 2014, 12:27 PM
    Author     : Peter Withers <p.withers@psych.ru.nl>
    Description:
        Converts the SVG map from inkscape into a properties file with all the regions from the input SVG.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:svg="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                xmlns="http://www.w3.org/2000/svg"
                xmlns:inkscape="http://www.inkscape.org/namespaces/inkscape"
                version="2.0">
    <xsl:output method="text" encoding="UTF-8" />
    <xsl:variable name="filename" select="(tokenize(base-uri(), '/'))[last()]"/>
    <xsl:variable name="classname" select="substring-before($filename, '.svg')"/>
    <xsl:template match="/">
        <xsl:text>package nl.ru.languageininteraction.language.client;
            
import nl.ru.languageininteraction.language.client.util.SvgTemplate;
            
            // generated with svg2java.xsl
public abstract class </xsl:text><xsl:value-of select="$classname" /><xsl:text> extends com.google.gwt.i18n.client.Messages {

    private static final SvgTemplate SVG_TEMPLATE = GWT.create(SvgTemplate.class);
    
</xsl:text>
<xsl:for-each select="svg:svg/svg:g[svg:path]">
    <xsl:for-each select="svg:path">    
    <xsl:text>
    @DefaultMessage("</xsl:text><xsl:value-of select="@style"/>"<xsl:text>)
    @Key("style</xsl:text><xsl:value-of select="@id"/><xsl:text>")
    String style</xsl:text><xsl:value-of select="@id"/><xsl:text>();
        
    @DefaultMessage("</xsl:text><xsl:value-of select="@data"/>"<xsl:text>)
    @Key("data</xsl:text><xsl:value-of select="@id"/><xsl:text>")
    String data</xsl:text><xsl:value-of select="@id"/><xsl:text>();
        
</xsl:text>
    </xsl:for-each>
</xsl:for-each>
<!--            <xsl:text>
</xsl:text>
            <xsl:text>data</xsl:text>
            <xsl:value-of select="@id"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@d"/>
            <xsl:text>
</xsl:text>
            <xsl:text>transform</xsl:text>
            <xsl:value-of select="@id"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@transform"/>
            <xsl:text>
</xsl:text>
            </xsl:for-each>
            <xsl:text>transform</xsl:text>
            <xsl:value-of select="replace(@inkscape:label, ' ', '_')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@transform"/>
            <xsl:text>
</xsl:text>
            <xsl:text>id</xsl:text>
            <xsl:value-of select="replace(@inkscape:label, ' ', '_')"/>
            <xsl:value-of select="@inkscape:label"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="replace(@inkscape:label, ' ', '_')"/>
            <xsl:text>
</xsl:text> 
        </xsl:for-each>
        
-->
<xsl:text>
public void getSvg(SafeHtmlBuilder builder) {
    </xsl:text>
<xsl:for-each select="svg:svg/svg:g[svg:path]">
        builder.append(SVG_TEMPLATE.groupTag(id<xsl:value-of select="replace(@inkscape:label, ' ', '_')"/>(),transform<xsl:value-of select="replace(@inkscape:label, ' ', '_')"/>()));
<xsl:for-each select="svg:path">
        builder.append(SVG_TEMPLATE.pathTag(transform<xsl:value-of select="@id"/>(), style<xsl:value-of select="@id"/>(), data<xsl:value-of select="@id"/>()));
</xsl:for-each>
        builder.append(SVG_TEMPLATE.groupTagEnd());
        </xsl:for-each>
<xsl:text>
}</xsl:text>
    </xsl:template>
</xsl:stylesheet>
