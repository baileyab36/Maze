import javax.swing.*;


//trial program to try to make a maze

public class Driver extends JApplet{
    public void init() {
        int tileSize = 100;
        int tileNum = 5;
        Maze maze = new Maze(tileNum, tileSize);
        getContentPane().add(maze);
        setSize(tileNum*tileSize, tileNum*tileSize);

    }
}
