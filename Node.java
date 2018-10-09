// @author: Kristy Gardner

import java.util.LinkedList;

public class Node {

    int index;

    //keep track of the parent of this node in the tree that is created from the search
    Node parent;

    LinkedList<Node> neighbors = new LinkedList<Node>();
    int numNeighbors = 0;

    // to help you keep track of things as you're solving the maze
    boolean visited = false;
    boolean inSolution = false;

    static final String PATH = "X";
    static final String VISIT = ".";
    static final String NOT_VISIT = " ";

    public String toString() {
		if(visited) {
		    if(inSolution) return PATH;
		    else return VISIT;
		}
		else return NOT_VISIT;
    }

    public Node(int i) {
		index = i;
    }

    public void addNeighbor(Node neighbor){
    	neighbors.add(neighbor);
    	numNeighbors++;
    }

    public boolean neighborExists(Node neighbor){
    	return neighbors.contains(neighbor);
    }

    public void printNeighbors(){
    	for(int i = 0; i < numNeighbors; i++){
    		System.out.print(" " + neighbors.get(i) + " ");
    	}
    }

}
