/**
 * This is the Board class. This class is in charge of implementing all types of java swing. And representing a real life checkers board.
 */

import javax.swing.*; // #includes JFrame
import java.awt.*; // #includes Java Panels
import java.awt.event.*; // #includes action listener

public class Board implements ActionListener
{
    /* INSTANCE VARIABLES ARE LISTED BELOW */
    
    private int xDimension;                                         // x dimension of the window
    private int yDimension;                                         // y dimension of the window
    
    //INSTIANTIATE
    
    private JFrame frame = new JFrame("Checkers");                  // frame
    private JPanel panel = new JPanel();                            // panel
    private GridLayout layout = new GridLayout(8,8);                // gridlayout, 8x8
    private JButton[] b = new JButton[64];                          // 64 buttons
    private Square[] s = new Square[64];                            // 64 Squares
    private GameRules gr = new GameRules();                         // The class containing the game rules
        //IMAGES
        private ImageIcon white = new ImageIcon("empty.png");           // white icon
        private ImageIcon black = new ImageIcon("empty2.png");          // black icon
        private ImageIcon yellow = new ImageIcon("selected.png");       // yellow icon
        private ImageIcon redPiece = new ImageIcon("red.png");          // red piece
        private ImageIcon redKing = new ImageIcon("red-king.png");      // redKing
        private ImageIcon whitePiece = new ImageIcon("white.png");      // white piece
        private ImageIcon whiteKing = new ImageIcon("white-king.png");  // whiteKing
    
    /* METHODS ARE LISTED BELOW: */
    
    /**
     * Constructor.
     *
     * @param the X dimension of the window
     * @param the Y dimension of the window
     */
    Board(int x , int y)
    {
        xDimension = x;
        yDimension = y;
    }
    
    /**
     * This method opens and initializes all applicable java swing tools.
     *
     * This method initializes: JFrame, JPanel, JButtons and Action Listener. For clarity, this method continues in the 'makeSquaresAndButtons'
     * method.
     */
    public void open()
    {
        //FRAME
        frame.setSize(xDimension,yDimension);                   // sets the dimensions of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // frame closes when close
        
        //PANEL
        frame.setContentPane(panel);                            // connects frame and panel
        panel.setLayout(layout);                                // connects the panel and the layout
        //BUTTON & Square
        makeSquaresAndButtons();                                // instantiates all squares and buttons using a method
        
        //ACTION LISTENER
        for(int i = 0 ; i < 64 ; i++)
        {
            b[i].addActionListener(this);
        }
        //GO
        frame.setVisible(true);                                 // makes frame visible
    }
    
    /**
     *  This function has been seperated from the open() function for the purpouse of clarity.
     *
     *  This method initializes the square classes and the JButtons. It also links each square class to the respective JButton.
     *  This method uses one main loop to itirate through all buttons and squares, and 2 additional loops to itirate through the x and y coordinates.
     */
    private void makeSquaresAndButtons()
    {
        int y = 0;                                                  // y value
        int x = 0;                                                  // x value
        boolean isBlack = true;                                     // Whether the square should be black or not
        for(int i = 0; i < 64 ; i++)                                // Main loop that goes through each button and square.
        {
            b[i] = new JButton();                                   // INSTANTIATE buttons with a name
            panel.add(b[i]);                                        // Connects each button the the panel
            if(x == 8)                                              // What happens when we reach the end of the the board?
            {                                                       // This also acts as the secondary loop, increment below
                x = 0;
                y++;
                if(isBlack == true)
                {
                    isBlack = false;
                }
                else if(isBlack == false)
                {
                    isBlack = true;
                }
            }
            if(isBlack == true)                                      // Logic for setting the tiles
            {
                s[i] = new Square(i,6);                              // INSTANTIATE Squares, making square black
                s[i].setColor(1);
                changeTile(i,0);
                isBlack = false;
            }
            else
            {
                if(i < 24)                                          // make red peices on white squares less than 24
                {
                    s[i] = new Square(i,2);                         // INSTANTIATE Squares, making square have a red piece
                    s[i].setColor(0);
                    changeTile(i,0);
                    isBlack = true;
                }
                else if(i > 40)                                     // make white pieces on white squares greater than 40
                {
                    s[i] = new Square(i,1);                         // INSTANTIATE Squares, making square white
                    s[i].setColor(0);
                    changeTile(i,0);
                    isBlack = true;
                }
                else                                                // rest will not have pieces
                {
                    s[i] = new Square(i,0);                         // INSTANTIATE Squares, making square empty
                    s[i].setColor(0);
                    changeTile(i,0);
                    isBlack = true;
                }
            }
            s[i].setX(x);                                           // Sets the X coordinate
            s[i].setY(y);                                           // Sets the Y coordinate
            x++;                                                    // Increment the X after the end of the loop
        }
    }
    
    /**
     * This method resets the game.
     *
     * This method makes it easier to restart the game, placing back all the pieces to their original locations and resetting the values in the
     * GameRules class.
     */
    public void resetPieces()
    {
        System.out.printf("WHITE TURN!\n");
        gr.resetScore();
        gr.nextTurn();
        for(int i = 0; i < 64 ; i++)
        {
           if(i < 24)                               // red pieces can be found before the 24th square if the square is white
            {
                if(s[i].getColor() == 0)
                {
                    changeTile(i,2);
                    s[i].setPiece(2);
                }
            }
            if(i > 39)                              // white pieces can be found after the 39th square if the square is white
            {
                if(s[i].getColor() == 0)
                {
                    changeTile(i,1);
                    s[i].setPiece(1);
                }
            }
        }
    }
    
    /**
     * This method is the actionPerformed part of Action Listener.
     *
     * This method decides what happens when a button is pressed. Contains most of the game logic. Is extended with the
     * toggleSelect method and the selectedAndMove method. Read game rules class for more information.
     * @param action - the name of the what happens when a button is pressed
     */
    public void actionPerformed(ActionEvent action)
    {
        for(int i = 0 ; i < 64 ; i++) // itirates through all buttons to see if when a button is pressed, the button pressed is relevant to it.
        {
            if( gr.getWhiteTurn() == true) // White controlled buttons only
            {
                if(action.getSource() == b[i] && gr.getSelectedButton() == 65 && s[i].getPiece() == 1)  // If no other square has been selected yet
                {
                    toggleSelect(i);
                }
                else if(action.getSource() == b[i] && s[i].getNumber() == gr.getSelectedButton() && gr.getSelectedButton() != 65) // If a selected button is pressed again
                {
                    toggleSelect(i);
                }
                else if(action.getSource() == b[i] && s[i].getNumber() != gr.getSelectedButton() && gr.getSelectedButton() != 65) // when another button is pressed that isn't the currently selected button
                {
                    if(s[gr.getSelectedButton()].canMoveTo(s[i]) == true) // If a move can be made to that square
                    {
                        selectedAndMove(i,gr.getWhiteTurn()); // Move to that square
                    }
                    else
                    {
                        System.out.printf("invalid move, current selected piece is %d",gr.getSelectedButton()); // Tell the user the move can't be made
                    }
                }
            }
            else if(gr.getWhiteTurn() == false) // Red controlled buttons only
            {
                if(action.getSource() == b[i] && gr.getSelectedButton() == 65 && s[i].getPiece() == 2) // If no other square has been selected yet
                {
                    toggleSelect(i);
                }
                else if(action.getSource() == b[i] && s[i].getNumber() == gr.getSelectedButton() && gr.getSelectedButton() != 65) // If a selected button is pressed again
                {
                    toggleSelect(i);
                }
                else if(action.getSource() == b[i] && s[i].getNumber() != gr.getSelectedButton() && gr.getSelectedButton() != 65) // when another button is pressed that isn't the currently selected button
                {
                    if(s[gr.getSelectedButton()].canMoveTo(s[i]) == true) // If a move can be made to that square
                    {
                        selectedAndMove(i,gr.getWhiteTurn()); // Move to that square
                    }
                    else
                    {
                        System.out.printf("invalid move, current selected piece is %d",gr.getSelectedButton()); // Tell the user the move can't be made
                    }
                }
            }
        }
        for(int i = 0 ; i < 64 ; i++) // This loop goes through all squares to check if they should be made yellow
        {
            if(gr.getSelectedButton() != 65 && s[gr.getSelectedButton()].canMoveTo(s[i]) == true) // if the button can be moved to it should be yellow
            {
                s[i].setPiece(5);
                changeTile(i,5);    // make yellow
            }
            else if(gr.getSelectedButton() == 65 && s[i].getPiece() == 5) // once unselection happens, the button should return the its original color
            {
                s[i].setPiece(0);
                changeTile(i,0);    // set to the base color
            }
        }
    }
    
    /**
     * This method visually changes the image that the button holds.
     *
     * Changes button's image depending on the provided number. 0 means that the button is reset to its base image (black or white).
     * 1 means the button has a white piece.
     * 2 means the button has a red piece.
     * 3 means the button has a white king.
     * 4 means the button has a red king.
     * 5 means the button is yellow.
     * @param i the number of the button
     * @param c the color that the button should be changed into
     */
    public void changeTile(int i,int c)
    {
        if(c == 0)                          // sets to base color of the square
        {
            if(s[i].getColor() == 1)
            {
                b[i].setIcon(black);
            }
            else if(s[i].getColor() == 0)
            {
                b[i].setIcon(white);
            }
        }
        else if(c == 1)                    // sets to white piece
        {
            b[i].setIcon(whitePiece);
        }
        else if(c == 2)                    // sets to red piece
        {
            b[i].setIcon(redPiece);
        }
        else if(c == 3)                    // sets to white king
        {
            b[i].setIcon(whiteKing);
        }
        else if(c == 4)                    // sets to red king
        {
            b[i].setIcon(redKing);
        }
        else if(c == 5)
        {
            b[i].setIcon(yellow);           // sets to selected tile
        }
    }
    
    /**
     * This method has been seperated from the actionListener method for clarity. It selects or unselects a square.
     *
     * This method selects or unselects a square. If a square is selected it is unselected. If a square is unselected it is selected.
     * @param i the square number containing a piece.
     */
    private void toggleSelect(int i)
    {
        if(s[i].getNumber() == gr.getSelectedButton())
        {
            gr.setSelectedButton(65);
            System.out.println("Piece " + s[i].getNumber() + " is now un selected");
        }
        else
        {
            gr.setSelectedButton(i);
            System.out.println("Piece " + s[i].getNumber() + " is now selected");
        }
    }
    
    /**
     * The function has been seperated from the actionListener method for clarity. It moves a piece to another and deselects that square.
     *
     * @param which square has been chosen to move to
     * @param n what turn is it? According to the GameRules class
     */
    private void selectedAndMove(int i , boolean b)
    {
        s[gr.getSelectedButton()].moveTo(s[i]);
        changeTile(gr.getSelectedButton(),0);
        if( b == true)
        {
            changeTile(i,1);
        }
        else
        {
            changeTile(i,2);
        }
        gr.toggleTurn();
        gr.nextTurn();
        gr.setSelectedButton(65);
    }
}
