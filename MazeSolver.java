/*
* Name: 	Louis Kedziora 
* ID:		V00820695 
* Date: 	March 5, 2016
* Filename:	MazeSolver.java 
* Details: 	CSC-115\Assignment-3
*/

public class MazeSolver {
	
	/*
	*	findPath
	*	It uses mapMaker to solve the given maze 
	*	when called and returns the path;
	*	@param Maze 
	*	@return A String representation of the the path 
	*	to solve the maze.
	*	if maze is unsolvable then returns an empty string "".
	*/
    public static String findPath(Maze maze) {
		
		StringBuffer sb = new StringBuffer();
		int mazeRows = maze.getNumRows();
		int mazeCols = maze.getNumCols();
		MazeLocation[][] mapers = mapMaker(mazeRows, mazeCols, maze);
		
		boolean[][] mapCheck = new boolean[mazeRows][mazeCols];
		Stack<MazeLocation> stackers = new StackRefBased<MazeLocation>();
		MazeLocation start = mzLocMaker(maze.getEntry().getRow(), maze.getEntry().getCol(), maze);
		MazeLocation end = mzLocMaker(maze.getExit().getRow(), maze.getExit().getCol(), maze);
		
		mapCheck[start.getRow()][start.getCol()] = true;
		MazeLocation current;
		stackers.push(start);
		int cRow;
		int cCol;
		try {
				while ((stackers.isEmpty() == false) && stackers.peek().equals(end) == false) {
					current = stackers.peek();
					cRow = current.getRow();
					cCol = current.getCol();
					mapCheck[cRow][cCol] = true;
					if ((mapers[cRow + 1][cCol].isWall()) == false && mapCheck[cRow + 1][cCol] == false) {
						stackers.push(mapers[cRow + 1][cCol]);
					} else if ((mapers[cRow][cCol + 1].isWall()) == false && mapCheck[cRow][cCol + 1] == false) {
						stackers.push(mapers[cRow][cCol + 1]);
					} else if ((mapers[cRow][cCol - 1].isWall()) == false && mapCheck[cRow][cCol - 1] == false) {
						stackers.push(mapers[cRow][cCol - 1]);
					} else if ((mapers[cRow - 1][cCol].isWall()) == false && mapCheck[cRow - 1][cCol] == false) {
						stackers.push(mapers[cRow - 1][cCol]);
					} else {
						stackers.pop();
					}
				}
				if (stackers.isEmpty()) {
					return "";
				} else {
					while (stackers.size() != 1) {
						sb.insert(0, stackers.pop().toString());
						sb.insert(0, " ");
					}
					sb.insert(0, stackers.pop().toString());
					return sb.toString();
				}
				
			} catch (StackEmptyException et) {
				System.out.println("BROKE CODE");
				
			} catch (ArrayIndexOutOfBoundsException ex) {
				return "";
			}
		return "";
    }
	
	/*
	*	mapMaker
	*	Converts a maze into a 2D array of MazeLocations
	*	@param int rows, int cols, Maze mz
	*	@return 2d array of MazeLocations
	*	This is a helper method to help findPath
	* 	check what squares are walls
	*/
	private static MazeLocation[][] mapMaker(int rows, int cols, Maze mz) {
		MazeLocation[][] tmp = new MazeLocation[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int v = 0; v < cols; v++) {
				tmp[i][v] = mzLocMaker(i, v, mz);
			}
		}
		return tmp;
	}
	
	/*
	*	mzLocMaker
	*	Converts maze locations into MazeLocations
	*	@param int row, int col, Maze mz
	*	@return MazeLocation
	*	This is a helper method to help mapMaker
	* 	make MazeLocations
	*/
	private static MazeLocation mzLocMaker(int row, int col, Maze mz) {
		boolean wall = mz.isWall(row,col);
		MazeLocation tmp = new MazeLocation(row, col, wall);
		return tmp;
	}
}

