import assignment1.Flight;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.asynchttpclient.util.DateUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.*;
import java.time.Duration;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
@RunWith(JUnitParamsRunner.class)
public class Project1 {
    private static EdgeDriver driver;
    private static final String hotwire = "https://www.hotwire.com/";

    private static final String googleTravel ="https://www.google.com/travel/flights?tcfs&ved=2ahUKEwi-4e3jieaCAxVOgwMIHeWdADAQ0I8EegQIAxAJ&ictx=2";
    private static Connection connection;
    private static final String travel ="https://www.expedia.com/";
    private static final String travel2 = "https://www.kayak.com/";
    private static final String travel3 = "https://www.booking.com/packages.html?aid=304142&label=gen173bo-1DCAEoggI46AdIM1gDaI8CiAEBmAExuAEXyAEM2AED6AEB-AECiAIBmAICqAIDuAK_nP-qBsACAdICJDM3Zjc0MzZkLTgwZmQtNDlkMy1hYjRhLWIxYzc2NjliZTVkOdgCBOACAQ&sid=18d21f300d0af9311c5affecc7f568e7";
    @BeforeClass
    public static void setUp(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
//        try{
//            connection = DriverManager.getConnection("jdbc:sqlite:flightData.sqlite");
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
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
    @Parameters({"Cancun","Las Vegas","Denver", "Rome", "Milan", "Paris", "Madrid", "Amsterdam", "Singapore"})
    public void googleFlight(String destination) throws InterruptedException {
        driver.get(googleTravel);

        ArrayList<String[]> myDates = getDates();
        WebElement toInput = driver.findElement(By.xpath("//input[@placeholder='Where to?']"));
        toInput.sendKeys("Cancun");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        WebElement firstSuggestion = driver.findElement(By.id("c2"));
        System.out.println(firstSuggestion);
        firstSuggestion.click();
        String[] firstDates = myDates.get(0);


        WebElement departDateInput = driver.findElement(By.xpath("//input[@placeholder='Departure']"));
        departDateInput.clear();
        departDateInput.sendKeys(firstDates[0]);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Enter the return date
        WebElement returnDateInput = driver.findElement(By.xpath("//input[@placeholder='Return']"));
        returnDateInput.clear();
        returnDateInput.sendKeys(firstDates[1]);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        WebElement searchButton = driver.findElement(By.cssSelector("#yDmH0d > c-wiz.zQTmif.SSPGKf > div > div:nth-child(2) > c-wiz > div.cKvRXe > c-wiz > div.vg4Z0e > div:nth-child(1) > div.SS6Dqf.POQx1c > div.MXvFbd > div > button"));
        searchButton.click();
        WebElement priceText = driver.findElement(By.cssSelector("#yDmH0d > c-wiz.zQTmif.SSPGKf > div > div:nth-child(2) > c-wiz > div.cKvRXe > c-wiz > div.PSZ8D.EA71Tc > div.FXkZv > div:nth-child(4) > ul > li:nth-child(1) > div > div.yR1fYc > div > div.OgQvJf.nKlB3b > div.U3gSDe > div.BVAVmf.I11szd.POX3ye > div.YMlIz.FpEdX > span"));
        String price = priceText.getText();
        //allPrices.add(price);
        int parsedPrice = Integer.parseInt(price.replace("$","").replace(",",""));
        putTravelDataInDatabase(destination, parsedPrice, firstDates[0],firstDates[1]);

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        for(int i= 1; i<myDates.size(); i++){
            String[] dates = myDates.get(i);
            departDateInput = driver.findElement(By.xpath("//input[@placeholder='Departure']"));
            departDateInput.clear();
            departDateInput.sendKeys(Keys.CONTROL + "a"); // Select all text
            departDateInput.sendKeys(Keys.DELETE);
            departDateInput.sendKeys(dates[0]);
            Thread.sleep(3000);

            returnDateInput = driver.findElement(By.xpath("//input[@placeholder='Return']"));
            returnDateInput.clear();
            returnDateInput.sendKeys(dates[1], Keys.ENTER);
            Thread.sleep(3000);

            priceText = driver.findElement(By.cssSelector("#yDmH0d > c-wiz.zQTmif.SSPGKf > div > div:nth-child(2) > c-wiz > div.cKvRXe > c-wiz > div.PSZ8D.EA71Tc > div.FXkZv > div:nth-child(4) > ul > li:nth-child(1) > div > div.yR1fYc > div > div.OgQvJf.nKlB3b > div.U3gSDe > div.BVAVmf.I11szd.POX3ye > div.YMlIz.FpEdX > span"));
            wait.until(ExpectedConditions.visibilityOf(priceText));
            price = priceText.getText();
            parsedPrice = Integer.parseInt(price.replace("$","").replace(",",""));
            putTravelDataInDatabase(destination, parsedPrice, dates[0],dates[1]);
            //allPrices.add(price);
        }



    }

    /**
     * Runs the assert method after the test that places data into the db is complete
     */
    @AfterClass
    public void AssertResults(){
        Flight cheapFlight = getCheapestFlightFromDB();
        System.out.println(cheapFlight);
        Assert.assertEquals(387,cheapFlight.getPrice());
    }



    /**
     *
     * @return list of dates from May to August in a list of [fromDate, toDate]
     */
    public ArrayList<String[]> getDates(){
        ArrayList<String[]> res = new ArrayList<String[]>();
        //navigate from month to month
        for(int currMonth = 5; currMonth < 9; currMonth++){
            String year = "2024-";
            YearMonth yearMonthObj = YearMonth.of(2024, 5);
            int daysInMonth = yearMonthObj.lengthOfMonth();
            //navigate on each day in each month and get the first and add 7
            int startDay = 1;
            for(int i = 7; i<=daysInMonth; i++){
                if(currMonth == 8 && i ==15){
                    String beginDate = year+currMonth+"-"+startDay;
                    String endDate = year+currMonth+"-"+(i);
                    String[] returnResult = {beginDate, endDate};
                    res.add(returnResult);
                    //System.out.println(Arrays.toString(returnResult));
                    break;
                }
                String beginDate = year+currMonth+"-"+startDay;
                String endDate = year+currMonth+"-"+(i);
                String[] returnResult = {beginDate, endDate};
                res.add(returnResult);
                startDay++;
                //System.out.println(Arrays.toString(returnResult));
            }
        }
        return res;

    }

    /**
     * Places the travel info into the database
     * @param destination
     * @param price
     * @param fromDate
     * @param toDate
     */
    private void putTravelDataInDatabase(String destination, int price, String fromDate, String toDate){
        String sql = "insert into flightData (destination, price, fromDate, toDate) values (?,?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, destination);
            ps.setInt(2, price);
            ps.setString(3, fromDate);
            ps.setString(4, toDate);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the cheapest flight from the database in the form of a Flight class
     * @return Flight
     */
    private Flight getCheapestFlightFromDB(){
        Flight cheapFlight = null;
        String sql = "select * from flightData where price = (select min(price) from flightData)";
        int count = 0;
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //Working on the result set
            while(rs.next()){
                String destination = rs.getString("destination");
                int price = rs.getInt("price");
                String fromDate = rs.getString("FromDate");
                String toDate = rs.getString("ToDate");
                cheapFlight = new Flight(destination, price, fromDate, toDate);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return cheapFlight;
    }

}
