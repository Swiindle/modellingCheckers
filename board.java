import javax.swing.*; // #includes JFrame
import java.awt.*; // #includes Java Panels

public class board
{
    // instance variables //
    
    private int xDimension;         // x dimension of the window
    private int yDimension;         // y dimension of the window
    
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
    *
    * Opens a window
    *
    */
    public void open()
    {
        // constructors
        
        JFrame frame = new JFrame("Game"); // creates a frame
        JPanel panel = new JPanel(); // creates a panel
        GridLayout layout = new GridLayout(8,8); // sets layout to be a gridlayout, of 8x8 size
        JButton[] b = new JButton[64]; // creates an array of 64 objects
        ImageIcon white = new ImageIcon("empty.png"); // imageicon the white
        ImageIcon black = new ImageIcon("empty2.png"); // imageicon the white
        for(int i = 0 ; i < 64 ; i++)
        {
            b[i] = new JButton(); // initializes those objects
        }
        
        // frame related
        
        frame.setVisible(true); // makes frame visible
        frame.setSize(xDimension,yDimension); // sets the dimensions of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // frame closes when close
        
        // panel related
        
        frame.setContentPane(panel); // connects frame and panel
        panel.setLayout(layout); // connects the panel and the layout
        
        int isBlack = 1;
        int count = 0;
        
        for(int i = 0; i < 64 ; i++) // this algorithm gives each button an image
        {
            panel.add(b[i]);
            if(isBlack == 1)
            {
                b[i].setIcon(black);
                isBlack = 0;
                count++;
            }
            else
            {
                b[i].setIcon(white);
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
     *
     * Returns Window Size
     *
     */
    public int getXDimension()
    {
        return this.xDimension;
    }
    /*
     *
     * Returns Window Size
     *
     */
    public int getYDimension()
    {
        return this.yDimension;
    }
    /*
     *
     * Sets X Dimension
     *
     */
    public void setXDimension(int x)
    {
        this.xDimension = x;
    }
    /*
     *
     * Sets Y Dimension
     *
     */
    public void setYDimension(int y)
    {
        this.yDimension = y;
    }
}
