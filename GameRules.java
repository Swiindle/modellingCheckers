/**
 * This class stores all necessary information about the current state of the game.
 * Works with board class to make the game work.
 * Outputs any necessary information.
 * There should only be one instance of GameRules class per game.
 */

public class GameRules
{
    
    /* INSTANCE VARIABLES ARE LISTED BELOW */

    private boolean whiteTurn = true;
    private int turnNumber = 0;
    private int selectedButton = 65; // the current selected button
    private int redScore = 12;
    private int whiteScore = 12;
    
    /* METHODS ARE LISTED BELOW: */

    
    /**
     * This method changes whose turn it is. It also outputs to show the change.
     *
     * If its currently white's turn, then it will change to false.
     * If it isn't currently white's turn the it will change to true!
     */
    public void toggleTurn()
    {
        if(whiteTurn == true)
        {
            whiteTurn = false;
            System.out.printf("RED'S TURN!\n");
        }
        else
        {
            whiteTurn = true;
            System.out.printf("WHITE TURN!\n");
        }
    }
    /**
     * This method returns a true or false statement depending on whether it is white's turn or not.
     * @return Whether it is white's turn or not, as a true or false statement
     */
    public boolean getWhiteTurn()
    {
        return whiteTurn;
    }
    /**
     * This method sets the Current Selected Button to the specified value.
     * Should be 65  when no piece has been selected.
     *
     * When a button is selected, when the conditions have been met as stated in the board class,
     * this method should be used to store the selected button's number.
     * @param n the number of the currently selected button
     */
    public void setSelectedButton(int n)
    {
        selectedButton = n;
    }
    /**
     * This method returns which button is current selected.
     * Should be 65 if no button has been selected.
     * @return the currently selected button.
     */
    public int getSelectedButton()
    {
        return selectedButton;
    }
    /**
     * This method resets the values of the GameRules class. Useful when the game is restarted.
     */
    public void resetScore()
    {
        whiteTurn = true;
        redScore = 12;
        whiteScore = 12;
        turnNumber = 0;
        selectedButton = 65;
    }
    /**
     * This method increments the turn and outputs the turn number.
     */
    public void nextTurn()
    {
        turnNumber++;
        System.out.printf("This is turn number %d\n\n",turnNumber);
    }
}
