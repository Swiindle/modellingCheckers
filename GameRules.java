public class GameRules
{
    int turn = 0;
    int selectedPiece = 65;
    
    public void swapTurn()
    {
        if(turn == 0)
        {
            turn = 1;
        }
        else
        {
            turn = 0;
        }
    }
    public int getTurn()
    {
        return turn;
    }
}
