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
     *
     * This method initializes the board
     *
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
        this.makeSquaresAndButtons();                           // instantiates all squares and buttons
        
        //ACTION LISTENER
        for(int i = 0 ; i < 64 ; i++)
        {
            b[i].addActionListener(this);
        }
        //GO
        frame.setVisible(true);                                 // makes frame visible
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
            if(x == 8)                                           // This algorithm goes through the standard grid creation
            {
                x = 0;
                y++;
                if(isBlack == 1)                                 // alternates between white and black
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
                s[i] = new Square(i,5);                             // INSTANTIATE Squares
                s[i].setColor(1);
                changeTile(i,0);
                isBlack = 0;
                x++;
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
                x++;
            }
            s[i].setX(x);                                        // Sets the X coordinate
            s[i].setY(y);                                        // Sets the Y coordinate
        }
    }
    /*
     *
     * This function puts back each piece to their original location.
     *
     */
    public void resetPieces()
    {
        System.out.printf("It's white turn!\n");
        for(int i = 0; i < 64 ; i++)
        {
           if(i < 24) // black pieces
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
    /*
     *
     * What happens when a button is pressed?
     *
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
                    this.toggleSelect(i);
                }
                // Toggling selection of the square
                else if(action.getSource() == b[i] && s[i].getNumber() == gr.getSelectedButton() && gr.getSelectedButton() != 65)
                {
                    this.toggleSelect(i);
                }
                // Pressing other button while a square is selected alrdy
                else if(action.getSource() == b[i] && s[i].getNumber() != gr.getSelectedButton() && gr.getSelectedButton() != 65)
                {
                    if(s[gr.getSelectedButton()].canMoveTo(s[i]) == true)
                    {
                        this.selectedAndMove(i,gr.getTurn());
                    }
                    else
                    {
                        System.out.println("invalid move");
                    }
                }
            }
            else if(gr.getTurn() == 1) // when red turn
            {
                // First selection of the square
                if(action.getSource() == b[i] && gr.getSelectedButton() == 65 && s[i].getPiece() == 2)
                {
                    this.toggleSelect(i);
                }
                // Unselecting the square;
                else if(action.getSource() == b[i] && s[i].getNumber() == gr.getSelectedButton() && gr.getSelectedButton() != 65)
                {
                    this.toggleSelect(i);
                }
                // When pressing another square that isn't the selected square
                else if(action.getSource() == b[i] && s[i].getNumber() != gr.getSelectedButton() && gr.getSelectedButton() != 65)
                {
                    if(s[gr.getSelectedButton()].canMoveTo(s[i]) == true)
                    {
                        this.selectedAndMove(i,gr.getTurn());
                    }
                    else
                    {
                        System.out.println("invalid move");
                    }
                }
            }
            /*
            if(gr.getSelectedButton() != 65 && s[gr.getSelectedButton()].canMoveTo(s[i]) == true)
            {
                System.out.print(i);
                changeTile(i,5);
            }*/
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
    /*
     *
     * This function has been seperated from the action function for clarity
     *
     */
    private void toggleSelect(int i)
    {
        if(s[i].getSelected() == 1)
        {
            s[i].changeSelect();
            gr.setSelectedButton(65);
            System.out.println("piece " + s[i].getNumber() + "un selected");
        }
        else
        {
            s[i].changeSelect();
            gr.setSelectedButton(i);
            System.out.println("piece " + s[i].getNumber() + "selected");
        }
    }
    /*
     *
     * The function has been seperated from action function for clarity
     * i = which itiration of the loop, n = which turn
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
        System.out.printf("Moving to %d\n",i);
    }
}
