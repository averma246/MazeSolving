// @basic starter code author: Kristy Gardner
// @primary author: Ana Verma

public class Graph {

    int numNodes;
    Node[] nodes;

    public void addEdge(int i, int j) {
    	if(i < numNodes && j < numNodes){
	    	Node node1 = nodes[i];
	    	Node node2 = nodes[j];

	    	node1.addNeighbor(node2);
	    	node2.addNeighbor(node1);
	    }

		return;
    }

    public boolean edgeExists(Node a, Node b){
    	return a.neighborExists(b);
    }

    public Graph(int num) {
		numNodes = num;
		nodes = new Node[numNodes];
		for(int i = 0; i < numNodes; i++) {
		    nodes[i] = new Node(i);
		}

		// you might also want to do other things here
    }



}








