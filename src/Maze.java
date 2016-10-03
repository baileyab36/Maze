import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by DELL 780 on 10/2/2016.
 */
//FOR THIS ITERATION: picker chooses random sequential areas that do not touch
//puts tiles on a background.

class Maze extends JPanel {
    private int tileSize;
    private int tileNum;
    private Vector<Point> points;

    public Maze(int tileNum, int tileSize) {
        this.tileNum = tileNum;
        this.tileSize = tileSize;
        setBackground(Color.gray);
        setForeground(Color.black);
        points = new Vector<>();
    }

    //if this runs continuously then we can stick picker() in here and let it run sequentially
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        Point p = picker(points);
        drawTile(page, (int)p.getX(), (int)p.getY());
    }

    private void drawTile(Graphics page, int x, int y) {
        page.fillRect(x, y, tileSize, tileSize);
    }


    private Point picker(Vector<Point> points) {
        Point point = new Point();
        if (points.isEmpty()) {
            point.setLocation(0,0);
        } else {
            int x;
            int y;
            int ran;


            point.setLocation(x,y);
        }


        points.add(point);
        return point;
    }
}
