package scenarii;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HarvestMailsLinkedin extends Scenario{

	protected Scanner sc;

	public HarvestMailsLinkedin(WebDriver driver){
		super(driver);
		sc = new Scanner(System.in);
	}

	@Override
	public void mining() {
		System.out.println("I'll now begin to harvest mails. Which company do you want to focus?");
		System.out.print("> ");
		String company = sc.nextLine();
		System.out.println("Good choice! Let's harvest mails related to '" + company + "'");
		// Go to the company page
		driver.get("https://www.linkedin.com/company/" + company);
		// Load employees
		driver.findElement(By.xpath("//a[@data-control-name='topcard_see_all_employees']")).click();

		do {
			// We have to scroll because the content is loaded dynamically
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,(document.body.scrollHeight)*0.3)");
			sleep(1);
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,(document.body.scrollHeight)*0.7)");
			sleep(1);

			List<WebElement> spanList = driver.findElements(By.xpath("//span[@class='name actor-name']"));
			for(WebElement span : spanList) {
				String prenom = (span.getText().substring(0, span.getText().indexOf(' '))).toLowerCase();
				String nom = (span.getText().substring(span.getText().indexOf(' ')+1, span.getText().length())).toLowerCase();
				nom.replace(' ', '-');
				System.out.println(prenom + "." + nom + "@" + company + ".com");
			}
			
			driver.findElement(By.xpath("//button[contains(@class, 'artdeco-pagination__button--next')]")).click();
			sleep(2);
		}while(true);
	}



}
