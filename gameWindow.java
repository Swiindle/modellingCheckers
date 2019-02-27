import javax.swing.*; // #includes JFrame

public class gameWindow
{
    // instance variables //
    
    private int xDimension;         // x dimension of the window
    private int yDimension;         // y dimension of the window
    
    // methods //
    
    /**
     * Constructor. Makes an instance of the Game Window.
     *
     * @param width The width of the window, in pixels.
     * @param height The height of the window, in pixels.
     */
    gameWindow(int x , int y)
    {
        xDimension = x;
        yDimension = y;
    }
    /*
    *
    * Opens a Game Window using JFrame
    *
    */
    public void open()
    {
        JFrame frame = new JFrame("Game"); // creates a frame
        JPanel panel = new JPanel(); // creates a panel
        frame.setContentPane(panel); // connects frame and panel
        frame.setVisible(true); // makes frame visible
        frame.setSize(xDimension,yDimension); // sets the dimensions of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
