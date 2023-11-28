import assignment1.Game;
import assignment1.Inventory;
import assignment1.InventoryInterface;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class InventoryMockTest {
    @Mock
    private InventoryInterface invIF;

    @Test
    public void testCheapestGame(){
        Inventory invMock = Mockito.mock(Inventory.class);
        Mockito.when(invMock.findCheapestGame()).thenReturn(new Game("12345", "PC", "Madden NFL", "01/01/2023", "EA Games", "Sports", 60, "5.0"));
        Assert.assertEquals("Madden NFL", invMock.findCheapestGame().getName());
    }

    @Test
    public void testExpensiveGame(){
        InventoryInterface invMock = Mockito.mock();
        Mockito.when(invMock.findMostExpensiveGame()).thenReturn(
                new Game("86462", "Nintendo", "Super Mario Bros","01/01/93", "Nintendo", "Platformer", 30, "5.0")
        );
        Assert.assertEquals("Super Mario Bros", invMock.findMostExpensiveGame().getName());
    }

    @Test
    public void testAveragePrice(){
        InventoryInterface invMock = Mockito.mock();
        Mockito.when(invMock.getAveragePriceOfAllGames()).thenReturn(60.0);
        System.out.println(invMock.getAveragePriceOfAllGames());
        Assert.assertEquals(60.0, invMock.getAveragePriceOfAllGames(), 0);
    }

}
