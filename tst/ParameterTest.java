import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


@RunWith(JUnitParamsRunner.class)
public class ParameterTest {
    private static EdgeDriver driver;

//    @Test
//    @Parameters({"1,2,3","2,3,4","4,5,6"})
//    public void testAddition(int a, int b, int c) {
//        Assert.assertEquals(a+b+c, a+b+c);
//    }
    @BeforeClass
    public static void setUp(){
        driver = new EdgeDriver();
    }

    @Test
    @Parameters({"ggc","gsu","ksu"})
    public void testGoogle(String searchString){
        driver.get("https://www.google.com");
        WebElement textBox = driver.findElement(By.name("q"));
        textBox.sendKeys(searchString);
        textBox.submit();
        //WebDriver wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
}
