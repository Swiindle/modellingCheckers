/**
* This class contains all the necessary data for each square on the board.
* Works closely with JButton to make the game work.
* The square class contains all some permenant data and data that can be changed.
*/
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
    /**
     * Constructor. Creates an instance of the square class.
     *
     * @param n gives the square the number of n
     * @param p gives the square the piece of p
     *
     */
    public Square(int n, int p)
    {
        numberSquare = n;
        piece = p;
    }
    /**
     * Returns the number of the square.
     */
    public int getNumber()
    {
        return numberSquare;
    }
    /**
     * Sets the original color of the square. Should be called only once. Permenant data.
     *
     * This method should be used to set the square to be either white (baseColor = 0)
     * or black ( baseColor = 1)
     * @param c sets the base color of this square
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
    /**
     * Returns the original color of this square.
     * @return the Base Color of the square
     */
    public int getColor()
    {
        return baseColor;
    }
    /**
     * Sets the X coordinate of the square to the given x parameter.
     * Should only be called once.
     * Permenant data.
     * @param x sets the X coordinate to this value
     */
    public void setX(int x)
    {
        xCoordinate = x;
    }
    /**
     * Sets the Y coordinate of the square to the given x parameter.
     * Should only be called once.
     * Permenant data.
     * @param y sets the Y coordinate to this value
     */
    public void setY(int y)
    {
        yCoordinate = y;
    }
    /**
     * Returns the X coordinate of this square.
     * @return the X coordinate of the square on the board.
     */
    public int getX()
    {
        return xCoordinate;
    }
    /**
     * Returns the Y coordinate of this square.
     * @return the Y coordinate of the square on the board.
     */
    public int getY()
    {
        return yCoordinate;
    }
    /**
     * Toggles whether this square has been selected by the user or not
     *
     * If this square has been selected and this method is called, this square is now unselected.
     * If this square is not selected and this method is called, this square is now selected.
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
    /**
     * Returns whether this square is selected or not
     * @return whether this piece has been selected or not
     */
    public int getSelected() // should this be a boolean type?
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
    /**
     * Gives this square a piece.
     *
     * 0 means no piece. 1 means white piece. 2 means red piece. 3 means white king. 4 means red king.
     * 5 means this piece has a yellow square. 6 means that this piece is black and should never have a piece.
     * @param n the new piece that this square has
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
    /**
     * Returns the type of piece this square has.
     * @return the piece type
     */
    public int getPiece()
    {
        return piece;
    }
    /**
     * This method moves the piece in this square to the square specified.
     * @param that the square that the piece should be moved to
     */
    public void moveTo(Square that)
    {
        that.setPiece(piece);
        piece = 0;
    }
    /**
     * This method dictates whether a move to the given square is valid. It is based on the rules of checkers.
     *
     * @return true of false depending whether a move is valid or not.
     */
    public Boolean canMoveTo(Square that)
    {
        if(piece == 1) // if a white piece,
        {
            if(that.getPiece() == 6)
            {
                return false;
            }
            else if(that.getPiece() != 0 && that.getPiece() != 5)
            {
                return false;
            }
            else if(that.getY() > yCoordinate)
            {
                return false;
            }
            else if(that.getY() != yCoordinate - 1)
            {
                return false;
            }
            else if((xCoordinate == 7 && that.getX() != 6) || (xCoordinate == 0 && that.getX() != 1))
            {
                return false;
            }
            else if(that.getX() != xCoordinate - 1 && that.getX() != xCoordinate + 1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if(piece == 2) // if a red piece,
        {
            if(that.getPiece() == 6)
            {
                return false;
            }
            else if(that.getPiece() != 0 && that.getPiece() !=5)
            {
                return false;
            }
            else if(that.getY() < yCoordinate)
            {
                return false;
            }
            else if(that.getY() != yCoordinate + 1)
            {
                return false;
            }
            else if((xCoordinate == 7 && that.getX() != 6) || (xCoordinate == 0 && that.getX() != 1))
            {
                return false;
            }
            else if(that.getX() != xCoordinate - 1 && that.getX() != xCoordinate + 1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        System.out.println("should not get here");
        return false;
    }
    /**
     * This method prints out some details about the square, most useful for debugging.
     */
    public void sayName()
    {
        System.out.printf("Hi, I am square %d, I have %d piece, my X is %d, my Y is %d\n",numberSquare,piece,xCoordinate,yCoordinate);
    }
}
