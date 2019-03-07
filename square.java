public class square
{
    int xCoordinate;
    int yCoordinate;
    int numberSquare;
    int baseColor;
    int isSelected = 0;
    /*
     *
     * Constructor
     *
     */
    public square(int n)
    {
        numberSquare = n;
    }
    /*
     *
     * Debugging purpouses: each square says its own name
     *
     */
    public void myName()
    {
        System.out.printf("Hi I am Square %d, My X is %d, My Y is %d\n",numberSquare,xCoordinate,yCoordinate);
    }
    /*
     *
     * sets the baseColor of the tile
     *
     */
    public void setColor(int c)
    {
        if(c == 0)
        {
            System.out.printf("square %d is now white\n",numberSquare);
            baseColor = 0;
        }
        if(c == 1)
        {
            System.out.printf("square %d is now black\n",numberSquare);
            baseColor = 1;
        }
    }
    public int getColor()
    {
        return baseColor;
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
    /*
     * sets the
     */
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
     *
     * returns the square number
     *
     */
    public int getNumber()
    {
        return numberSquare;
    }
    /*
     *
     *
     *
     */
    public void changeSelect()
    {
        if(isSelected == 1)
        {
            isSelected = 0;
        }
        else if(isSelected == 0)
        {
            isSelected = 1;
        }
    }
    /*
     *
     *
     *
     */
    public int isSelected()
    {
        if(isSelected == 0)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}
