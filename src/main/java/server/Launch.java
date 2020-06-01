package server;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import scenarii.HarvestMailsLinkedin;
import scenarii.Scenario;

public class Launch {


	public static void main(String[] args) {
		
		System.out.println("ZPi : Linkedin manager");
		System.out.println("Welcome! ");
		
		if(System.getProperty("os.name").startsWith("Windows"))
			System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriverWin.exe");
		else
			System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriverUnix");
		
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
		
		FirefoxOptions opt = new FirefoxOptions();
		//opt.addArguments("--headless");
		WebDriver driver = new FirefoxDriver(opt);

		DataConfig.getInstanceConfig();
		
		Scenario scenario = new HarvestMailsLinkedin(driver);
		scenario.startScenario();
		
		//close Firefox
		driver.close();

	}

}