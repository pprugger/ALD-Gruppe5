package A08_GraphZusammenhaengend;

import basisAlgorithmen.Graph;
import basisAlgorithmen.WeightedEdge;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class ConnectedComponents {

    /**
     * Retourniert die Anzahl der zusammenh�ngenden Komponenten eines Graphen
     *
     * @param g zu pr�fender Graph
     * @return Anzahl der Komponenten
     */
    public int getNumberOfComponents(Graph g) {
        return getNumberOfComponents(g,0);
    }

    public int getNumberOfComponents(Graph g, int Startvertex) {
        //Initialisierung eines Stacks für das speichern der verbundenen Vertices(Knoten)
        Stack<Integer> verticesstack = new Stack<>();
        //Initialisierung eines Set zum zählen der verknüpften Knoten
        Set<Integer> verticesset = new HashSet<>();
        int containdComponents = 1;
        //Startknoten wird eingefügt
        verticesstack.add(Startvertex);
        verticesset.add(Startvertex);
        /*Slange der Stack der in der while gefüllt wird nicht leer ist werden die folgenden operationen durchgeführt:
        jeder knoten hat im package basisalgorithmen gewichtete Kanten(weightedEdges), daher iterieren wir mit der FOR-Schleife durch dies durch.
        Dann fügen wir alle Knoten die mit unseren aktullen Knoten verbunden sind(to_vertex) zu unserem Stack und in unser Set!!!(könnten in dem Fall auch die Kanten sein.B
        Da bin ich mir nicht ganz sicher. Der Stack ist in dem Fall nur eine Hilfsdatenstruktur um die verknüpften Knoten rauszukriegen)
        */
        while (!verticesstack.empty()){
            List<WeightedEdge> weightedEdges = g.getEdges(verticesstack.pop());
            for(WeightedEdge we: weightedEdges){
                if (verticesset.add(we.to_vertex))
                verticesstack.add(we.to_vertex);
            }
        }
        /* Jetzt iterieren wir durch unseren Graphen g den wir übergeben haben rekursiv durch solange bis ein Knoten nicht mehr im Set das wir vorher befüllt haben durch,
        und zählen hoch. Damit wir wirklich alle verknüpften Knoten haben müssen wir rekursiv wieder in den gleichen Graphen und den aktuellen Knoten wieder übergeben.
         */
        for (int i = Startvertex; i < g.numVertices() ; i++) {
            if(!verticesset.contains(i)){
                containdComponents += getNumberOfComponents(g,i);
                break;
            }
        }
        return containdComponents;
    }
}
