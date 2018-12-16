import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CreateNewRepositoryPage;
import pages.LoginPage;
import pages.MainPage;
import steps.Steps;


public class GitHubAutomationTest {
    private Steps steps;
    private final String USERNAME = "USERNAME";
	private final String PASSWORD = "PASSWORD";
	private final int REPOSITORY_NAME_POSTFIX_LENGTH = 6;

	private final String REPOSITORY_NAME_PREFIX = "testRepo_";

	@BeforeMethod(description = "Init browser")
	public void setUp()
	{
        steps = new Steps();
        steps.openBrowser();
	}

	@Test
	public void oneCanCreateProject()
	{
        steps.loginGithub(USERNAME, PASSWORD);
        String repositoryName = steps.generateRandomRepositoryNameWithCharLength(REPOSITORY_NAME_POSTFIX_LENGTH);
        steps.createNewRepository(repositoryName, "auto-generated test repo");
        Assert.assertEquals(steps.getCurrentRepositoryName(), repositoryName);
        Assert.assertTrue(steps.currentRepositoryIsEmpty());
	}

	@Test
	public void oneCanLoginGithub()
	{
        steps.loginGithub(USERNAME, PASSWORD);
        Assert.assertEquals(USERNAME, steps.getLoggedInUserName());
	}

	@Test
    public void repositoryName() {
        steps.loginGithub(USERNAME, PASSWORD);
        String repositoryName = steps.generateRandomRepositoryNameWithCharLength(REPOSITORY_NAME_POSTFIX_LENGTH);
        steps.createNewRepository(repositoryName, "auto-generated test repo");
        Assert.assertEquals(REPOSITORY_NAME_PREFIX.length() + REPOSITORY_NAME_POSTFIX_LENGTH, steps.getCurrentRepositoryName().length());
    }

	@Test
	public void checkRepositoryName() {
		steps.loginGithub(USERNAME, PASSWORD);
		String repositoryName = steps.generateRandomRepositoryNameWithCharLength(REPOSITORY_NAME_POSTFIX_LENGTH);
		steps.createNewRepository(repositoryName, "auto-generated test repo");
		steps.createNewRepository(repositoryName, "auto-generated test repo");
		Assert.assertNotEquals(steps.pageURL(), "https://github.com/" + USERNAME + "/" + repositoryName);
	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser()
	{
	    steps.closeBrowser();
	}
}
