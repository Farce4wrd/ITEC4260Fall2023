import assignment1.Lawyer;
import org.junit.Assert;
import org.junit.Test;

public class LawyerTest {

    @Test
    public void testVacationDays(){
        Lawyer law = new Lawyer("Peter");
        int vacDay = law.getVacationDays();
        Assert.assertEquals("This should return the vacation days", 1, vacDay);
    }
}
