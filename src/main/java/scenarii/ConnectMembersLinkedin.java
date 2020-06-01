package scenarii;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConnectMembersLinkedin extends Scenario {

	protected Scanner sc;
	protected int accountVisited;

	public ConnectMembersLinkedin(WebDriver driver) {
		super(driver);
		sc = new Scanner(System.in);
		accountVisited=0;
	}

	@Override
	public void mining() {
		//System.out.println("I'll now begin to connect accounts. With which keywords related accounts do you want to connect?");
		//System.out.print("> ");
		//String keywords = sc.nextLine();
		//System.out.println("Good choice! Let's add accounts related to '" + keywords + "'");

		//driver.get(DataConfig.getSearchUrl() + keywords);
		//driver.get("https://www.linkedin.com/search/results/people/?keywords=natixis");
		driver.get("https://www.linkedin.com/search/results/people/?facetGeoRegion=%5B%22fr%3A0%22%5D&facetIndustry=%5B%2296%22%2C%224%22%2C%226%22%2C%22118%22%5D&keywords=alternant&origin=FACETED_SEARCH&page=82");
		//System.out.println("Loading of url "+ DataConfig.getSearchUrl() + keywords);
		sleep(1);
		int compteur = 0;
		do {
			// We have to scroll because the content is loaded dynamically
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,(document.body.scrollHeight)*0.3)");
			sleep(1);
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,(document.body.scrollHeight)*0.7)");
			sleep(1);

			List<WebElement> connectLinks = driver.findElements(By.xpath("//a[@class='search-result__result-link ember-view']"));
			for(WebElement link : connectLinks) {
				if ((compteur++ % 2)!=0)
					continue; //each link is doubled for some reason in the array, so we skip the second one
				System.out.println("Visit the account " + link.getAttribute("href") /*.substring(20, button.getAttribute("aria-label").length())*/);
				visitAccount(link.getAttribute("href"));
				//link.click();
				//driver.findElement(By.xpath("//button[@class='send-invite__cancel-btn']")).click();
			}
			System.out.println("Account visited : " + accountVisited);
			driver.findElement(By.xpath("//button[contains(@class, 'artdeco-pagination__button artdeco-pagination__button--next')]")).click();
		}while(true);
	}

	private void visitAccount(String url) {
		//Open a new tab
		((JavascriptExecutor) driver).executeScript("window.open('" + url + "','_blank')");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,(document.body.scrollHeight)*0.3)");
		sleep(2.2);
		
		//Close the tab
		((JavascriptExecutor) driver).executeScript("window.close()");
		driver.switchTo().window(tabs.get(0));
		accountVisited++;
	}
}
