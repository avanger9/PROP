all: class jar

jar: CtDomini.jar DriverNode.jar DriverGraph.jar DriverTauler.jar DriverHidato.jar

class: CtDomini.class Hidato.class Tauler.class Tauler_hexagon.class Tauler_quadrat.class Tauler_quadrat_vertex.class Tauler_triangle.class Tauler_triangle_vertex.class Graph.class Node.class DriverNode.class DriverGraph.class DriverTauler.class

CtDomini.jar: CtDomini.class Hidato.class Tauler.class Tauler_hexagon.class Tauler_quadrat.class Tauler_quadrat_vertex.class Tauler_triangle.class Tauler_triangle_vertex.class Graph.class Node.class ElementNotFoundException.class GetNumException.class ItemExistanceException.class
	jar cfe CtDomini.jar CtDomini CtDomini.class Hidato.class Tauler.class Tauler_hexagon.class Tauler_quadrat.class Tauler_quadrat_vertex.class Tauler_triangle.class Tauler_triangle_vertex.class Graph.class Node*.class ElementNotFoundException.class GetNumException.class ItemExistanceException.class 

DriverNode.jar: DriverNode.class Node.class
	jar -cfe DriverNode.jar DriverNode DriverGraph.class Node.class ElementNotFoundException.class GetNumException.class ItemExistanceException.class 
DriverGraph.jar: DriverGraph.class Graph.class Node.class
	jar -cfe DriverGraph.jar DriverGraph DriverGraph.class Graph.class Node.class ElementNotFoundException.class GetNumException.class ItemExistanceException.class 
DriverTauler.jar: DriverTauler.class Tauler.class Graph.class Node.class
	jar -cfe DriverTauler.jar DriverTauler DriverTauler.class Tauler.class Node.class ElementNotFoundException.class GetNumException.class ItemExistanceException.class 
DriverHidato.jar: DriverHidato.class Hidato.class Graph.class Node.class
	jar -cfe DriverHidato.jar DriverHidato DriverHidato.class Hidato.class Node.class ElementNotFoundException.class GetNumException.class ItemExistanceException.class 


CtDomini.class: CtDomini.java Hidato.class
	javac CtDomini.java
Hidato.class: Hidato.java Tauler.class Node.class
	javac Hidato.java
Tauler.class: Tauler.java Graph.class Node.class
	javac Tauler.java
Tauler_hexagon.class: Tauler_hexagon.java Graph.class Node.class
	javac Tauler_hexagon.java
Tauler_quadrat.class: Tauler_quadrat.java Graph.class Node.class
	javac Tauler_quadrat.java
Tauler_quadrat_vertex.class: Tauler_quadrat_vertex.java Graph.class Node.class
	javac Tauler_quadrat_vertex.java
Tauler_triangle.class: Tauler_triangle.java Graph.class Node.class
	javac Tauler_triangle.java
Tauler_triangle_vertex.class: Tauler_triangle_vertex.java Graph.class Node.class
	javac Tauler_triangle_vertex.java
Graph.class: Graph.java Node.class
	javac Graph.java
Node.class: Node.java
	javac Node.java

DriverNode.class: DriverNode.java Node.class
	javac DriverNode.java
DriverGraph.class: DriverGraph.java Graph.class Node.class
	javac DriverGraph.java
DriverTauler.class: DriverTauler.java Tauler.class Node.class
	javac DriverTauler.java
DriverHidato.class: DriverHidato.java Hidato.class Node.class
	javac DriverHidato.java

clean: cclass cjar
	
cclass:
	rm -f *.class
cjar:
	rm -f *.jar
