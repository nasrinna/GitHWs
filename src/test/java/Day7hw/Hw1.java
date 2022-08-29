package Day7hw;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class Hw1 {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		setUp();
		// test1();
		// test2();
		// test3();
		// test4();
		test5();
		// test6();

	}

	public static void setUp() {
		String key = "webdriver.chrome.driver";
		String path = "/Users/nasrinnoorahmad/Desktop/B7-Selenium/chromedriver";
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");

		System.setProperty(key, path);
		driver = new ChromeDriver();

		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().window().maximize();

	}

	public static void test1() throws InterruptedException {
		System.out.println("running");
		driver.get("http://demo.guru99.com/test/simple_context_menu.html");

		WebElement el = driver.findElement(By.xpath("//*[text()='right click me']"));

		Actions action = new Actions(driver);

		action.contextClick(el).build().perform();

		WebElement copy = driver.findElement(By.xpath("//*[text()='Copy']"));
		copy.click();
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		System.out.println(text);

		alert.accept();

		System.out.println("done");
		driver.quit();

	}

	public static void test2() throws InterruptedException {
		System.out.println("running");
		driver.get("http://demo.guru99.com/test/simple_context_menu.html");

		WebElement el = driver.findElement(By.cssSelector("button[ondblclick]"));

		Actions action = new Actions(driver);

		action.doubleClick(el).build().perform();
		Thread.sleep(3000);

		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();

		System.out.println("done");

		driver.close();

	}

	public static void test3() throws InterruptedException {

		System.out.println("Running");
		driver.get("http://dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");

		WebElement parentElementCap = driver.findElement(By.cssSelector("#dropContent"));
		List<WebElement> allChildElementsCap = parentElementCap.findElements(By.xpath("*"));

		WebElement parentElementCoun = driver.findElement(By.cssSelector("#countries"));
		List<WebElement> allChildElementsCoun = parentElementCoun.findElements(By.xpath("*"));

		Actions action = new Actions(driver);

		for (WebElement cap : allChildElementsCap) {
			String capital = cap.getAttribute("id");
			if (capital.startsWith("box")) {
				int caplen = capital.length();
				for (WebElement coun : allChildElementsCoun) {
					String country = coun.getAttribute("id");
					int counlen = country.length();
					if (capital.substring(caplen - 1).equals(country.substring(counlen - 1))) {
						action.clickAndHold(cap).moveToElement(coun).release().build().perform();
					}

				}
			}

		}

		Thread.sleep(3000);

		System.out.println("Done..... :)");

		driver.close();

	}

	public static void test4() throws InterruptedException {
		System.out.println("Running");
		driver.get("https://opensource-demo.orangehrmlive.com");

		Thread.sleep(5000);

		WebElement user = driver.findElement(By.cssSelector("input[name='username']"));
		user.sendKeys("Admin");

		WebElement password = driver.findElement(By.cssSelector("input[name='password']"));
		password.sendKeys("admin123");

		WebElement login = driver.findElement(By.cssSelector("button[type]"));
		login.click();

		WebElement admin = driver.findElement(By.xpath("//span[text() = 'Admin']"));
		admin.click();

		WebElement jobElement = driver.findElement(By.xpath("//span[contains(text(),'Job ')]"));
		jobElement.click();

		WebElement jobTitle = driver.findElement(By.xpath("//a[contains(text(),'Job Titles')]"));
		jobTitle.click();

		WebElement add = driver.findElement(By.cssSelector("button[data-v-7e88b27e]"));
		add.click();

		Thread.sleep(5000);

		WebElement job_title = driver
				.findElement(By.xpath("//form[contains(@class,'oxd-form')]//div[1]//div[1]//div[2]//input"));
		job_title.sendKeys("SDET");

		WebElement jobDiscription = driver
				.findElement(By.xpath("//form[contains(@class,'oxd-form')]//div[2]//div[1]//div[2]//textarea"));
		jobDiscription.sendKeys("Software Development Engineer in Test");

		WebElement notes = driver
				.findElement(By.xpath("//form[contains(@class,'oxd-form')]//div[4]//div[1]//div[2]//textarea"));
		notes.sendKeys("I am qualified for SDET");

		WebElement save = driver.findElement(By.xpath("//form[contains(@class,'oxd-form')]//div[5]//button[2]"));
		save.click();

		Thread.sleep(5000);

		try {
			WebElement jobExists = driver
					.findElement(By.xpath("//form[contains(@class,'oxd-form')]//div[1]//div[1]//span"));
			System.out.println(jobExists.getText());
			WebElement cancel = driver.findElement(By.xpath("//form[contains(@class,'oxd-form')]//div[5]//button[1]"));
			cancel.click();

		} catch (Exception e) {
		}

		Thread.sleep(5000);
		List<WebElement> list = driver
				.findElements(By.xpath("//div[contains(@class,'oxd-table-body')]//div/div//div[2]"));

		boolean isFound = false;

		for (WebElement el : list) {
			if (el.getText().equals("SDET")) {
				isFound = true;
				break;
			}

		}
		Assert.assertTrue(isFound, "Value Not Found");
		driver.close();

	}

	public static void test5() throws InterruptedException {
		System.out.println("Running");
		driver.get("https://opensource-demo.orangehrmlive.com");

		Thread.sleep(5000);

		WebElement user = driver.findElement(By.cssSelector("input[name='username']"));
		user.sendKeys("Admin");

		WebElement password = driver.findElement(By.cssSelector("input[name='password']"));
		password.sendKeys("admin123");

		WebElement login = driver.findElement(By.cssSelector("button[type]"));
		login.click();

		WebElement emp_id = driver
				.findElement(By.xpath("//div[@class='oxd-grid-4 orangehrm-full-width-grid']/div[2]//div[2]/input"));
		emp_id.click();
		emp_id.sendKeys("0016");

		WebElement emp_dropDown = driver
				.findElement(By.xpath("//div[@class='oxd-grid-4 orangehrm-full-width-grid']//div[3]//div[2]//div"));
		emp_dropDown.click();
		WebElement emp_list = driver.findElement(By.xpath("//div[@role='listbox']"));
		emp_list.findElement(By.xpath(".//*[contains(text(),'Full-Time Permanent')]")).click();

		WebElement include = driver
				.findElement(By.xpath("//div[@class='oxd-grid-4 orangehrm-full-width-grid']//div[4]"));
		include.click();
		WebElement include_list = driver.findElement(By.xpath("//div[@role='listbox']"));
		include_list.findElement(By.xpath(".//*[contains(text(),'Current and Past Employees')]")).click();

		WebElement sub_unit = driver
				.findElement(By.xpath("//div[@class='oxd-grid-4 orangehrm-full-width-grid']//div[7]"));
		sub_unit.click();
		WebElement subUnit_list = driver.findElement(By.xpath("//div[@role='listbox']"));

		// search
		subUnit_list.findElement(By.xpath(".//*[contains(text(),'Administration')]")).click();
		WebElement search_b = driver.findElement(By.cssSelector("button[type='submit']"));
		search_b.click();

		Thread.sleep(3000);

		List<WebElement> list = driver.findElements(By.xpath("//div[@role='cell']"));

		Assert.assertTrue(list.get(2).getText().equals("Linda Jane"), "Value Not Found Name");
		Assert.assertTrue(list.get(3).getText().equals("Anderson"), "Value Not Found lastname");

		Boolean employee_status = list.get(4).getText().equals("VP - Sales & Marketing")
				|| list.get(4).getText().equals("FreeLance");
		Assert.assertTrue(employee_status, "Correct job Title");

		String[] s = list.get(7).getText().split(" ");
		System.out.println(s[0]);

		Assert.assertTrue(list.get(6).getText().length() == 2, "Wrong size");

	}

	public static void test6() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));

		Thread.sleep(2000);

		WebElement username = driver.findElement(By.xpath("//input[@name='username']"));

		username.sendKeys("Admin");

		WebElement password = driver.findElement(By.xpath("//input[@name='password']"));

		password.sendKeys("admin123");

		WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));

		button.click();

		WebElement empStatDiv = driver
				.findElement(By.xpath("//div[@class='oxd-grid-4 orangehrm-full-width-grid']/div[3]"));

		empStatDiv.click();

		WebElement listbox = driver.findElement(By.xpath("//div[@role='listbox']"));

		listbox.findElement(By.xpath(".//*[contains(text(), 'Full-Time Permanent')]")).click();

	}

}
