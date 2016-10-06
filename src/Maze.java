import javax.swing.*;
import java.awt.*;


//FOR THIS ITERATION: picker chooses random sequential areas that do not touch
//puts tiles on a background.

public class Maze extends JApplet {
    private int tileSize = 50;
    private int tileNum = 5;
    private int[][] map;

    public void init() {
        getContentPane().setBackground(Color.gray);
        setSize(tileSize * tileNum, tileSize * tileNum);

        map = new int[tileNum][tileNum];
        for (int i = 0; i < tileNum; i++) {
            for (int j = 0; j < tileNum; j++) {
                map[i][j] = 0;
            }
        }
        notTouchingRandomPicker();

        /* for printing the map array
        for (int i = 0; i < tileNum; i++) {
            for (int j = 0; j < tileNum; j++) {
                System.out.print("map[" + i + "][" + j + "] = " + map[i][j] + "\n");
            }
        }*/
    }


    //prints out whatever is given by the map.
    //map[x][y] == 1 for a block at that coordinate
    //Works great!
    public void paint(Graphics page) {
        super.paint(page);
        setForeground(Color.black);
        setBackground(Color.gray);

        for (int i = 0; i < tileNum; i++) {
            for (int j = 0; j < tileNum; j++) {
                if (map[i][j] == 1)
                    page.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
            }
        }
    }

    //WORKS!
    //picks random spots to put blocks in
    private void randomPicker() {
        int ran;

        for (int i = 0; i < tileNum; i++) {
            for (int j = 0; j < tileNum; j++) {
                ran = (int) (Math.random() * 3);
                if (ran == 1) {
                    map[i][j] = 1;
                }
            }
        }
    }


    //WORKS
    //skips a spot every time
    private void skipPicker() {

        for (int i = 0; i < tileNum; i++) {
            for (int j = 0; j < tileNum; j++) {
                //if even
                if ((i * tileNum + j) % 2 == 0) {
                    map[i][j] = 1;
                }
            }
        }
    }


    //Picks spots that are not touching each other
    private void notTouchingRandomPicker() {
        boolean north, south, east, west;
        boolean canContinue = true;
        int numDirections = 2;

        int x = 0;
        int y = 0;
        int lastTile = tileNum - 1;


        while (canContinue) {
            // remake ran every time

            int ran = (int) (Math.random() * 4);
            System.out.println("ran = " + ran);

            //if its false you cant go there
            north = false;
            south = false;
            west = false;
            east = false;

            //make new ints for my own sake
            int northY = y - 1;
            int southY = y + 1;
            int westX = x - 1;
            int eastX = x + 1;


            //is it open
            //first parameter = not on border
            //second parameter = the next tile is free
            //all tiles around the next tile are free

            //check all around northY
            if (y != 0 && map[x][northY] == 0) {
                if (x == 0 || map[westX][northY] == 0) {
                    if (x == lastTile || map[eastX][northY] == 0)
                        north = true;
                }
            }

            //check all around southY
            if (y != lastTile && map[x][southY] == 0) {
                if (x == 0 || map[westX][southY] == 0) {
                    if (x == lastTile || map[eastX][southY] == 0)
                        south = true;
                }
            }

            //check all around westX
            if (x != 0 && map[westX][y] == 0) {
                if (y == 0 || map[westX][northY] == 0) {
                    if (y == lastTile || map[westX][southY] == 0)
                        west = true;
                }
            }

            //check all around eastX
            if (x != lastTile && map[eastX][y] == 0) {
                if (y == 0 || map[eastX][northY] == 0) {
                    if (y == lastTile || map[eastX][southY] == 0)
                        east = true;
                }
            }

            printDirections(north, south, east, west);


            //making map based on directions available

            //if its the first time around x and y will be zero.
            //we always start in the top left corner
            if (x == 0 && y == 0 && map[x][y] == 0) {
                System.out.println("First time through loop");
                map[x][y] = 1;
                //randomly choose right or down for moving
                //must be changed here or it wont work.
                /*if (ran < 2) {
                    x++;
                    north = false;
                } else {
                    y++;
                    east = false;
                }*/


            } else {

                //once numDirections has been pared down to one direction, the loop ends
                while (numDirections > 1) {
                    print("choosing directions");


                    //randomly choose an available direction and make the others false
                    //loop should run a maximum of three times
                    switch (ran) {
                        case 0:
                            if (north) {
                                south = false;
                                west = false;
                                east = false;
                            }
                            break;
                        case 1:
                            if (south) {
                                north = false;
                                west = false;
                                east = false;
                            }
                            break;
                        case 2:
                            if (west) {
                                north = false;
                                south = false;
                                east = false;
                            }
                            break;
                        case 3:
                            if (east) {
                                north = false;
                                south = false;
                                west = false;
                            }
                            break;
                    }
                    printDirections(north, south, east, west);


                    //if ran chose a direction that was already false it will move on to the next one until it finds the one that is true.
                    ran++;
                    if (ran == 4) {
                        ran = 0;
                    }

                    //update direction count
                    numDirections = calculateNumDirections(north, south, west, east);
                    print("numDirections = " + numDirections + "\n");

                }

                //only one direction is now true
                //put tiles in map as long as you can
                if (north) {
                    y = northY;
                } else if (south) {
                    y = southY;
                } else if (west) {
                    x = westX;
                } else if (east) {
                    x = eastX;
                }

                map[x][y] = 1;
            }


            //will be either one or zero
            //this determines whether the application is killed.
            numDirections = calculateNumDirections(north, south, west, east);


            if (numDirections == 0)
                canContinue = false;

            chosenDirection(north, south, east, west);
            print("x = " + x + "  y= " + y);
            print("\nstill continuing:" + canContinue + "\n\n");
        }
    }


    private int calculateNumDirections(boolean north, boolean south, boolean west, boolean east) {
        int numDirections = 0;
        if (north) {
            numDirections++;
        }
        if (south) {
            numDirections++;
        }
        if (west) {
            numDirections++;
        }
        if (east) {
            numDirections++;
        }

        return numDirections;
    }

    private void printDirections(boolean north, boolean south, boolean east, boolean west) {
        System.out.println("north: " + north + "\t\tsouth: " + south + "\t\twest: " + west + "\t\teast: " + east);
    }

    private void chosenDirection(boolean north, boolean south, boolean east, boolean west) {
        //only works if one is true
        print("Direction chosen: ");
        if (north)
            print("north");
        if (south)
            print("South");
        if (west)
            print("west");
        if (east)
            print("east");
        print("\n");
    }

    private void print(String printString) {
        System.out.print(printString);
    }
}
