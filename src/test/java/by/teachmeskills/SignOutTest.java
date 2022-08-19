package by.teachmeskills;

import by.teachmeskills.pages.AllProjectsPage;
import by.teachmeskills.pages.LoginPage;
import by.teachmeskills.steps.LoginSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SignOutTest {

    @BeforeMethod
    public void openAndLogin() {
        new LoginSteps().openAndLogin();
    }

    @Test
    public void sighOutTest() {
        new AllProjectsPage().clickDropdownMenu();
        assertTrue(new LoginPage().getLoginButton().isDisplayed(), "The user wasn't sighed out");
    }
}
