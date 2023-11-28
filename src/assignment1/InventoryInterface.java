package assignment1;
import assignment1.Game;
public interface InventoryInterface {
    void add(Game g);
    void remove(Game g);
    Game findCheapestGame();
    Game findMostExpensiveGame();
    double getAveragePriceOfAllGames();
}
