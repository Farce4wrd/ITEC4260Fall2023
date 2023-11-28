import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class MockitoSampleTest {

    @Test
    public void mockExampleTest(){
        ArrayList names = Mockito.mock(ArrayList.class);
        Mockito.when(names.size()).thenReturn(1000000);
        Assert.assertEquals(1000000, names.size());
    }
}
