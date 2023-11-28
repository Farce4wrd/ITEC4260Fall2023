import junitparams.Parameters;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;
import java.util.List;

public class Project1 {
    private static EdgeDriver driver;
    private static final String hotwire = "https://www.hotwire.com/";

    private static final String googleTravel ="https://www.google.com/travel/flights?tcfs&ved=2ahUKEwi-4e3jieaCAxVOgwMIHeWdADAQ0I8EegQIAxAJ&ictx=2";

    private static final String travel ="https://www.expedia.com/";
    private static final String travel2 = "https://www.kayak.com/";
    private static final String travel3 = "https://www.booking.com/packages.html?aid=304142&label=gen173bo-1DCAEoggI46AdIM1gDaI8CiAEBmAExuAEXyAEM2AED6AEB-AECiAIBmAICqAIDuAK_nP-qBsACAdICJDM3Zjc0MzZkLTgwZmQtNDlkMy1hYjRhLWIxYzc2NjliZTVkOdgCBOACAQ&sid=18d21f300d0af9311c5affecc7f568e7";
    @BeforeClass
    public static void setUp(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
    }

    @Test
    //@Parameters({"Cancun", "Las Vegas"})
    public void visitCancun(){
        String city = "Cancun, Mexico";
        driver.get(travel2);
        //WebElement element = driver.findElement(By.id("#flights.0.startLocation-typeahead-downshift-container-input"));
        WebElement element1 = driver.findElement(By.cssSelector("div > div > div > div.zEiP-formContainer > div.zEiP-formBody > div > div.zEiP-formField.zEiP-destination > div > div > input"));
        //WebElement ec = driver.findElement(By.cssSelector("#c0UZ9 > div > div > div > div.zEiP-formContainer > div.zEiP-formBody > div > div.zEiP-formField.zEiP-destination > div > div > input"));
        element1.sendKeys(city);
        element1.submit();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement searchButton = driver.findElement(By.cssSelector("div > div > div > div.zEiP-formContainer > div.zEiP-formBody > div > div.zEiP-formField.zEiP-submit > button"));
        searchButton.submit();
        
    }

    @Test
    public void expedia(){
        String fromCity = "Atlanta";
        String toCity = "Cancun";
        driver.get(travel);
        WebElement flight = driver.findElement(By.cssSelector("#multi-product-search-form-1 > div > div > div.uitk-tabs-container > ul > li:nth-child(2) > a"));
        flight.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement fromButton = driver.findElement(By.cssSelector("#FlightSearchForm_ROUND_TRIP > div > div.uitk-layout-flex-item.uitk-layout-flex-item-max-width-half_width.uitk-layout-flex-item-flex-basis-half_width > div > div.uitk-input-swapper-start-input > div > div > div:nth-child(2) > div:nth-child(1) > button"));
        fromButton.click();
        WebElement fromElement = driver.findElement(By.cssSelector("#origin_select"));
        fromElement.sendKeys(fromCity);
        fromElement.submit();
;    }

    @Test
    public void hotWire(){
        String fromCity = "Atlanta, GA, United States of America (ATL-Hartsfield-Jackson Atlanta Intl.)";
        String toCity = "Cancun, Quintana Roo, Mexico";
        String params = "Cancun, Quintana Roo, Mexico"+"";
        driver.get(hotwire);
        WebElement element = driver.findElement(By.cssSelector("#root > div.page-home > div.hero-background-container.hero-background-container--campaign > div.hero-background > div > div > div.farefinder-container > div > div.farefinder-options > div.farefinder-option.active"));
        //click on the flight button
        WebElement ec = driver.findElement(By.cssSelector("#root > div.page-home > div.hero-background-container.hero-background-container--campaign > div.hero-background > div > div > div.farefinder-container > div > div.farefinder-options > div:nth-child(3)"));
        ec.click();
        //Find the From City Input element
        WebElement fromElement = driver.findElement(By.cssSelector("#root > div.page-home > div.hero-background-container.hero-background-container--campaign > div.hero-background > div > div > div.farefinder-container > div > div.farefinder-flight.farefinder-content > form > div.col-xs-12.margin-top-6 > div > div > input"));
        fromElement.sendKeys(fromCity);
        fromElement.submit();
        //WebElement toElement = driver.findElement(By.cssSelector("#root > div.page-home > div.hero-background-container.hero-background-container--campaign > div.hero-background > div > div > div.farefinder-container > div > div.farefinder-flight.farefinder-content > form > div:nth-child(4) > div > div.hw-form-group.form-group.floating-label.empty.has-icon.has-error > input"));
        WebElement toElement = driver.findElement(By.cssSelector("#root > div.page-home > div.hero-background-container.hero-background-container--campaign > div.hero-background > div > div > div.farefinder-container > div > div.farefinder-flight.farefinder-content > form > div:nth-child(3) > div > div > input"));

        toElement.sendKeys(toCity);
        //#root > div.page-home > div.hero-background-container.hero-background-container--campaign > div.hero-background > div > div > div.farefinder-container > div > div.farefinder-flight.farefinder-content > form > div:nth-child(4) > div > div.hw-form-group.form-group.floating-label.empty.has-icon > input
        //WebElement nextButton = driver.findElement(By.cssSelector("#tipContent-694 > span.Tooltip__inner > div > div.cal-controls.clearfix.picker__calendar.range > div.cal-controls__button.text-center.cal-controls__button--middle.cal-controls__button--next > button"));

        WebElement fromDate = driver.findElement(By.cssSelector("#input1-farefinder-flight-date"));
        fromDate.sendKeys("05/01/2024");
        WebElement toDate = driver.findElement(By.cssSelector("#input2-farefinder-flight-date"));
        toDate.sendKeys("05/07/2024");
        WebElement flightButton = driver.findElement(By.cssSelector("#root > div.page-home > div.hero-background-container.hero-background-container--campaign > div.hero-background > div > div > div.farefinder-container > div > div.farefinder-flight.farefinder-content > form > div.submit-button > button"));
        flightButton.click();
    }

    @Test
    public void googleFlight(){
        driver.get(googleTravel);
//        WebElement fromCity = driver.findElement(By.cssSelector("#ow60 > div.cQnuXe.k0gFV > div > div > input"));
//        fromCity.sendKeys("Atlanta");

        //WebElement toCity = driver.findElement(By.cssSelector("#i21 > div.e5F5td.vxNK6d > div > div > div.cQnuXe.k0gFV > div > div > input"));
        WebElement toInput = driver.findElement(By.xpath("//input[@placeholder='Where to?']"));
        toInput.sendKeys("Cancun");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //WebElement firstSuggestion = driver.findElement(By.xpath("//li[@role='option'][1]"));
        WebElement firstSuggestion = driver.findElement(By.id("c2"));
        System.out.println(firstSuggestion);
        firstSuggestion.click();

        WebElement departDateInput = driver.findElement(By.xpath("//input[@placeholder='Departure']"));
        departDateInput.clear();
        departDateInput.sendKeys("2024-05-01");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Enter the return date
        WebElement returnDateInput = driver.findElement(By.xpath("//input[@placeholder='Return']"));
        returnDateInput.clear();
        returnDateInput.sendKeys("2024-05-07");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


//        WebElement fromDate = driver.findElement(By.cssSelector("#yDmH0d > c-wiz.zQTmif.SSPGKf > div > div:nth-child(2) > c-wiz > div.cKvRXe > c-wiz > div.vg4Z0e > div:nth-child(1) > div.SS6Dqf.POQx1c > div.AJxgH > div > div.rIZzse > div.bgJkKe.K0Tsu > div > div > div.cQnuXe.k0gFV > div > div > div:nth-child(1) > div > div.oSuIZ.YICvqf.kStSsc.ieVaIb > div > input"));
//        fromDate.click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//
//        WebElement innerFromDate = driver.findElement(By.cssSelector("#ow79 > div.ZGEB9c.yRXJAe.P0ukfb.icWGef.bgJkKe.BtDLie.iWO5td > div > div.rj7qGc.ksI2Bc.P0ukfb > div.X4feqd.wDt51d > div.AotkO.uknidb > div.oSuIZ.YICvqf.kStSsc.ieVaIb"));
//        innerFromDate.clear();
//        innerFromDate.sendKeys("2024-05-01",Keys.ENTER);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//
//        WebElement innerToDate = driver.findElement(By.cssSelector("#ow79 > div.ZGEB9c.yRXJAe.P0ukfb.icWGef.bgJkKe.BtDLie.iWO5td > div > div.rj7qGc.ksI2Bc.P0ukfb > div.X4feqd.wDt51d > div.AotkO.uknidb > div.oSuIZ.YICvqf.lJODHb.qXDC9e"));
//        innerToDate.clear();
//        innerToDate.sendKeys("2024-05-07",Keys.ENTER);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//
//        WebElement closeDateButton = driver.findElement(By.cssSelector("#ow79 > div.ZGEB9c.yRXJAe.P0ukfb.icWGef.bgJkKe.BtDLie.iWO5td > div > div.akjk5c.FrVb0c > div.WXaAwc > div > button"));
//        closeDateButton.click();

        WebElement searchButton = driver.findElement(By.cssSelector("#yDmH0d > c-wiz.zQTmif.SSPGKf > div > div:nth-child(2) > c-wiz > div.cKvRXe > c-wiz > div.vg4Z0e > div:nth-child(1) > div.SS6Dqf.POQx1c > div.MXvFbd > div > button"));
        searchButton.click();

        //#yDmH0d > c-wiz.zQTmif.SSPGKf > div > div:nth-child(2) > c-wiz > div.cKvRXe > c-wiz > div.vg4Z0e > div:nth-child(1) > div.SS6Dqf.POQx1c > div.AJxgH > div > div.rIZzse > div.bgJkKe.K0Tsu > div > div > div.cQnuXe.k0gFV > div > div > div:nth-child(1) > div > div.oSuIZ.YICvqf.kStSsc.ieVaIb > div > input

    }
    private void clickElement(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

}
