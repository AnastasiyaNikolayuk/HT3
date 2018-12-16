package steps;

import driver.DriverSingleton;
import utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pages.CreateNewRepositoryPage;
import pages.LoginPage;
import pages.MainPage;


public class Steps {
    private WebDriver driver;
    private final Logger logger = LogManager.getRootLogger();

    public void openBrowser()
	{
	    driver = DriverSingleton.getDriver();
	}

	public void closeBrowser()
 	{
 	    DriverSingleton.closeDriver();
 	}

 	public String pageURL() {
        return driver.getCurrentUrl();
    }

 	public void loginGithub(String username, String password)
	{
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public String getLoggedInUserName()
	{
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.getLoggedInUserName().trim().toLowerCase();
    }

    public void createNewRepository(String repositoryName, String repositoryDescription)
	{
	    MainPage mainPage = new MainPage(driver);
        mainPage.clickOnCreateNewRepositoryButton();
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
	}

	public boolean currentRepositoryIsEmpty()
	{
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        return createNewRepositoryPage.isCurrentRepositoryEmpty();
	}

	public String getCurrentRepositoryName(){
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        return createNewRepositoryPage.getCurrentRepositoryName();
    }

    public String generateRandomRepositoryNameWithCharLength(int howManyChars){
        String repositoryNamePrefix = "testRepo_";
        return repositoryNamePrefix.concat(Utils.getRandomString(howManyChars));
    }
}
