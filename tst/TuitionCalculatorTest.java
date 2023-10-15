import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Select;

public class TuitionCalculatorTest {

    private static EdgeDriver driver;

    @BeforeClass
    public static void setUp(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
    }

    @Test
    public void testTuitionCalculator() {
        driver.get("https://www.ggc.edu/admissions/tuition-and-financial-aid-calculators");
        WebElement element = driver.findElement(By.cssSelector("#inorout1"));
        element.click();
        WebElement selectElement = driver.findElement(By.cssSelector("#creditHOURS"));
        Select creditHours = new Select(selectElement);
        creditHours.selectByVisibleText("15 hours");
        element = driver.findElement(By.cssSelector("#onoroff0"));
        element.click();


        element = driver.findElement(By.cssSelector("#totalcost"));
        String firstTuition = element.getAttribute("value");
        Assert.assertEquals("$2631.00", firstTuition);

        //2nd Exercise
        element = driver.findElement(By.cssSelector("#inorout0"));
        element.click();

        element = driver.findElement(By.cssSelector("#totalcost"));
        String secondTuition = element.getAttribute("value");
        Assert.assertEquals("$8122.00", secondTuition);

        //3rd exercise
        //13 credit hours
        element = driver.findElement(By.cssSelector("#inorout1"));
        element.click();
        selectElement = driver.findElement(By.cssSelector("#creditHOURS"));
        creditHours = new Select(selectElement);
        creditHours.selectByVisibleText("13 hours");
        element = driver.findElement(By.cssSelector("#onoroff0"));
        element.click();
        element = driver.findElement(By.cssSelector("#totalcost"));
        String assumedTuition = element.getAttribute("value");

        driver.get("https://ggc.gabest.usg.edu/pls/B400/twbkwbis.P_WWWLogin");
        element = driver.findElement(By.cssSelector("#UserID"));
        element.sendKeys("900185638");
        element= driver.findElement(By.cssSelector("#PIN"));
        element.sendKeys("200572249");
        element = driver.findElement(By.cssSelector("body > div.pagebodydiv > form > p > input[type=submit]"));
        element.submit();
        element = driver.findElement(By.cssSelector("body > div.pagebodydiv > table.menuplaintable > tbody > tr:nth-child(7) > td:nth-child(2) > a"));
        element.click();
        element = driver.findElement(By.cssSelector("body > div.pagebodydiv > div > table > tbody > tr > td > span > table > tbody > tr:nth-child(3) > td > font > table.menuplaintable > tbody > tr:nth-child(1) > td:nth-child(2) > a"));
        element.click();
        element = driver.findElement(By.cssSelector("body > div.pagebodydiv > form > input[type=submit]"));
        selectElement = driver.findElement(By.cssSelector("#term_id"));
        Select term = new Select(selectElement);
        term.selectByValue("202308");
        element.click();
        element = driver.findElement(By.cssSelector("body > div.pagebodydiv > form > table:nth-child(5) > tbody > tr:nth-child(13) > td:nth-child(3) > p"));
        String actualTuition = (element.getText()).replace(",", "");
        Assert.assertEquals(assumedTuition, actualTuition);

        System.out.println(firstTuition);
        System.out.println(secondTuition);


    }
}
