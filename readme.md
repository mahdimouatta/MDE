# MDE : Model Driven engineering 
## Project description
the global schema of the project
![Project Description](https://github.com/mmouatta/MDE/blob/master/images/ProjectDescription.PNG)

The Local Controller is represented with plantUML.

![LC](https://github.com/mmouatta/MDE/blob/master/images/LC.PNG)
the the LC is transformed to a Local Aggregated Controller LAC.
the LAC is also represented with plantUML.
![LAC](https://github.com/mmouatta/MDE/blob/master/images/LAC.PNG)
xtext is used to create a DSL to express the global constraints GC.
and then we make another model to model transformation to a Distributed controlled based on the GC and the LAC.
![DC](https://github.com/mmouatta/MDE/blob/master/images/DC.PNG)
in the DC we used '<' and '>' instead of '(' and ')' because the plantUML doesn't accept parenthesis inside parenthesis.

the another the DC is transformed to a Grafcet.
![DC](https://github.com/mmouatta/MDE/blob/master/images/Grafcet.PNG)
## Illustration of the DC synthesis for elevator subsystem

![Illustration of the DC synthesis for elevator subsystem](https://github.com/mmouatta/MDE/blob/master/images/LCtoDC.PNG)
