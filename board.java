import javax.swing.*; // #includes JFrame
import java.awt.*; // #includes Java Panels
import java.awt.event.*; // #includes action listener


public class board implements ActionListener
{
    // instance variables //
    
    private int xDimension;                                         // x dimension of the window
    private int yDimension;                                         // y dimension of the window
    
    private JFrame frame = new JFrame("Game");                      // frame
    private JPanel panel = new JPanel();                            // panel
    private GridLayout layout = new GridLayout(8,8);                // gridlayout, 8x8
    private JButton[] b = new JButton[64];                          // 64 buttons
    private square[] s = new square[64];                            // 64 squares
    //IMAGES
    private ImageIcon white = new ImageIcon("empty.png");           // white icon
    private ImageIcon black = new ImageIcon("empty2.png");          // black icon
    private ImageIcon yellow = new ImageIcon("selected.png");       // yellow icon
    private ImageIcon redPiece = new ImageIcon("red.png");          // red piece
    private ImageIcon redKing = new ImageIcon("red-king.png");      // redKing
    private ImageIcon whitePiece = new ImageIcon("white.png");      // white piece
    private ImageIcon whiteKing = new ImageIcon("white-king.png");  // whiteKing

    
    // methods //
    
    /**
     * Constructor. Makes an instance of the GUI.
     *
     * @param width The width of the window, in pixels.
     * @param height The height of the window, in pixels.
     */
    board(int x , int y)
    {
        xDimension = x;
        yDimension = y;
    }
    /*
    * This method initializes the board
    */
    public void open()
    {
        //INSTANTIATE CONSTRUCTORS
        
        for(int i = 0 ; i < 64 ; i++)
        {
            b[i] = new JButton();                           // INSTANTIATE buttons
            s[i] = new square(i);                           // INSTANTIATE squares
        }
        
        //FRAME
        
        frame.setVisible(true);                                 // makes frame visible
        frame.setSize(xDimension,yDimension);                   // sets the dimensions of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // frame closes when close
        
        //PANEL
        frame.setContentPane(panel);                            // connects frame and panel
        panel.setLayout(layout);                                // connects the panel and the layout
        
        //BUTTON & SQUARE
        this.setIcon();                                         // see below
        this.makeSquares();                                     // see below
        
        //ACTION LISTENER
        for(int i = 0 ; i < 64 ; i++)
        {
            b[i].addActionListener(this);
        }
    }
    /*
     *
     * Gives each button an icon, gives each square a color
     *
     */
    private void setIcon()
    {
        int isBlack = 1;
        int count = 0;
        
        for(int i = 0; i < 64 ; i++)
        {
            panel.add(b[i]);
            if(isBlack == 1)
            {
                b[i].setIcon(black);
                s[i].setColor(1);
                isBlack = 0;
                count++;
            }
            else
            {
                b[i].setIcon(white);
                s[i].setColor(0);
                isBlack = 1;
                count++;
            }
            if(count == 8)
            {
                count = 0;
                if(isBlack == 1)
                {
                    isBlack = 0;
                }
                else
                {
                    isBlack = 1;
                }
            }
        }
    }
    /*
     * Gives each square a x coordinate and a y coordinate
     */
    private void makeSquares()
    {
        int x = 0;
        int y = 0;
        for(int i = 0 ; i < 64 ; i++)
        {
            s[i].setX(x);
            s[i].setY(y);
            if(x == 8)
            {
                x = 0;
                y++;
            }
            x++;
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
            if(action.getSource() == b[i] && s[i].isSelected() == 0)
            {
                this.changeTile(i,5);
                s[i].changeSelect();
                System.out.printf("%d should now be selected\n",i);
            }
            else if(action.getSource() == b[i] && s[i].isSelected == 1)
            {
                System.out.printf("%d should now be unselected\n",i);
                if(s[i].getColor() == 1)
                {
                    this.changeTile(i,0);
                    s[i].changeSelect();
                }
                else if(s[i].getColor() == 0)
                {
                    this.changeTile(i,0);
                    s[i].changeSelect();
                }
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
            System.out.printf("set back to normal, base color is %d\n",s[i].getColor());
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
