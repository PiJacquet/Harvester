package scenarii;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import server.DataConfig;

public abstract class Scenario {
	
	protected WebDriver driver;
	
	public Scenario(WebDriver driver) {
		this.driver=driver;
		System.out.println("Loading of page " + DataConfig.getConnectUrl());
		driver.get(DataConfig.getConnectUrl());
		System.out.println("The page is now loaded!");
	}

	public Boolean connection() {

		WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement sub = driver.findElement(By.xpath("//button[@type='submit']"));

		username.sendKeys(DataConfig.getMail());
		password.sendKeys(DataConfig.getPassword());

		sub.click();
/*
		if(driver.getTitle().toUpperCase().startsWith("LINKEDIN")) {
			System.out.println("You are now connected with " + DataConfig.getMail());
			return true;
		}
		else {
			System.out.println("/!\\ Wrong mail/password : please check the config file!");
			return false;
		}
		*/
		return true;
	}
	
	public void startScenario() {
		if(connection())
			mining();
	}	
	
	public abstract void mining();
	
	public static void sleep(double d) {
		try {
			Thread.sleep((long) (d*1000));
		}catch(Exception e) {}
	}
}
