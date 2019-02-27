public class square
{
    int xPos;
    int yPos;
    int numberSquare;
    int color;
    
    /*
     constructor
    */
    public square(int x, int y, int n,int c)
    {
        xPos = x;
        ypos = y;
        numberSquare = n;
        color = c;
    }
    /*
     sets the color of the tile
     */
    void changeColor()
    {
        if(color == 0)
        {
            System.out.printf("i am now white\n");
        }
        if(color == 1)
        {
            System.out.printf("i am now black\n");
        }
    }
    /*
     returns the value of the xPosition of tile
     */
    void getXPos()
    {
        return xPos;
    }
    /*
     returns the value of the yPosition of tile
     */
    void getYPos()
    {
        return yPos;
    }
    /*
     returns the square number
     */
    void getNumber()
    {
        return number;
    }
}
