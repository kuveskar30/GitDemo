package locators;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base_test {

	WebDriver d1;

	public WebDriver launchBrowser(String base_url, String browser_type) {



		if (browser_type.equals("chrome")) {
			d1 = new ChromeDriver();
		} else if (browser_type.equals("edge")) {
			d1 = new EdgeDriver();
		} else if (browser_type.equals("fireFox")) {
			d1 = new FirefoxDriver();
		}

		d1.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		d1.manage().window().maximize();
		d1.get(base_url);
		return d1;
	}

	public void selectValueFromDropdownBy(String selectBy, String dropdownValue, WebElement element) {

		Select select = new Select(element);

		if (selectBy.equals("value")) {

			select.selectByValue(dropdownValue);

		} else if (selectBy.equals("index")) {
			int index = Integer.parseInt(dropdownValue);
			select.selectByIndex(index);
		} else if (selectBy.equals("text")) {
			select.selectByVisibleText(dropdownValue);
		} else {
//	    	throw new WrongParameterValueException("1st parameter should be either of value/index/text");
		}

	}

	public void sendTextValue(WebElement element, String textValue) {
		element.sendKeys(textValue);
	}

	public void closeAllWindowsOfBrowser() {
		try{
			Thread.sleep(15000);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		d1.quit();
	}

	public void clickOnElement(WebElement element, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(d1, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public String getElementTextValue(WebElement element, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(d1, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

}
