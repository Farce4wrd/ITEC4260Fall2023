import assignment1.Game;
import assignment1.Inventory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InventoryTest {

    private static Inventory games = new Inventory();
    //elden ring, fallout 4, resident evil 2 remake
    private static String[] steamIDs = {"883710", "1245620", "377160"};

    @Before
    public void setUp(){
        for(int i = 0; i< steamIDs.length; i++){
            Game g = new Game(steamIDs[i]);
            g.getGameInfoFromSteam();
            games.addGame(g);
        }

    }

    @Test
    public void testAddingToInventory(){
        int size = games.getSize();
        Game g = new Game("894020");
        g.getGameInfoFromSteam();
        games.addGame(g);
        Assert.assertEquals("When you add one game, the inventory increases by one", size+1, games.getSize());
        //let's test adding a duplicate game
        Game d = new Game("883710");
        g.getGameInfoFromSteam();
        Assert.assertEquals("Duplicate game added", size+1, games.getSize());
    }

    @Test
    public void testRemoveFromInventory(){
        int size = games.getSize();    // original size
        System.out.println("Original size: "+ size);
        Game g = new Game("883710"); //Create game object.
        g.getGameInfoFromSteam();
        games.removeGame(g);
        Assert.assertEquals("When you remove a game, the inventory decreases by one", size-1, games.getSize());


    }

    @Test
    public void testCheapestGame(){
        //cheapest in the inventory is fallout4
        Game cheapest = games.findCheapestGame();
        Assert.assertEquals(1999.0, cheapest.getPrice(), 0);

    }

    @Test
    public void testMostHighlyRated(){
        Game highlyrated = games.findMostHighlyRatedGame();
        Assert.assertEquals("12345620", highlyrated.getSteamID());
    }

    @Test
    public void testPrintAveragePriceOfAllGames(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        games.printAveragePriceOfAllGames();
        Assert.assertEquals("fails when games are on sale",
                (1999+3999+5999)/3,Double.parseDouble(baos.toString()),10);
    }


}
