import javax.swing.*; // #includes JFrame
import java.awt.*; // #includes Java Panels

public class square
{
    int xCoordinate;
    int yCoordinate;
    int numberSquare;
    int color;
    /*
     * constructor
     */
    public square(int n)
    {
        numberSquare = n;
    }
    /*
     * Debugging purpouses: each square says its own name
     */
    public void myName()
    {
        System.out.printf("Hi I am Square %d, My X is %d, My Y is %d\n",numberSquare,xCoordinate,yCoordinate);
    }
    /*
     * sets the color of the tile
     */
    public void changeColor(int color)
    {
        if(color == 0)
        {
            System.out.printf("i am now white\n");
        }
        if(color == 1)
        {
            System.out.printf("i am now black\n");
        }
        if(color == 2)
        {
            System.out.printf("i am now yellow\n");
        }
    }
    /*
     * sets the x value to parameter
     */
    public void setX(int x)
    {
        xCoordinate = x;
    }
    /*
     * sets the y value to parameter
     */
    public void setY(int y)
    {
        yCoordinate = y;
    }
    public void setNumber(int n)
    {
        numberSquare = n;
    }
    /*
     * returns the value of the x coordinate of tile
     */
    public int getxCoordinate()
    {
        return xCoordinate;
    }
    /*
     * returns the value of the y coordinate of tile
     */
    public int getyCoordinate()
    {
        return yCoordinate;
    }
    /*
    * returns the square number
     */
    public int getNumber()
    {
        return numberSquare;
    }
}
