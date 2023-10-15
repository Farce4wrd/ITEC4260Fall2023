import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.openqa.selenium.NoSuchElementException;

public class CarListingTest {


    private static EdgeDriver driver;
    private static Connection connection;

    @BeforeClass
    public static void setUp(){
        //System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:cars.sqlite");
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Test
    public void testCraigList(){
        driver.get("https://atlanta.craigslist.org/d/cars-trucks/search/cta");
       /* WebElement element = driver.findElement(By.cssSelector("#search-results-page-1 > ol"));
       //WebElement childElement = element.findElement(By.tagName("<li>"));*/
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebElement gallery = driver.findElement(By.id("search-results-page-1"));
        List<WebElement> cars = gallery.findElements(By.tagName("li"));
        for(WebElement car: cars){
            WebElement label = findElement(car, "label");
            WebElement price = findElement(car,"priceinfo");
            WebElement carLink = car.findElement(By.tagName("a"));
            String title = "";
            int myprice = -1;
            String url = "";
            String currentDate = (new Date()).toString();

            if(label != null){
                title = label.getText();
            }else{
                title = "mystery car";
            }
            if(price != null){
                myprice = Integer.parseInt(price.getText().replace("$","").replace(",",""));
            }else{
                System.out.println("no price given (free?)");
                myprice = -1;
            }
            if (carLink.getAttribute("href") != "") {
                url = carLink.getAttribute("href");
            } else {
                url = "";
            }
            addCarListingtoDatabase(title,myprice,url,currentDate);
        }


        Assert.assertEquals(120, cars.size());
        //String s = "https://atlanta.craigslist.org/d/cars-trucks/search/cta";
    }
    public WebElement findElement(WebElement element, String className){
        try{
            return element.findElement(By.className(className));
        }catch(NoSuchElementException e){
            return null;
        }
    }

    private void addCarListingtoDatabase(String title, int price, String url, String currentDate){
        String sql = "insert into cars (subject, price, url, timestamp) values (?,?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setInt(2, price);
            ps.setString(3, url);
            ps.setString(4, currentDate);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    @Test
    public void testDBConnection() throws SQLException{
        DatabaseMetaData meta = connection.getMetaData();
        //String str = connection.getSchema();
       //System.out.println(meta);
    }
    @Test
    public void testTableCount(){
        String sql = "select count(*) from cars";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println(rs.getInt("count(*)"));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void cleanUp() throws SQLException{
        driver.close();
        connection.close();
    }
}
