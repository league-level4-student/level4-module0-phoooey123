package extra;

import java.util.HashMap;

//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....


public class TheWrongWayCow {

  public static int[] findWrongWayCow(final char[][] field) {
      // Fill in the code to return the x,y coordinate position of the
      // head (letter 'c') of the wrong way cow!
	  int east = 0;
	  int north = 0;
	  int west = 0;
	  int south = 0;
	 HashMap<String, int[]>  coords = new HashMap<String, int[]>();
      for (int y = 0; y < field.length; y++) {
		for (int x = 0; x < field[y].length; x++) {
			if(field[y][x] == 'c') {
				if(!((y+1 == field.length) || (y+2 == field.length)) && field[y+1][x] == 'o' && field[y+2][x] == 'w') {
					north++;
					int[] w = {x , y};
					coords.put("north", w);
				}
				else if(x <= field[y].length - 3 && field[y][x+1] == 'o' && field[y][x+2] == 'w') {
					west++;
					int[] s = {x, y};
					coords.put("west", s);
				}
			}
			else if(field[y][x] == 'w') {
				if(!((y+1 == field.length) || (y+2 == field.length)) && y > 2 && field[y+1][x] == 'o' && field[y+2][x] == 'c') {
					south++;
					int[] e = {x, y+2};
					coords.put("south", e);
				}
				else if(x < field[y].length - 3 && field[y][x+1] == 'o' && field[y][x+2] == 'c') {
					east++;
					int[] n = {x+2, y};
					coords.put("east", n);
				}
			}
			
		}
	}
      System.out.println(east + " east");
      System.out.println(north + " north");
      System.out.println(west + " west");
      System.out.println(south + " south");
      if(east == 1) {
    	  System.out.println(coords.get("east")[0]);
    	  System.out.println(coords.get("east")[1]);
    	  return coords.get("east");
      }
      else if(north == 1) {
    	  System.out.println(coords.get("north")[0]);
    	  System.out.println(coords.get("north")[1]);
    	  return coords.get("north");
      }
      else if(west == 1) {
    	  System.out.println(coords.get("west")[0]);
    	  System.out.println(coords.get("west")[1]);
    	  return coords.get("west");
      }
      else {
    	  System.out.println(coords.get("south")[0]);
    	  System.out.println(coords.get("south")[1]);
    	  return coords.get("south");
      }
  }
}