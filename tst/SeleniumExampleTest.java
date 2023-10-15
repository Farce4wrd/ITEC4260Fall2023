import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class SeleniumExampleTest {

    private static EdgeDriver driver;

    @BeforeClass
    public static void setUp(){
        //System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
    }

    @Test
    public void testGGC(){
        driver.get("https://www.google.com");
        //driver.navigate().to("https://google.com");
    }

    @Test
    public void testNews(){
        driver.get("https://news.ycombinator.com/");

        WebElement newsTable = driver.findElement(By.cssSelector("#hnmain > tbody > tr:nth-child(3) > td > table > tbody"));
        int headlinesCount = newsTable.findElements(By.cssSelector("tr.athing")).size();

        System.out.println("This is headcount: "+headlinesCount);
        Assert.assertEquals(headlinesCount, 30);
    }


    @Test
    public void googleSearchExample(){
        driver.get("https://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.clear();
        element.sendKeys("GGC");
        element.submit();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("GGC"));
    }

    @Test
    public void ihgRewardsExample(){
        driver.get("https://ihgrewardsclubdining.rewardsnetwork.com/join.htm");

        WebElement element = driver.findElement(By.name("firstName"));
        element.clear();
        element.sendKeys("Peter");
        element = driver.findElement(By.name("lastName"));
        element.sendKeys("NotSaying");
        element = driver.findElement(By.name("zipCode"));
        element.sendKeys("30043");
        element = driver.findElement(By.name("partnerProgramNumber"));
        element.sendKeys("114151626215151");
        element = driver.findElement(By.name("email"));
        element.sendKeys("iokolocha@ggc.edu");
        element = driver.findElement(By.name("password"));
        element.sendKeys("ThisIs8lettered");
        element= driver.findElement(By.name("acceptTOS"));
        element.submit();
    }
    @Ignore
    @Test
    public void testCraigsListBestOf() {
        driver.get("https://www.craigslist.org/about/best/all/");
        WebElement element  =  driver.findElement(By.cssSelector(".bestoftoc > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > a:nth-child(1)"));
        System.out.println(element.getText());
    }

//    @AfterClass
//    public static void cleanUp(){
//        driver.close();
//    }
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

        element = driver.findElement(By.cssSelector("#inorout0"));
        element.click();

        element = driver.findElement(By.cssSelector("#totalcost"));
        String secondTuition = element.getAttribute("value");
        Assert.assertEquals("$8122.00", secondTuition);

        System.out.println(firstTuition);
        System.out.println(secondTuition);

    }
    @Test
    public void testBill(){
        String url = "https://ggc.gabest.usg.edu/pls/B400/twbkwbis.P_WWWLogin";
    }








}
