/**
 * This is the MainMethod class.
 * This class controls the flow of the program and contains the main method. Execute this
 * class to start the program.
 */
public class MainMethod
{
    /**
     * This is the main function.
     */
    public static void main(String[] arguments)
    {
        // initialization
        Board b = new Board(640,640);
        // function
        b.open();
        b.resetPieces();
    }
}
