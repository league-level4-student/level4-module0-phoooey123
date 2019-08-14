package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	static boolean run = false;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	static boolean hasUnvisited;

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		int x = randGen.nextInt(maze.getWidth());
		int y = randGen.nextInt(maze.getHeight());
		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.getCell(x, y));
		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		maze.cells[currentCell.getX()][currentCell.getY()].setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> sels = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,
		if (sels.size() > 0) {
			// C1. select one at random.
			int selec = randGen.nextInt(sels.size());

			// C2. push it to the stack
			for (int i = 0; i < sels.size(); i++) {
				if (i == selec) {
					uncheckedCells.push(sels.get(i));
					// C3. remove the wall between the two cells
					removeWalls(currentCell, sels.get(i));
					// C4. make the new cell the current cell and mark it as visited
					currentCell = sels.get(i);
					// C5. call the selectNextPath method with the current cell
					selectNextPath(currentCell);
				}
			}
		} else {

			// D. if all neighbors are visited

			// D1. if the stack is not empty
			if (uncheckedCells.size() > 0) {
				// D1a. pop a cell from the stack

				// D1b. make that the current cell
				currentCell = uncheckedCells.pop();
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() - c2.getX() == 1) {
			c2.setEastWall(false);
			c1.setWestWall(false);
		}
		if (c1.getX() - c2.getX() == -1) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		}
		if (c1.getY() - c2.getY() == 1) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}
		if (c1.getY() - c2.getY() == -1) {
			c2.setNorthWall(false);
			c1.setSouthWall(false);
		}
		if (!run) {

			maze.cells[0][randGen.nextInt(maze.cells.length - 1)].setWestWall(false);
			maze.cells[maze.cells.length - 1][randGen.nextInt(maze.cells.length - 1)].setEastWall(false);

			run = true;
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> cells = new ArrayList<Cell>();
		if (c.getY() != 0) {
			if (!maze.cells[c.getX()][c.getY() - 1].hasBeenVisited()) {
				cells.add(maze.cells[c.getX()][c.getY() - 1]);
			}
		}
		if (c.getX() != maze.cells.length - 1) {
			if (!maze.cells[c.getX() + 1][c.getY()].hasBeenVisited()) {
				cells.add(maze.cells[c.getX() + 1][c.getY()]);
			}
		}
		if (c.getY() != maze.cells.length - 1) {
			if (!maze.cells[c.getX()][c.getY() + 1].hasBeenVisited()) {
				cells.add(maze.cells[c.getX()][c.getY() + 1]);
			}
		}
		if (c.getX() != 0) {
			if (!maze.cells[c.getX() - 1][c.getY()].hasBeenVisited()) {
				cells.add(maze.cells[c.getX() - 1][c.getY()]);
			}
		}
		return cells;
	}
}
