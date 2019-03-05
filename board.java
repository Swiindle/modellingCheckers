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
    * Opens the Master File
    *
    */
    public void open()
    {
        // constructors
        
        JFrame frame = new JFrame("Game"); // creates a frame
        JPanel panel = new JPanel(); // creates a panel
        GridLayout layout = new GridLayout(16,16); // sets the layout to be a grid
        JButton[] b = new JButton[64]; // adds buttons
        for(int i = 0 ; i < 64 ; i++)
        {
            b[i] = new JButton();
        }
        
        // frame related
        
        frame.setVisible(true); // makes frame visible
        frame.setSize(xDimension,yDimension); // sets the dimensions of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        // panel related
        
        frame.setContentPane(panel); // connects frame and panel
        panel.setLayout(layout); // connects the panel and the layout
        for(int i = 0 ; i < 64 ; i++)
        {
            panel.add(b);
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
