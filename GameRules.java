/**
 * This class stores all necessary information about the current state of the game.
 * Works with board class to make the game work.
 * Outputs any necessary information.
 */

public class GameRules
{
    int turn = 0;
    int selectedButton = 65; // the current selected button
    int redScore = 12;
    int whiteScore = 12;
    /**
     * This method changes whose turn it is. It also outputs to show the change.
     *
     */
    public void toggleTurn()
    {
        if(turn == 0)
        {
            turn = 1;
            System.out.printf("RED'S TURN\n\n");
        }
        else
        {
            turn = 0;
            System.out.printf("WHITE TURN\n\n");
        }
    }
    /**
     * This method returns whose turn is it. 0 being white, 1 being red.
     * @return whose turn is it?
     */
    public int getTurn()
    {
        return turn;
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
     * This method resets the values of the GameRules class.
     */
    public void resetScore()
    {
        turn = 0;
        redScore = 12;
        whiteScore = 12;
    }
}
