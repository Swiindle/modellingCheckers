import javax.swing.*; // #includes JFrame
import java.awt.*; // #includes Java Panels

public class board
{
    // instance variables //
    
    private int xDimension;         // x dimension of the window
    private int yDimension;         // y dimension of the window
    
    private JFrame frame = new JFrame("Game");                  // frame
    private JPanel panel = new JPanel();                        // panel
    private GridLayout layout = new GridLayout(8,8);            // gridlayout, 8x8
    private JButton[] b = new JButton[64];                      // 64 buttons
    private square[] s = new square[64];                       // 64 squares
    private ImageIcon white = new ImageIcon("empty.png");       // white icon
    private ImageIcon black = new ImageIcon("empty2.png");      // black icon
    
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
    }
    /*
    * Gives each button an icon, gives each square a color
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
                s[i].changeColor(1);
                isBlack = 0;
                count++;
            }
            else
            {
                b[i].setIcon(white);
                s[i].changeColor(0);
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
}
