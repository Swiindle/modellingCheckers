public class GameRules
{
    int turn = 0;
    int selectedButton = 65; // the current selected button
    /*
     *
     * This function has been seperated from the open() function for the purpouse of clarity
     *
     */
    public void toggleTurn()
    {
        if(turn == 0)
        {
            turn = 1;
            System.out.printf("it is Red's turn!\n");
        }
        else
        {
            turn = 0;
            System.out.printf("it is White's turn!\n");
        }
    }
    /*
     *
     * This function has been seperated from the open() function for the purpouse of clarity
     *
     */
    public int getTurn()
    {
        return turn;
    }
    /*
     *
     * This function has been seperated from the open() function for the purpouse of clarity
     *
     */
    public void setSelectedButton(int n)
    {
        selectedButton = n;
    }
    /*
     *
     * This function has been seperated from the open() function for the purpouse of clarity
     *
     */
    public int getSelectedButton()
    {
        return selectedButton;
    }
}
