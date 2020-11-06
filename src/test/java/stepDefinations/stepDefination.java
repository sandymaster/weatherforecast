package stepDefinations;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

@RunWith(Cucumber.class)
public class stepDefination {

	public static String path;
	public static WebDriver driver;
	public static String URL;
	public String city;
	public WebDriverWait configWait;
	public WebElement day;
	public WebElement hr;
	public WebElement cond;
	public WebElement rain;
	public WebElement min;
	public WebElement speed;
	public WebElement direction;
	public WebElement rainfall;
	public WebElement press;
	List<WebElement> hours;
	List<WebElement> conditions;
	List<WebElement> Rains;
	List<WebElement> mins;
	List<WebElement> speeds;
	List<WebElement> directions;
	List<WebElement> rainfalls;
	List<WebElement> pressures;
	
	// New for weather
  @Given("^Open the Browser navigate to URL$")
    public void Open_the_Browser_navigate_to_URL() throws Throwable {
 	  
	    path = System.getProperty("user.dir");
		URL = "http://localhost:3000/";
		
		System.setProperty("webdriver.chrome.driver", path+"/src/test/java/Drivers/chromedriver.exe");
			
			ChromeOptions options = new ChromeOptions();
			   System.setProperty("webdriver.chrome.silentOutput", "true");
			   options.addArguments("start-maximized");
		       options.addArguments("enable-automation"); 
			driver = new ChromeDriver(options);
			configWait = new WebDriverWait(driver,5);
			driver.get(URL);
    	
    }
  
  
  @When("^User enters the City \"([^\"]*)\" and Enter$")
  public void user_enters_the_City_and_Enter(String arg1) throws Throwable {
	  
	  driver.findElement(By.id("city")).clear();
	  city = arg1;
	  driver.findElement(By.id("city")).sendKeys(city);
	  System.out.println("User enters the City");
	  driver.findElement(By.id("city")).sendKeys(Keys.ENTER);
	  System.out.println("User Clicked Enter");
  }
  
  @Then("^System displays five day weather forecast$")
  public void System_displays_five_day_weather_forecast() throws Throwable {
	  
	  int count = driver.findElements(By.xpath("//span[@class='name']")).size();
	  try {
		  Assert.assertTrue(count==5);
		  System.out.println("Pass System has displayed all 5 day weather of "+city);
		  
	  }catch(Exception e) {
		  System.out.println("Failed to display all five day weather with error "+e.getMessage());
	  }
	  
  }
  


  @Then("^System displays three hourly forecast for day selection$")
  public void system_displays_three_hourly_forecast_for_day_selection() throws Throwable {
      List<WebElement> days = driver.findElements(By.xpath("//span[@class='name']"));
      Iterator<WebElement> it = days.iterator();
      
      int counter = 1;
    	  day = it.next();
    	  day.click();
    	  //Validation for the 3 days display
    	  String name = day.getAttribute("data-test");
    	  
    	  int count = driver.findElements(By.xpath("//div[@class='detail']//span[contains(@data-test,'hour-"+counter+"-')]")).size();
    	  hours = driver.findElements(By.xpath("//div[@class='detail']//span[contains(@data-test,'hour-"+counter+"-')]"));
    	  Iterator<WebElement> hrIt = hours.iterator();
    	   hr = hrIt.next();
    	  
    	  try {
    		  Assert.assertEquals(count, 3);
    		  System.out.println("Passed Total Hourly forecast populated for "+name+" is = "+count);
    	  }catch(Exception e) {
    		  System.out.println("Failed Total Hourly forecast populated for "+name+" is = "+count);
    	  }
    	  
    	  
  }
  
  @And("^User select day again and hides hourly forecast$")
  public void user_select_day_again_and_hides_hourly_forecast() throws Throwable {
	  
	  day.click();
	  
	  try {
		  Assert.assertFalse(hr.isSelected());
		  System.out.println("Pass Hour details not displayed on second click on day");
	  }catch(Exception e) {
		  System.out.println("Fail Hour details displayed on second click on day"+e.getMessage());
	  }

  }
  
  @And("^Daily forecast should summarize the hour data$")
  public void daily_forecast_should_summarize_the_hour_data() throws Throwable {
      
	 
		rain = driver.findElement(By.xpath("//div[@class='detail']//span[contains(@data-test,'maximum-1-1')]"));
		min = driver.findElement(By.xpath("//div[@class='detail']//span[contains(@data-test,'minimum-1-1')]"));
		speed = driver.findElement(By.xpath("//div[@class='detail']//span[contains(@data-test,'speed-1-1')]"));
		rainfall = driver.findElement(By.xpath("//div[@class='detail']//span[contains(@data-test,'rainfall-1-1')]"));
		press = driver.findElement(By.xpath("//div[@class='detail']//span[contains(@data-test,'pressure-1-1')]"));
		
		try {
			Assert.assertTrue(rain.isDisplayed());
			System.out.println("Pass maximum temperatures is present with value-"+rain.getText());
		}catch(Exception e) {
			System.out.println("Fail maximum temperatures not present with "+e.getMessage());
		}
		
		try {
			Assert.assertTrue(min.isDisplayed());
			System.out.println("Pass minimum temperatures is present with value-"+min.getText());
		}catch(Exception e) {
			System.out.println("Fail minimum temperatures not present with "+e.getMessage());
		}
		
		try {
			Assert.assertTrue(speed.isDisplayed());
			System.out.println("Pass wind speed is present with value-"+speed.getText());
		}catch(Exception e) {
			System.out.println("Fail wind speed not present with "+e.getMessage());
		}
		
		try {
			Assert.assertTrue(rainfall.isDisplayed());
			System.out.println("Pass Aggregate rainfall is present with value-"+rainfall.getText());
		}catch(Exception e) {
			System.out.println("Fail Aggregate rainfall not present with "+e.getMessage());
		}
		
		try {
			Assert.assertTrue(press.isDisplayed());
			System.out.println("Pass Pressure is present with value-"+press.getText());
		}catch(Exception e) {
			System.out.println("Fail Pressure not present with "+e.getMessage());
		}
		

  } 
  
  public static void closeBrowser() {
	  driver.close();
  }
}