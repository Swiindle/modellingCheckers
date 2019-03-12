import javax.swing.*; // #includes JFrame
import java.awt.*; // #includes Java Panels
import java.awt.event.*; // #includes action listener


public class Board implements ActionListener
{
    //INSTANCE VARIABLES
    
    private int xDimension;                                         // x dimension of the window
    private int yDimension;                                         // y dimension of the window
    private int selectedButton = 65;                                 // the current board selected piece
    
    //INSTIANTIATE
    private JFrame frame = new JFrame("Game");                      // frame
    private JPanel panel = new JPanel();                            // panel
    private GridLayout layout = new GridLayout(8,8);                // gridlayout, 8x8
    private JButton[] b = new JButton[64];                          // 64 buttons
    private Square[] s = new Square[64];                            // 64 Squares
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
     * Constructor. Makes an instance of the GUI.
     *
     *
     */
    Board(int x , int y)
    {
        xDimension = x;
        yDimension = y;
    }
    /*
    * This method initializes the board
    */
    public void open()
    {
        //FRAME
        
        frame.setVisible(true);                                 // makes frame visible
        frame.setSize(xDimension,yDimension);                   // sets the dimensions of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // frame closes when close
        
        //PANEL
        frame.setContentPane(panel);                            // connects frame and panel
        panel.setLayout(layout);                                // connects the panel and the layout
        //BUTTON & Square
        this.makeSquaresAndButtons();                           // instantiates all squares and buttons
        
        //ACTION LISTENER
        for(int i = 0 ; i < 64 ; i++)
        {
            b[i].addActionListener(this);
        }
    }
    /*
     *
     * This function has been seperated from the open() function for the purpouse of clarity
     *
     */
    private void makeSquaresAndButtons()
    {
        int y = 0;
        int isBlack = 1;
        int x = 0;
        for(int i = 0; i < 64 ; i++)
        {
            b[i] = new JButton();                                   // INSTANTIATE buttons
            panel.add(b[i]);                                        // Connects each button the the panel
            if(isBlack == 1)                                        // Logic for setting the tiles
            {
                s[i] = new Square(i,5);                             // INSTANTIATE Squares
                s[i].setColor(1);
                changeTile(i,0);
                isBlack = 0;
                x++;
            }
            else if(isBlack == 0)
            {
                if(i > 40)
                {
                    s[i] = new Square(i,1);
                    s[i].setColor(0);
                    isBlack = 1;
                    x++;
                }
                else
                {
                    s[i] = new Square(i,0);
                    s[i].setColor(0);
                    changeTile(i,0);
                    isBlack = 1;
                    x++;
                }

            }
            if(x == 8)                                           // This algorithm goes through the standard grid creation
            {
                x = 0;
                y++;
                if(isBlack == 1)                                     // alternates between white and black
                {
                    isBlack = 0;
                }
                else
                {
                    isBlack = 1;
                }
            }
            s[i].setX(x);                                           // Sets the X coordinate
            s[i].setY(y);                                           // Sets the Y coordinate
        }
    }
    /*
     *
     * This function puts back each piece to their original location.
     *
     */
    public void makePieces()
    {
        for(int i = 0; i < 64 ; i++)
        {
           /* if(i < 24) // black pieces
            {
                if(s[i].getColor() == 0)
                {
                    changeTile(i,2);
                    s[i].setPiece(2);
                }
            } */
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
    /*
     *
     * What happens when a button is pressed?
     *
     */
    public void actionPerformed(ActionEvent action)
    {
        for(int i = 0 ; i < 64 ; i++)
        {
            // First selection of the square
            if(action.getSource() == b[i] && selectedButton == 65 && s[i].getPiece() == 1)
            {
                selectedButton = i;
                s[i].changeSelect();
                System.out.printf("i am %d, i am selected\n", selectedButton);
            }
            // Toggling selection of the square
            else if(action.getSource() == b[i] && s[i].getNumber() == selectedButton && selectedButton != 65)
            {
                System.out.printf("i am %d, i am unselected\n", selectedButton);
                selectedButton = 65;
                s[i].changeSelect();
            }
            // Pressing other button while a square is selected alrdy
            else if(action.getSource() == b[i] && s[i].getNumber() != selectedButton && selectedButton != 65)
            {
                if(s[i].getPiece() == 0) // if other button is empty
                {
                    s[selectedButton].moveTo(s[i]);
                    changeTile(selectedButton,0);
                    changeTile(i,1);
                    selectedButton = 65;
                    System.out.printf("Moving to %d\n",i);
                }
                else // if other button is not relevant
                {
                    System.out.printf("Not relevant\n");
                }
            }
            else
            {
                System.out.printf("Not relevant\n");
            }
        }
    }
    /*
     *
     * Changes the tile
     *
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
}
