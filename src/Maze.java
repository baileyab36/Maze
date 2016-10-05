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
        skipPicker();

        for (int i = 0; i < tileNum; i++) {
            for (int j = 0; j < tileNum; j++) {
                System.out.println(map[i][j]);
            }
        }
    }


    //prints out whatever is given by the map.
    //map[x][y] == 1 for a block at that coordinate
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


}
