package locators;

import java.time.Duration; 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class login {
	
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver d1 = new ChromeDriver();
		d1.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		d1.manage().window().maximize();
		
		d1.get("https://www.facebook.com/r.php?locale=en_GB&display=page");
		d1.findElement(By.cssSelector("#fullname_field input[name='firstname']")).sendKeys("Firstname001");
		d1.findElement(By.cssSelector("#fullname_field input[name='lastname']")).sendKeys("LastName001");
		
		d1.findElement(By.cssSelector("#reg_form_box input[name='reg_email__']")).sendKeys("demo_mail001@yopmail.com");
		d1.findElement(By.cssSelector("#reg_form_box input[name='reg_email_confirmation__']")).sendKeys("demo_mail001@yopmail.com");
		
		//select day
		WebElement day_dropdown = d1.findElement(By.id("day"));
		Select day_dropdown_options = new Select(day_dropdown);
		day_dropdown_options.selectByValue("10");
		System.out.println(day_dropdown_options.getFirstSelectedOption().getText());
		//select month
		WebElement month_dropdown = d1.findElement(By.id("month"));
		Select month_dropdown_options = new Select(month_dropdown);
		month_dropdown_options.selectByVisibleText("Aug");
		System.out.println(month_dropdown_options.getFirstSelectedOption().getText());
		//select birth year
		WebElement year_dropdown = d1.findElement(By.id("year"));
		Select year_dropdown_options = new Select(year_dropdown);
		year_dropdown_options.selectByVisibleText("2000");
		System.out.println(year_dropdown_options.getFirstSelectedOption().getText());
		
		//gender
		WebElement maleRadioButtonText = d1.findElement(By.xpath("//span[@data-name='gender_wrapper'] //label[text()='Male']"));
		maleRadioButtonText.click();
		
		
		Thread.sleep(2000);
		
		Assert.assertTrue(d1.findElement(By.xpath("//span[@data-name='gender_wrapper'] //input[@value='2']")).isSelected(), "Male is not selected !!");

		//		d1.findElement(By.xpath("//span[@data-name='gender_wrapper'] //input[@value='1']")).click();
		
		//new password
		d1.findElement(By.id("password_step_input")).sendKeys("Abcd@1234");
		
		//submit
		d1.findElement(By.cssSelector("button[name='websubmit']")).click();
		
		WebElement error_element = d1.findElement(By.id("reg_error_inner"));
		//wait
		WebDriverWait wait = new WebDriverWait(d1, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(error_element));
		
		String reg_error_text = error_element.getText();
		System.out.println(reg_error_text);
		
		Assert.assertEquals(reg_error_text, "This name contains certain characters that aren't allowed. Learn more about our name policies.");
		
		//close browser
		login.closeAllBrowserWindows(d1);
		
	}
	
	public static void closeAllBrowserWindows(WebDriver driver) throws InterruptedException {
		Thread.sleep(10000);
		driver.quit();
	}

}
