/**
 * This is the Board class. This class is in charge of implementing all types of java swing.
 */

import javax.swing.*; // #includes JFrame
import java.awt.*; // #includes Java Panels
import java.awt.event.*; // #includes action listener

public class Board implements ActionListener
{
    //INSTANCE VARIABLES
    
    private int xDimension;                                         // x dimension of the window
    private int yDimension;                                         // y dimension of the window
    
    //INSTIANTIATE
    private JFrame frame = new JFrame("Game");                      // frame
    private JPanel panel = new JPanel();                            // panel
    private GridLayout layout = new GridLayout(8,8);                // gridlayout, 8x8
    private JButton[] b = new JButton[64];                          // 64 buttons
    private Square[] s = new Square[64];                            // 64 Squares
    private GameRules gr = new GameRules();
        //IMAGES
        private ImageIcon white = new ImageIcon("empty.png");           // white icon
        private ImageIcon black = new ImageIcon("empty2.png");          // black icon
        private ImageIcon yellow = new ImageIcon("selected.png");       // yellow icon
        private ImageIcon redPiece = new ImageIcon("red.png");          // red piece
        private ImageIcon redKing = new ImageIcon("red-king.png");      // redKing
        private ImageIcon whitePiece = new ImageIcon("white.png");      // white piece
        private ImageIcon whiteKing = new ImageIcon("white-king.png");  // whiteKing
    
    //METHODS
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
        makeSquaresAndButtons();                           // instantiates all squares and buttons
        
        //ACTION LISTENER
        for(int i = 0 ; i < 64 ; i++)
        {
            b[i].addActionListener(this);
        }
        //GO
        frame.setVisible(true);                                 // makes frame visible
    }
    /**
     * This function has been seperated from the open() function for the purpouse of clarity.
     *
     *  This method initializes the square classes and the JButtons. It also links each square class to the respective JButton.
     */
    private void makeSquaresAndButtons()
    {
        int y = 0;
        int x = 0;
        int isBlack = 1;
        for(int i = 0; i < 64 ; i++)
        {
            b[i] = new JButton();                                   // INSTANTIATE buttons
            panel.add(b[i]);                                        // Connects each button the the panel
            if(x == 8)                                              // This algorithm goes through the standard grid creation
            {
                x = 0;
                y++;
                if(isBlack == 1)                                    // alternates between white and black
                {
                    isBlack = 0;
                }
                else if(isBlack == 0)
                {
                    isBlack = 1;
                }
            }
            if(isBlack == 1)                                        // Logic for setting the tiles
            {
                s[i] = new Square(i,6);                             // INSTANTIATE Squares
                s[i].setColor(1);
                changeTile(i,0);
                isBlack = 0;
            }
            else
            {
                if(i < 24) // make red peices on white squares less than 24
                {
                    s[i] = new Square(i,2);
                    s[i].setColor(0);
                    changeTile(i,0);
                    isBlack = 1;
                }
                else if(i > 40) // make white pieces on white squares greater than 40
                {
                    s[i] = new Square(i,1);
                    s[i].setColor(0);
                    changeTile(i,0);
                    isBlack = 1;
                }
                else    // blank pieces
                {
                    s[i] = new Square(i,0);
                    s[i].setColor(0);
                    changeTile(i,0);
                    isBlack = 1;
                }
            }
            s[i].setX(x);                                        // Sets the X coordinate
            x++;
            s[i].setY(y);                                        // Sets the Y coordinate
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
        for(int i = 0; i < 64 ; i++)
        {
           if(i < 24) // red pieces
            {
                if(s[i].getColor() == 0)
                {
                    changeTile(i,2);
                    s[i].setPiece(2);
                }
            }
            if(i > 39) // white pieces
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
     * toggleSelect method and the selectedAndMove method.
     */
    public void actionPerformed(ActionEvent action)
    {
        for(int i = 0 ; i < 64 ; i++) // itirates through all buttons
        {
            if( gr.getTurn() == 0) // when white turn
            {
                // First selection of the square
                if(action.getSource() == b[i] && gr.getSelectedButton() == 65 && s[i].getPiece() == 1)
                {
                    toggleSelect(i);
                }
                // Toggling selection of the square
                else if(action.getSource() == b[i] && s[i].getNumber() == gr.getSelectedButton() && gr.getSelectedButton() != 65)
                {
                    toggleSelect(i);
                }
                // Pressing other button while a square is selected alrdy
                else if(action.getSource() == b[i] && s[i].getNumber() != gr.getSelectedButton() && gr.getSelectedButton() != 65)
                {
                    if(s[gr.getSelectedButton()].canMoveTo(s[i]) == true)
                    {
                        selectedAndMove(i,gr.getTurn());
                    }
                    else
                    {
                        System.out.println("invalid move, current selected piece is &d",gr.getSelectedButton());
                    }
                }
            }
            else if(gr.getTurn() == 1) // when red turn
            {
                // First selection of the square
                if(action.getSource() == b[i] && gr.getSelectedButton() == 65 && s[i].getPiece() == 2)
                {
                    toggleSelect(i);
                }
                // Unselecting the square;
                else if(action.getSource() == b[i] && s[i].getNumber() == gr.getSelectedButton() && gr.getSelectedButton() != 65)
                {
                    toggleSelect(i);
                }
                // When pressing another square that isn't the selected square
                else if(action.getSource() == b[i] && s[i].getNumber() != gr.getSelectedButton() && gr.getSelectedButton() != 65)
                {
                    if(s[gr.getSelectedButton()].canMoveTo(s[i]) == true)
                    {
                        selectedAndMove(i,gr.getTurn());
                    }
                    else
                    {
                        System.out.println("invalid move, current selected piece is &d",gr.getSelectedButton());
                    }
                }
            }
        }
        for(int i = 0 ; i < 64 ; i++)
        {
            if(gr.getSelectedButton() != 65 && s[gr.getSelectedButton()].canMoveTo(s[i]) == true)
            {
                s[i].setPiece(5);
                changeTile(i,5);
            }
            else if(gr.getSelectedButton() == 65 && s[i].getPiece() == 5)
            {
                s[i].setPiece(0);
                changeTile(i,0);
            }
        }
    }
    /**
     * This method visually changes the image that the button holds.
     *
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
        if(s[i].getSelected() == 1)
        {
            s[i].changeSelect();
            gr.setSelectedButton(65);
            System.out.println("piece " + s[i].getNumber() + " un selected");
        }
        else
        {
            s[i].changeSelect();
            gr.setSelectedButton(i);
            System.out.println("piece " + s[i].getNumber() + " selected");
        }
    }
    /**
     * The function has been seperated from the actionListener method for clarity. It moves a piece to another and deselects that square.
     *
     * @param which square has been chosen to move to
     * @param n what turn is it? According to the GameRules class
     */
    private void selectedAndMove(int i , int n)
    {
        s[gr.getSelectedButton()].moveTo(s[i]);
        changeTile(gr.getSelectedButton(),0);
        if( n == 0)
        {
            changeTile(i,1);
        }
        else
        {
            changeTile(i,2);
        }
        gr.toggleTurn();
        gr.setSelectedButton(65);
    }
}
