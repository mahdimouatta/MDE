# MDE : Model Driven engineering 
## Project description
the global schema of the project
![Project Description](/images/ProjectDescription.png)

The Local Controller is represented with plantUML.

![LC](images/LC.png)
the the LC is transformed to a Local Aggregated Controller LAC.
the LAC is also represented with plantUML.
![LAC](images/LAC.png)
xtext is used to create a DSL to express the global constraints GC.
and then we make another model to model transformation to a Distributed controlled based on the GC and the LAC.
![DC](images/DC.png)
in the DC we used '<' and '>' instead of '(' and ')' because the plantUML doesn't accept parenthesis inside parenthesis.

the another the DC is transformed to a Grafcet.
![DC](images/Grafcet.png)
## Illustration of the DC synthesis for elevator subsystem

![Illustration of the DC synthesis for elevator subsystem](images/LCtoDC.png)
