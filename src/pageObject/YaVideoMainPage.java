package pageObject;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class YaVideoMainPage 
{
	
	public WebDriver webDriver;
	
	public YaVideoMainPage(WebDriver driver)
	{
		webDriver = driver;
		PageFactory.initElements(driver, this);
	}
    
    public WebElement SearchField()
    {
       WebElement element = webDriver.findElement(By.className("input__control"));
       return element;
    }
    
    public WebElement SearchButton()
    {
       WebElement element = webDriver.findElement(By.className("search2__button"));
       return element;
    }
    
    public void Open()
    {
    	webDriver.navigate().to("https://yandex.ru/video/");
    	webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    
    public void SearchForVideos(String searchString) throws InterruptedException
    {
    	
    	SearchField().sendKeys(searchString);
    	SearchButton().click();
    	webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	new WebDriverWait(webDriver, 20).until(
    	          webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    	Thread.sleep(5000);
    }
    
    public WebElement GetFirstVideoTrailer()
    {	
    	List<WebElement> elemList = webDriver.findElements(By.cssSelector(".thumb-image__image"));
    	return elemList.get(1);
    }
    
    public boolean CheckForTreiler(WebElement video) throws InterruptedException
    {
    	boolean result = false;
    	String elem = 
    			video.getAttribute("src");

    	List<String> listOfUrls = new ArrayList<String>();
    	
    	MoveToElement(video);
    	for(int i = 0; i< 50; i++)
    	{
    		listOfUrls.add(GetFirstVideoTrailer().getAttribute("src"));
    		Thread.sleep(250);
    		i++;
    	}
    	
    	if((Collections.frequency(listOfUrls, elem) != listOfUrls.size()))
    		result = true;
    	
    	return result;
    }
    
    private void MoveToElement(WebElement element)
    {
    	Actions builder  = new Actions(webDriver);
        builder.moveToElement(element).build().perform();
        //Thread.sleep(5000);
    }
    

}
