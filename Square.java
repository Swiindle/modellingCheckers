public class Square
{
    /* Will add this later, defined as e.g "Square.Definitions.WHITE"
    public enum Definitions
    {
        WHITE,
        RED,
        EMPTY
    };*/
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
    public Square(int n)
    {
        numberSquare = n;
    }
    /*
     *
     * Constructor
     *
     */
    public Square(int n, int p)
    {
        numberSquare = n;
        piece = p;
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
    public int getX()
    {
        return xCoordinate;
    }
    /*
     * returns the value of the y coordinate of tile
     */
    public int getY()
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
    public int getSelected()
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
        else if(n == 5)         // yellow selected tile
        {
            piece = 5;
        }
        else                    // black tile can have no pieces
        {
            piece = 6;
        }
    }
    /*
     *
     * returns the value of the current piece that the square has
     *
     */
    public int getPiece()
    {
        return piece;
    }
    /*
     *
     * Moves the piece from current square to the square provided
     *
     */
    public void moveTo(Square that)
    {
        that.setPiece(piece);
        piece = 0;
    }
    public Boolean canMoveTo(Square that)
    {
        if(piece == 1) // if a white piece,
        {
            if(that.getPiece() == 6)
            {
                //System.out.println("you can't move to black pieces");
                return false;
            }
            else if(that.getPiece() != 0 && that.getPiece() != 5)
            {
                return false;
            }
            else if(that.getY() > yCoordinate)
            {
                //System.out.println("you can't move backwards");
                return false;
            }
            else if(that.getY() != yCoordinate - 1)
            {
                //System.out.println("you can only move 1 step forward!");
                return false;
            }
            else if((xCoordinate == 7 && that.getX() != 6) || (xCoordinate == 0 && that.getX() != 1))
            {
                //System.out.println("you can't move, special edge case");
                return false;
            }
            else if(that.getX() != xCoordinate - 1 && that.getX() != xCoordinate + 1)
            {
                //System.out.println("you can't move that far left");
                return false;
            }
            else
            {
                //System.out.println("can move");
                return true;
            }
        }
        else if(piece == 2) // if a red piece,
        {
            if(that.getPiece() == 6)
            {
                //System.out.println("you can't move to black pieces");
                return false;
            }
            else if(that.getPiece() != 0 && that.getPiece() != 5)
            {
                return false;
            }
            else if(that.getY() < yCoordinate)
            {
                //System.out.println("you can't move backwards");
                return false;
            }
            else if(that.getY() != yCoordinate + 1)
            {
                //System.out.println("you can only move 1 step forward!");
                return false;
            }
            else if((xCoordinate == 7 && that.getX() != 6) || (xCoordinate == 0 && that.getX() != 1))
            {
                //System.out.println("you can't move, special edge case");
                return false;
            }
            else if(that.getX() != xCoordinate - 1 && that.getX() != xCoordinate + 1)
            {
                //System.out.println("you can't that far left");
                return false;
            }
            else
            {
                //System.out.println("can move");
                return true;
            }
        }
        System.out.println("should not get here");
        return false;
    }
    /*
     *
     *
     * Function is here for debuggin purpouses
     *
     */
    public void sayName()
    {
        System.out.printf("Hi, I am square %d, I have %d piece, my X is %d, my Y is %d\n",numberSquare,piece,xCoordinate,yCoordinate);
    }
}
