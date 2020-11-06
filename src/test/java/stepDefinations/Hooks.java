package stepDefinations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	@Before("@Weather")
	public void OpenBrower()
	{
		System.out.println("Open Browser...");
		
	}
	
	
	@After("@Weather")
	public void Aftervalidation()
	{
		System.out.println("Closing Browser...");
		stepDefination.closeBrowser();
	}
		

}

