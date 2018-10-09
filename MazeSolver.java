// @starter code author: Kristy Gardner
// @author of solve(): Ana Verma

/*---------------------------------------------------------------------------
ANSWER THE QUESTIONS FROM THE DOCUMENT HERE

(1) Which graph representation did you choose, and why?
	- Adjacency List -- the graph is rather sparse 
	- space taken up by the adjacency matrix would be O(n^2) regardless of the connectivity of the graph,
		but with the list, only the nodes that have neighbors hold onto other nodes -- in this case a node cannot have
		more than four other neighbors, so the max number of nodes a node would hold onto is 4 (per node) -- running through
		a list with max four elements should not take too long (constant time)

	- in terms of time, an adjacency matrix would probably be just as good or slightly since look up time in a matrix is constant
		--we could check whether an edge exists between two nodes in constant time (we would know all the nodes (or rather the 
		the indeces) a certain node could be directly adjacent to: n-size, n+size, n-1, n+1, where n is the index of the current node
		and size is the square root of the number of nodes, and use those indeces to check the matrix)
	- I chose the adjacency matrix because it is both time and space efficient 


(2) Which search algorithm did you choose, and why?
	- in this case the difference between DFS and BFS is not significant: usually BFS is used to find the guaranteed optimal
		path, but here, there is only one path that connects the beginning to the end and so either search would return the same 
		result 
	- I chose DFS for this because the search follows a single path until a dead end is reached, or the end is found 
		if the end is found before all other dead ends, then that path can be returned and the search terminated -- in a breadth first search,
		all nodes as far away from the entrance as the exit, could be checked before the path is returned  


---------------------------------------------------------------------------*/

import java.io.*;
import java.lang.Math;
import java.util.Stack;


public class MazeSolver {

    public void run(String filename) throws IOException {

		// read the input file to extract relevant information about the maze
		String[] readFile = parse(filename);
		int mazeSize = Integer.parseInt(readFile[0]);
		int numNodes = mazeSize*mazeSize;
		String mazeData = readFile[1];

		// construct a maze based on the information read from the file
		Graph mazeGraph = buildGraph(mazeData, numNodes);

		// do something here to solve the maze
		solve(mazeGraph);
		// print out the final maze with the solution path
		printMaze(mazeGraph.nodes, mazeData, mazeSize);
    }


    public Node[] solve(Graph mazeGraph){
       	//this method assumes that the exit is at the very last node, and the entrance is the very first node 

    	//allows to exit loop if the exit has been found
    	boolean endFound = false;

    	//stack holding onto all the nodes that have been found 
    	Stack<Node> stack = new Stack<Node>();

    	Node firstNode = mazeGraph.nodes[0];
    	firstNode.parent = null;

    	stack.push(firstNode);

    	Node finalNode = mazeGraph.nodes[0];


    	while(!stack.empty() && !endFound){
    		Node x = stack.pop();

    		if(!x.visited){

    			x.visited = true;

    			if(x.index == mazeGraph.numNodes - 1){
    				finalNode = x;
    				x.inSolution = true;
    				endFound = true;
    			}

    			else{
	    			//keep track of index in the list of neighbors of x
	    			int pos = 0;

	    			while(pos < x.numNeighbors){
	    				Node toAdd = x.neighbors.get(pos);

	    				if(!toAdd.visited){
		    				toAdd.parent = x;
		    				stack.push(toAdd);
		    			}
		    			
		    			pos++;
	    			}
	    		}

    		}
    	}

    	Stack<Node> path = new Stack<Node>();
    	int pathSize = 0;

    	while(finalNode != null){
    		path.push(finalNode);
    		finalNode.inSolution = true;
    		pathSize++;

    		finalNode = finalNode.parent;
    	}

    	Node[] toReturn = new Node[pathSize];

    	for(int i = 0; i < pathSize; i++){
    		toReturn[i] = path.pop();
    	}

    	return toReturn;

    }





    // prints out the maze in the format used for HW8
    // includes the final path from entrance to exit, if one has been recorded,
    // and which cells have been visited, if this has been recorded
    public void printMaze(Node[] mazeCells, String mazeData, int mazeSize) {
	
		int ind = 0;
		int inputCtr = 0;

		System.out.print("+");
		for(int i = 0; i < mazeSize; i++) {
		    System.out.print("--+");
		}
		System.out.println();

		for(int i = 0; i < mazeSize; i++) {
		    if(i == 0) System.out.print(" ");
		    else System.out.print("|");

		    for(int j = 0; j < mazeSize; j++) {
			System.out.print(mazeCells[ind] + "" + mazeCells[ind] +  mazeData.charAt(inputCtr));
			inputCtr++;
			ind++;
		    }
		    System.out.println();

		    System.out.print("+");
		    for(int j = 0; j < mazeSize; j++) {
			System.out.print(mazeData.charAt(inputCtr) + "" +  mazeData.charAt(inputCtr) + "+");
			inputCtr++;
		    }
		    System.out.println();
		}
	
    }

    // reads in a maze from an appropriately formatted file (this matches the
    // format of the mazes you generated in HW8)
    // returns an array of Strings, where position 0 stores the size of the maze
    // grid (i.e., the length/width of the grid) and position 1 stores minimal 
    // information about which walls exist
    public String[] parse(String filename) throws IOException {
		FileReader fr = new FileReader(filename);

		// determine size of maze
		int size = 0;
		int nextChar = fr.read();
		while(nextChar >= 48 && nextChar <= 57) {
		    size = 10*size + nextChar - 48;
		    nextChar = fr.read();
		}

		String[] result = new String[2];
		result[0] = size + "";
		result[1] = "";


		// skip over up walls on first row
		for(int j = 0; j < size; j++) {
		    fr.read();
		    fr.read();
		    fr.read();
		}
		fr.read();
		fr.read();

		for(int i = 0; i < size; i++) {
		    // skip over left wall on each row
		    fr.read();
		    
		    for(int j = 0; j < size; j++) {
			// skip over two spaces for the cell
			fr.read();
			fr.read();

			// read wall character
			nextChar = fr.read();
			result[1] = result[1] + (char)nextChar;

		    }
		    // clear newline character at the end of the row
		    fr.read();
		    
		    // read down walls on next row of input file
		    for(int j = 0; j < size; j++)  {
			// skip over corner
			fr.read();
			
			//skip over next space, then handle wall
			fr.read();
			nextChar = fr.read();
			result[1] = result[1] + (char)nextChar;
		    }

		    // clear last wall and newline character at the end of the row
		    fr.read();
		    fr.read();
		    
		}

		return result;
    }
    
    public Graph buildGraph(String maze, int numNodes) {

		Graph mazeGraph = new Graph(numNodes);
		int size = (int)Math.sqrt(numNodes);

		int mazeInd = 0;
		for(int i = 0; i < size; i++) {
		    // create edges for right walls in row i
		    for(int j = 0; j < size; j++) {
				char nextChar = maze.charAt(mazeInd);
				mazeInd++;
				if(nextChar == ' ') {
				    // add an edge corresponding to a right wall, using the 
				    // indexing convention for nodes
				    mazeGraph.addEdge(size*i + j, size*i + j + 1);
				}
		    }

		    // create edges for down walls below row i
		    for(int j = 0; j < size; j++)  {
				char nextChar = maze.charAt(mazeInd);
				mazeInd++;
				if(nextChar == ' ') {
				    // add an edge corresponding to a down wall, using the 
				    // indexing convention for nodes
				    mazeGraph.addEdge(size*i + j, size*(i+1) + j);
				}
		    }    
		}

		return mazeGraph;
    }
       
    public static void main(String [] args) {
		if(args.length < 1) {
		    System.out.println("USAGE: java MazeSolver <filename>");
		}
		else{
		    try{
				new MazeSolver().run(args[0]);
		    }
		    catch(IOException e) {
				e.printStackTrace();
		    }
		}
    }

}
