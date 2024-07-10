package locators;

import java.time.Duration; 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Signup {
	
	
	
	public static void main(String[] args) throws InterruptedException {
		// Launce browser
		String base_url = "https://www.facebook.com/r.php?locale=en_GB&display=page";
		WebDriver d1 = launchBrowser(base_url, "fireFox");// chrome/fireFox/edge
		//WebElments
		WebElement firstname = d1.findElement(By.cssSelector("#fullname_field input[name='firstname']"));
		WebElement lastname = d1.findElement(By.cssSelector("#fullname_field input[name='lastname']"));
		WebElement usermail = d1.findElement(By.cssSelector("#reg_form_box input[name='reg_email__']"));
		WebElement confirm_mail = d1.findElement(By.cssSelector("#reg_form_box input[name='reg_email_confirmation__']"));
		WebElement password = d1.findElement(By.cssSelector("#reg_form_box input[type='password']"));
		WebElement day_dropdown = d1.findElement(By.id("day"));
		WebElement month_dropdown = d1.findElement(By.id("month"));
		WebElement year_dropdown = d1.findElement(By.id("year"));
		WebElement gender_male_radiobox = 
		d1.findElement(By.xpath("//span[@data-name='gender_wrapper'] //label[text()='Male']"));
		WebElement Signup_button = d1.findElement(By.cssSelector("button[name='websubmit']"));
		WebElement error_element = d1.findElement(By.id("reg_error_inner"));
		
		sendTextValue(firstname, "name001");
		sendTextValue(lastname, "name002");
		sendTextValue(usermail, "demo_mail001@yopmail.com");
		sendTextValue(confirm_mail, "demo_mail001@yopmail.com");
		sendTextValue(password, "Abc@12345");
		
		clickOnElement(gender_male_radiobox, 10, d1);
		
		selectValueFromDropdownBy("value", "10", day_dropdown);
		selectValueFromDropdownBy("index", "5", month_dropdown);
		selectValueFromDropdownBy("text", "1998", year_dropdown);
		
		clickOnElement(Signup_button, 10, d1);
		
		String expected_error_message01 = getElementTextValue(error_element, 10, d1);
		String actual_error_message01 = "This name contains certain characters that aren't allowed. Learn more about our name policies.";
		System.out.println(actual_error_message01);
		Assert.assertEquals(expected_error_message01, actual_error_message01);
		
		closeAllWindowsOfBrowser(d1);
	
	}
	
	
	public static WebDriver launchBrowser(String base_url, String browser_type) {
		
		WebDriver d1 = null;
		
		if(browser_type.equals("chrome")){
		 d1 = new ChromeDriver();
		}else if(browser_type.equals("edge")){
			d1 = new EdgeDriver();
		}else if (browser_type.equals("fireFox")) {
			d1 = new FirefoxDriver();
		}
	
		d1.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		d1.manage().window().maximize();
		d1.get(base_url);
		return d1;
	}
	
	public static void selectValueFromDropdownBy(String selectBy, String dropdownValue, WebElement element) {
		
		Select select = new Select(element);
		
		if(selectBy.equals("value")){
			
			select.selectByValue(dropdownValue);
		
	    }else if(selectBy.equals("index")){
	    	int index = Integer.parseInt(dropdownValue);
			select.selectByIndex(index);
	    }else if(selectBy.equals("text")){
	    	select.selectByVisibleText(dropdownValue);
	    }else {
//	    	throw new WrongParameterValueException("1st parameter should be either of value/index/text");
	    }
		
	}
	
	public static void sendTextValue(WebElement element, String textValue) {
		element.sendKeys(textValue);
	}
	
	public static void closeAllWindowsOfBrowser(WebDriver driver) throws InterruptedException {
		Thread.sleep(15000);
		driver.quit();
	}
	
	public static void clickOnElement(WebElement element, int timeInSeconds, WebDriver d1) {
		WebDriverWait wait = new WebDriverWait(d1, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();	
		}
	
	public static String getElementTextValue(WebElement element, int timeInSeconds, WebDriver d1) {
		WebDriverWait wait = new WebDriverWait(d1, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

}
