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
            
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import nl.ru.languageininteraction.language.client.util.SvgTemplate;
            
// generated with svg2java.xsl
public class </xsl:text><xsl:value-of select="$classname" /><xsl:text>Builder implements com.google.gwt.i18n.client.Messages {

    private static final SvgTemplate SVG_TEMPLATE = GWT.create(SvgTemplate.class);
    private static final </xsl:text><xsl:value-of select="$classname" /><xsl:text> SVG_DATA = GWT.create(</xsl:text><xsl:value-of select="$classname" /><xsl:text>.class);

    public enum SvgGroupStates {
        </xsl:text>
        <xsl:for-each select="svg:svg//svg:g[@inkscape:label]">
            <xsl:value-of select="translate(@inkscape:label, ' -', '__')"/><xsl:text>,
        </xsl:text>
        </xsl:for-each>
        <xsl:text>diagram // the diagram entry is used to identify the svg root element
    }
</xsl:text>
<!--<xsl:for-each select="svg:svg/svg:g[svg:path]">
    <xsl:text>
    @DefaultMessage("</xsl:text><xsl:value-of select="replace(@inkscape:label, ' ', '_')"/>"<xsl:text>)
    @Key("id</xsl:text><xsl:value-of select="replace(@inkscape:label, ' ', '_')"/><xsl:text>")
    abstract public String id</xsl:text><xsl:value-of select="replace(@inkscape:label, ' ', '_')"/><xsl:text>();
        
    @DefaultMessage("</xsl:text><xsl:value-of select="@transform"/>"<xsl:text>)
    @Key("data</xsl:text><xsl:value-of select="replace(@inkscape:label, ' ', '_')"/><xsl:text>")
    abstract public String transform</xsl:text><xsl:value-of select="replace(@inkscape:label, ' ', '_')"/><xsl:text>();
</xsl:text>
    <xsl:for-each select="svg:path">    
    <xsl:text>
    @DefaultMessage("</xsl:text><xsl:value-of select="@style"/>"<xsl:text>)
    @Key("style</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>")
    abstract public String style</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>();
        
    @DefaultMessage("</xsl:text><xsl:value-of select="@data"/>"<xsl:text>)
    @Key("data</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>")
    abstract public String data</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>();
        
    @DefaultMessage("</xsl:text><xsl:value-of select="@transform"/>"<xsl:text>)
    @Key("data</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>")
    abstract public String transform</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>();
</xsl:text>
    </xsl:for-each>
</xsl:for-each>-->
        <xsl:text>
    public void getSvg(SafeHtmlBuilder builder) {
        getDefsTag(builder);
</xsl:text>
        <xsl:for-each select="svg:svg/svg:g[svg:path]">
<xsl:text>        getSvg</xsl:text><xsl:value-of select="translate(@inkscape:label, ' -', '__')"/><xsl:text>(builder, SvgTemplate.Visibility.visible);
</xsl:text>
        </xsl:for-each>
        <xsl:text>    }
</xsl:text>
           <xsl:apply-templates select="svg:svg/svg:defs"/>
           <xsl:apply-templates select="svg:svg/svg:g[svg:path]"/>
        <xsl:text>}</xsl:text>
    </xsl:template>
    
    <xsl:template match="svg:g[@inkscape:label]">
<xsl:text>
    public void getSvg</xsl:text><xsl:value-of select="translate(@inkscape:label, ' -', '__')"/><xsl:text>(SafeHtmlBuilder builder, SvgTemplate.Visibility visibility) {
        builder.append(SVG_TEMPLATE.groupTag(SVG_DATA.id</xsl:text>
<xsl:value-of select="translate(@inkscape:label, ' -', '__')"/>
<xsl:text>(),SVG_DATA.transform</xsl:text>
<xsl:value-of select="translate(@inkscape:label, ' -', '__')"/>
<xsl:text>(), visibility));
</xsl:text>
<xsl:apply-templates/>
<xsl:text>      builder.append(SVG_TEMPLATE.groupTagEnd());
    }
</xsl:text>
    </xsl:template>
    <xsl:template match="svg:path">
<xsl:text>      builder.append(SVG_TEMPLATE.pathTag(SVG_DATA.transform</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.style</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.data</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>()));
</xsl:text>
    </xsl:template>
    <xsl:template match="svg:defs">
<xsl:text>public void getDefsTag(SafeHtmlBuilder builder) {
      builder.append(SVG_TEMPLATE.defsTag());
</xsl:text>     <xsl:apply-templates select="svg:linearGradient"/>
<xsl:text>      builder.append(SVG_TEMPLATE.defsTagEnd());
    }
</xsl:text>         
    </xsl:template>
    <xsl:template match="svg:linearGradient">
<xsl:text>      builder.append(SVG_TEMPLATE.linearGradientTag(SVG_DATA.id</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.xlinkHref</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.x1</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.y1</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.x2</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.y2</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.gradientTransform</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>()));
</xsl:text>     <xsl:apply-templates select="svg:stop"/>
<xsl:text>      builder.append(SVG_TEMPLATE.linearGradientTagEnd());
</xsl:text>         
    </xsl:template>
    <xsl:template match="svg:stop">
<xsl:text>      builder.append(SVG_TEMPLATE.stopTag(SVG_DATA.offset</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.style</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>()));
</xsl:text>         
    </xsl:template>
    <xsl:template match="svg:text">
<xsl:text>      builder.append(SVG_TEMPLATE.textTag(SVG_DATA.x</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.y</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.style</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>()));
</xsl:text>     <xsl:apply-templates select="svg:tspan"/>
<xsl:text>      builder.append(SVG_TEMPLATE.textTagEnd());
</xsl:text>         
    </xsl:template>
    <xsl:template match="svg:tspan">
<xsl:text>      builder.append(SVG_TEMPLATE.tspanTag(SVG_DATA.x</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.y</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.style</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.text</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>()));
</xsl:text>         
    </xsl:template>
    <xsl:template match="svg:rect">
<xsl:text>      builder.append(SVG_TEMPLATE.rectTag(SVG_DATA.x</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.y</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.rx</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.ry</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.width</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.height</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>(), SVG_DATA.style</xsl:text><xsl:value-of select="translate(@id, ' -', '__')"/><xsl:text>()));
</xsl:text>
    </xsl:template>
</xsl:stylesheet>
