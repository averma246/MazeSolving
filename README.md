# MazeSolving
----------------------------------------------------------------------------------------------------------
*Source code from Professor Kristy Gardner, Amherst College*

A program to solve mazes in the style and format of the mazes generated in repository Maze Generation

Node.java: represents a square in the maze grid
* @author: Kristy Garnder
* *Description from assignment:* CONTAINS: (1) an *index* that uniquely identifies it, (2) two booleans *visited* and *inSolution*, (3) method *toString()* used to print solution, (4) constructor

Graph.java: 
* basic starter code from Kristy Gardner
* @primary author: Ana Verma
* used in solving the maze

MazeSolver.java: 
* starter code provided by Kristy Gardner 
* @author of *solve()*: Ana Verma
* *description from assignment:* 
1. Calls the parse() method to read the contents of a file* and extract information about
the size of the maze and the walls in the maze.
2. Calls the buildGraph() method to generate a graph based on the maze information
extracted by parse(). This method calls the addEdge() method in Graph.
3. Calls the printMaze() method to print the maze. Right now if you compile and run
the program an empty maze will print that exactly matches the contents of your input
file. If you update the booleans in each node as you’re solving the maze, it should print
out something more interesting when you’re done solving
