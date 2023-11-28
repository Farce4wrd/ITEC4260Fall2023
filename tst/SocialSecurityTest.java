import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

@RunWith(JUnitParamsRunner.class)
public class SocialSecurityTest {

    private static EdgeDriver driver;
    private static final String CALC_SITE = "https://www.ssa.gov/OACT/quickcalc/";
    @BeforeClass
    public static void setUp(){
        //System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
    }

    @Test
    @Parameters({"1990, 55000, 2141.00", "1980, 65000, 2322.00", "1970, 75000, 2391.00"})
    public void testStuff(String birthYear, String earningsValue, String finalAmount){
        driver.get(CALC_SITE);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement month = driver.findElement(By.id("month"));
        WebElement day = driver.findElement(By.id("day"));
        WebElement year = driver.findElement(By.id("year"));
        WebElement earnings = driver.findElement(By.id(("earnings")));
        WebElement retireMonth = driver.findElement(By.id("retiremonth"));
        WebElement lastYear = driver.findElement(By.id("lastyear"));
        WebElement submitButton = driver.findElement(By.cssSelector("body > table:nth-child(6) > tbody > tr:nth-child(2) > td:nth-child(2) > form > table > tbody > tr:nth-child(5) > td > input[type=submit]"));

        month.clear();
        day.clear();
        year.clear();
        earnings.clear();
        retireMonth.clear();
        lastYear.clear();

//        // Born January 1 1990 earning $55,000
//        int person1RetirementYear = 1990 + 67;
//        month.sendKeys("6");
//        day.sendKeys("1");
//        year.sendKeys("1990");
//        earnings.sendKeys("55000");
//        retireMonth.sendKeys("1");
//        lastYear.sendKeys(String.valueOf(person1RetirementYear));

        month.sendKeys("1");
        day.sendKeys("1");
        year.sendKeys(birthYear);
        earnings.sendKeys(earningsValue);
        submitButton.click();

        WebElement total = driver.findElement(By.cssSelector("#est_fra"));
        String pulledFinalValue = total.getText();
        pulledFinalValue = pulledFinalValue.replace("$", "").replace(",", "");
        System.out.println(pulledFinalValue);
        Assert.assertEquals(finalAmount,pulledFinalValue);

    }

    @Test
    @Parameters("1990, 55000, 2141.00")
    public void testBenefitIn10Years(String birthYear, String earningsValue, String finalAmount){

        driver.get(CALC_SITE);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement month = driver.findElement(By.id("month"));
        WebElement day = driver.findElement(By.id("day"));
        WebElement year = driver.findElement(By.id("year"));
        WebElement earnings = driver.findElement(By.id(("earnings")));
        WebElement retireMonth = driver.findElement(By.id("retiremonth"));
        WebElement lastYear = driver.findElement(By.id("lastyear"));
        WebElement submitButton = driver.findElement(By.cssSelector("body > table:nth-child(6) > tbody > tr:nth-child(2) > td:nth-child(2) > form > table > tbody > tr:nth-child(5) > td > input[type=submit]"));

        month.clear();
        day.clear();
        year.clear();
        earnings.clear();
        retireMonth.clear();
        lastYear.clear();

        month.sendKeys("1");
        day.sendKeys("1");
        String after10Years = birthYear+10;
        year.sendKeys(birthYear);
        earnings.sendKeys(earningsValue);
        submitButton.click();

        WebElement total = driver.findElement(By.cssSelector("#est_fra"));
        String pulledFinalValue = total.getText();
        pulledFinalValue = pulledFinalValue.replace("$", "").replace(",", "");
        System.out.println(pulledFinalValue);
        //Assert.assertEquals(finalAmount,pulledFinalValue);
        Assert.assertFalse(finalAmount.equals(pulledFinalValue));

        //value at age 20
        //value at age 30


    }

    @Test
    @Parameters({"1,1,2001,168600", "1,1,1990,300000", "1,1,1980,400000","1,1,1970,500000", "1,1,2000,10000000"})
    public void testBenefits(String m, String d, String y, String salary) throws Exception{
        driver.get(CALC_SITE);
        System.out.println(driver.getTitle());
        WebElement month = driver.findElement(By.id("month"));
        WebElement day = driver.findElement(By.id("day"));
        WebElement year = driver.findElement(By.id("year"));
        WebElement earnings = driver.findElement(By.id("earnings"));
        month.clear();
        day.clear();
        year.clear();
        earnings.clear();
        month.sendKeys(m);
        day.sendKeys(d);
        year.sendKeys(y);
        earnings.sendKeys(salary);
        WebElement submit = driver.findElement(By.cssSelector("body > table:nth-child(6) > tbody > tr:nth-child(2) > td:nth-child(2) > form > table > tbody > tr:nth-child(5) > td > input[type=submit]"));
        submit.submit();

        WebElement benefitAmount = driver.findElement(By.id("est_fra"));
        double benefit = Double.parseDouble(benefitAmount.getText().replace("$", "").replace(",", ""));
        String record = m+","+d+","+y+","+salary+","+benefit;
        FileUtils.writeStringToFile(new File("benefits.csv"), record+"\n", "UTF-8", true);

    }

    @Test
    @Parameters({"0","10","20","30","40"})
    public void testGapYear(int gapYears) throws IOException {
        driver.get(CALC_SITE);
        System.out.println(driver.getTitle());
        WebElement month = driver.findElement(By.id("month"));
        WebElement day = driver.findElement(By.id("day"));
        WebElement year = driver.findElement(By.id("year"));
        WebElement earnings = driver.findElement(By.id("earnings"));
        month.clear();
        day.clear();
        year.clear();
        earnings.clear();
        month.sendKeys("1");
        day.sendKeys("1");
        year.sendKeys("1970");
        earnings.sendKeys("60000");
        WebElement submit = driver.findElement(By.cssSelector("body > table:nth-child(6) > tbody > tr:nth-child(2) > td:nth-child(2) > form > table > tbody > tr:nth-child(5) > td > input[type=submit]"));
        submit.submit();
        //go to the earnings used page
        WebElement button = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > form:nth-child(2) > input:nth-child(10)"));
        button.submit();

        //0 gap years mean, starts work at age 22
        int adultYear = 1970+17;
        int startYear = 1970+22+gapYears;
        for (int i = adultYear; i < startYear; i++) {
            WebElement e = driver.findElement(By.name(String.valueOf(i)));
            e.clear();
            e.sendKeys("0");
        }

        for (int i = startYear; i <= 2023; i++) {
            WebElement e = driver.findElement(By.name(String.valueOf(i)));
            e.clear();
            e.sendKeys("60000");
        }

        WebElement reclacButton = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > form:nth-child(3) > input:nth-child(7)"));
        reclacButton.submit();


        WebDriverWait wait3 = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#est_fra")));
        WebElement benefitAmount = driver.findElement(By.cssSelector("#est_fra"));
        double benefit = Double.parseDouble(benefitAmount.getText().replace("$","").replace(",",""));
        FileUtils.writeStringToFile(new File("gaps.csv"),gapYears + ";" + benefit + "\n","UTF-8",true);

    }

    @AfterClass
    public static void cleanUp(){
        //driver.close();
    }
}
