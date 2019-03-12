public class square
{
    int xCoordinate;
    int yCoordinate;
    int numberSquare;
    int baseColor;
    int isSelected = 0;
    int piece;
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
     * sets the baseColor of the tile
     *
     */
    public void setColor(int c)
    {
        if(c == 0)
        {
            baseColor = 0;
        }
        if(c == 1)
        {
            baseColor = 1;
            piece = 5;
        }
    }
    /*
     *
     * returns the base color of the square
     *
     */
    public int getColor()
    {
        return baseColor;
    }
    /*
     *
     * sets the x coordinate of the square
     *
     */
    public void setX(int x)
    {
        xCoordinate = x;
    }
    /*
     *
     * sets the y coordinate of the square
     *
     */
    public void setY(int y)
    {
        yCoordinate = y;
    }
    /*
     *
     * returns the value of the x coordinate of tile
     *
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
    /*
     *
     *
     *
     */
    public void setPiece(int n)
    {
        if(n == 0)              // no piece
        {
            piece = 0;
        }
        else if(n == 1)         // white piece
        {
            piece = 1;

        }
        else if(n == 2)         // red piece
        {
            piece = 2;

        }
        else if(n == 3)         // white king piece
        {
            piece = 3;
        }
        else if(n == 4)         // red king piece
        {
            piece = 4;
        }
        else if(n == 5)
        {
            piece = 5;           // black tile can have no pieces
        }
    }
    /*
     *
     * returns the value of the current piece that the square has
     *
     */
    public int whatPiece()
    {
        return piece;
    }
    /*
     *
     * Moves the piece from current square to the square provided
     *
     */
    public void moveTo(square that)
    {
        if(that.whatPiece() == 0)
        {
            that.setPiece(piece);
            piece = 0;
            System.out.printf("square %d has been moved to %d\n",numberSquare,that.getNumber());
        }
    }
}
