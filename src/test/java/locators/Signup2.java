package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Signup2 extends Base_test {
	
	public static void main(String[] args) {
		
		Signup2 signup2 = new Signup2();
		
		// Launch browser
		String base_url = "https://www.facebook.com/r.php?locale=en_GB&display=page";
		WebDriver d1 = signup2.launchBrowser(base_url, "chrome");// chrome/fireFox/edge
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
		
		signup2.sendTextValue(firstname, "name001");
		signup2.sendTextValue(lastname, "name002");
		signup2.sendTextValue(usermail, "demo_mail001@yopmail.com");
		signup2.sendTextValue(confirm_mail, "demo_mail001@yopmail.com");
		signup2.sendTextValue(password, "Abc@12345");
		
		signup2.clickOnElement(gender_male_radiobox, 10);
		
		signup2.selectValueFromDropdownBy("value", "10", day_dropdown);
		signup2.selectValueFromDropdownBy("index", "5", month_dropdown);
		signup2.selectValueFromDropdownBy("text", "1998", year_dropdown);
		
		signup2.clickOnElement(Signup_button, 10);
		
		String expected_error_message01 = signup2.getElementTextValue(error_element, 10);
		String actual_error_message01 = "This name contains certain characters that aren't allowed. Learn more about our name policies.";
		System.out.println(actual_error_message01);
		Assert.assertEquals(expected_error_message01, actual_error_message01);
		
		signup2.closeAllWindowsOfBrowser();
	
	}

}
