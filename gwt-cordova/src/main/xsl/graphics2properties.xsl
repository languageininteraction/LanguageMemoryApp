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
    <xsl:template match="/">
        <xsl:text># generated with map2properties.xsls
</xsl:text>
    <xsl:apply-templates select="svg:svg/svg:defs/svg:linearGradient"/>
    <xsl:apply-templates select="svg:svg//svg:g"/>
    <xsl:apply-templates select="svg:svg//svg:rect"/>
    <xsl:apply-templates select="svg:svg//svg:circle"/>
    <xsl:apply-templates select="svg:svg//svg:ellipse"/>
    <xsl:apply-templates select="svg:svg//svg:path"/>
    <xsl:apply-templates select="svg:svg//svg:text"/>
    </xsl:template>
    <xsl:template match="svg:path">                
            <xsl:text>style</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@style"/>
            <xsl:text>
</xsl:text>
            <xsl:text>data</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@d"/>
            <xsl:text>
</xsl:text>
            <xsl:text>transform</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@transform"/>
            <xsl:text>
</xsl:text>
            <xsl:text>id</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@id"/>
            <xsl:text>
</xsl:text>
    </xsl:template>
    <xsl:template match="svg:text">
            <xsl:text>style</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@style"/>
            <xsl:text>
</xsl:text>
            <xsl:text>x</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@x"/>
            <xsl:text>
</xsl:text>
            <xsl:text>y</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@y"/>
            <xsl:text>
</xsl:text>
            <xsl:text>transform</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@transform"/>
            <xsl:text>
</xsl:text>
            <xsl:apply-templates select="svg:tspan" />
    </xsl:template>
    <xsl:template match="svg:text/svg:tspan">
            <xsl:text>id</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@id"/>
            <xsl:text>
</xsl:text>
            <xsl:text>style</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@style"/>
            <xsl:text>
</xsl:text>
            <xsl:text>x</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@x"/>
            <xsl:text>
</xsl:text>
            <xsl:text>y</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@y"/>
            <xsl:text>
</xsl:text>
            <xsl:text>text</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="text()"/>
            <xsl:text>
</xsl:text>
    </xsl:template>
    <xsl:template match="svg:rect">                
            <xsl:text>style</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@style"/>
            <xsl:text>
</xsl:text>
            <xsl:text>x</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@x"/>
            <xsl:text>
</xsl:text>
            <xsl:text>y</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@y"/>
            <xsl:text>
</xsl:text>
            <xsl:text>rx</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@rx"/>
            <xsl:text>
</xsl:text>
            <xsl:text>ry</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@ry"/>
            <xsl:text>
</xsl:text>
            <xsl:text>width</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@width"/>
            <xsl:text>
</xsl:text>
            <xsl:text>height</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@height"/>
            <xsl:text>
</xsl:text>
    </xsl:template>
    <xsl:template match="svg:circle">                
            <xsl:text>style</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@style"/>
            <xsl:text>
</xsl:text>
            <xsl:text>cx</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@cx"/>
            <xsl:text>
</xsl:text>
            <xsl:text>cy</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@cy"/>
            <xsl:text>
</xsl:text>
            <xsl:text>r</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@r"/>
            <xsl:text>
</xsl:text>
    </xsl:template>
    <xsl:template match="svg:ellipse">                
            <xsl:text>style</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@style"/>
            <xsl:text>
</xsl:text>
            <xsl:text>cx</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@cx"/>
            <xsl:text>
</xsl:text>
            <xsl:text>cy</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@cy"/>
            <xsl:text>
</xsl:text>
            <xsl:text>rx</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@rx"/>
            <xsl:text>
</xsl:text>
            <xsl:text>ry</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@ry"/>
            <xsl:text>
</xsl:text>
            <xsl:text>transform</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@transform"/>
            <xsl:text>
</xsl:text>
    </xsl:template>
    <xsl:template match="svg:g">
            <xsl:text>transform</xsl:text>
            <xsl:value-of select="translate(if (@inkscape:label) then @inkscape:label else @id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@transform"/>
            <xsl:text>
</xsl:text>
            <xsl:text>id</xsl:text>
            <xsl:value-of select="translate(if (@inkscape:label) then @inkscape:label else @id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="translate(if (@inkscape:label) then @inkscape:label else @id, ' -', '__')"/>
            <xsl:text>
</xsl:text> 
    </xsl:template>
    <xsl:template match="svg:defs/svg:linearGradient">
            <xsl:text>id</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@id"/>
            <xsl:text>
</xsl:text>
            <xsl:text>xlinkHref</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@xlink:href"/>
            <xsl:text>
</xsl:text>
            <xsl:text>x1</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@x1"/>
            <xsl:text>
</xsl:text>
            <xsl:text>y1</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@y1"/>
            <xsl:text>
</xsl:text>
            <xsl:text>x2</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@x2"/>
            <xsl:text>
</xsl:text>
            <xsl:text>y2</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@y2"/>
            <xsl:text>
</xsl:text>
            <xsl:text>gradientTransform</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@gradientTransform"/>
            <xsl:text>
</xsl:text>
            <xsl:text>gradientUnits</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@gradientUnits"/>
            <xsl:if test="not(@gradientUnits)">
              <xsl:text>objectBoundingBox</xsl:text>
            </xsl:if>
            <xsl:text>
</xsl:text>
            <xsl:apply-templates select="svg:stop"/>
    </xsl:template>
    <xsl:template match="svg:defs/svg:linearGradient/svg:stop">                
            <xsl:text>style</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@style"/>
            <xsl:text>
</xsl:text>
            <xsl:text>offset</xsl:text>
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>=</xsl:text>
            <xsl:value-of select="@offset"/>
            <xsl:text>
</xsl:text>
    </xsl:template>
</xsl:stylesheet>
