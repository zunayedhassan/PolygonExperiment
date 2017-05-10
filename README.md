# Polygon Experiment
My experiment on various 2D shapes

## Object to Polygon Converter

This program converts various shapes (such as Rectangle, Circle, Ellipse, Rounded Rectangle) and Curves (such as Bézier Cubic curve and Bézier Quadratic curve) to paths

To get about how things done, please visit ```PolygonExperiment/PolygonConverter/src/zunayedhassan/PolygonConverterExperiment/Model/ShapeToPolygonConverter.java``` file

![Object to polygon converter preview](https://raw.githubusercontent.com/zunayedhassan/PolygonExperiment/master/PolygonConverter/preview.png)

## Vertex Sorting
Sorts randomly positioned vertices to clockwise or counter clockwise order

The algorithm is in ```PolygonExperiment/VertexSorting/src/zunayedhassan/VertexSorting/Model/VertexSortingHelper.java``` file

![Vertex Sorting preview](https://raw.githubusercontent.com/zunayedhassan/PolygonExperiment/master/VertexSorting/preview.png)

## Lasso Tool Example
Implementation of Lasso Tool on JavaFX

### Usage ###
Just look at the ```FreeHandSelector.java``` file and you will fugure this out.

![Lasso tool preview](https://raw.githubusercontent.com/zunayedhassan/PolygonExperiment/master/LassoTool/preview.jpg)

## Find Point within Polygon
Find out if the given point is within the polygon

## Note ##
Algorithm is from:
* PNPOLY - Point Inclusion in Polygon Test W. Randolph Franklin (WRF) 
* http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html

![Find Point within Polygon preview](https://github.com/zunayedhassan/PolygonExperiment/blob/master/FindPointWithinPolygon/preview.png?raw=true)

## Line Intersection

Find out if the two line is intersected or not

![Line Intersection preview](https://github.com/zunayedhassan/PolygonExperiment/blob/master/LineIntersection/preview.png)

## Polygon Union

Unify two or more polygons into one single polygon

Use ```PolygonExperiment/PolygonUnion/src/zunayedhassan/PolygonUnion/Model/PolygonUnionHelper.java``` file to unify polygons

![Polygon Union preview](https://raw.githubusercontent.com/zunayedhassan/PolygonExperiment/master/PolygonUnion/preview.png)

## Check the Point Position on Polygon Border

Use ```PolygonExperiment/CheckPointPositionOnPolygonBorder/src/zunayedhassan/CheckPointPositionOnPolygonBorder/Model/PolygonBorderPointsFinder.java``` file to find point on polygon border

![Check the Point Position on Polygon Border preview](https://raw.githubusercontent.com/zunayedhassan/PolygonExperiment/master/CheckPointPositionOnPolygonBorder/preview.png)
