package autotest;
import pageObject.*;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class CheckTrailerWhileHoverOnVideo 
{

  WebDriver driver;
	  
  @BeforeMethod
  public void beforeMethod() 
  {
	String exePath = "C:\\QA\\geckodriver.exe";
	System.setProperty("webdriver.gecko.driver", exePath);
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
  }
  
  @Test
  public void CheckTrailerWhileHoverOnVideoExists() throws InterruptedException
  {
	  YaVideoMainPage page = new YaVideoMainPage(driver);
	  page.Open();
	  page.SearchForVideos("Котики");
	  WebElement video = page.GetFirstVideoTrailer();
	  
	  if(!page.CheckForTreiler(video))
		  Assert.fail("Трейлера нет");
  }
  
  @AfterMethod
  public void afterMethod() 
  {
	  driver.quit();
  }

}
