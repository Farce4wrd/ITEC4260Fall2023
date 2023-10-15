import assignment1.Inventory;
import assignment1.Store;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;

/**
 * Steam API prices are highly unreliable
 */
public class storeTest {
    private static final String INVENTORY_URL = "https://gist.githubusercontent.com/tacksoo/b1d2da649f14840b598f6ce12ee2f68a/raw/d56aac3c0d2e6f70aa646a9a7077b901ba4e1bed/inventory.csv";
    private Store store;
    @Before
    public void setUp(){
        store = new Store(Store.INVENTORY_URL);
    }

    @Test
    public void testCheapestGame(){
        Inventory inv = store.getInventory();
        Assert.assertEquals(99,inv.findCheapestGame().getPrice(), 0);
    }

    @Test
    public void testMostExpensiveGame(){
        Inventory inv = store.getInventory();
        //most expensive is the first game with the highest price
        //most expensive is
        Assert.assertEquals(3999,inv.findMostExpensiveGame().getPrice(), 0);
    }

    @Test
    public void testAveragePriceOfAllGame(){
        Inventory inv = store.getInventory();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        inv.printAveragePriceOfAllGames();
        double average = (3999 + 3999 +99 + 1999)/4;
        Assert.assertEquals(average, Double.parseDouble(baos.toString()), 10);
    }

    @Test
    public void testDownload() throws Exception{
       Store myStore = new Store(Store.INVENTORY_URL);
       Inventory inv = myStore.getInventory();
        Assert.assertEquals(4, inv.getSize());
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDownloadExternalURL(){
        Store myStore = new Store("https://www.google.com");
    }

    @Test
    public void testIt(){

    }


}
