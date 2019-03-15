/**
 * This is the MainMethod class.
 * This class controls the flow of the program and contains the main method. Executed this
 * class to start the program.
 */
public class MainMethod
{
    public static void main(String[] arguments)
    {
        // initialization
        Board b = new Board(640,640);
        // function
        b.open();
        b.resetPieces();
    }
}
