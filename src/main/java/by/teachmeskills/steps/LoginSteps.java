package by.teachmeskills.steps;

import by.teachmeskills.pages.AllProjectsPage;
import by.teachmeskills.pages.LoginPage;
import com.codeborne.selenide.Selenide;

public class LoginSteps {

    LoginPage loginPage;
    AllProjectsPage allProjectsPage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
        this.allProjectsPage = new AllProjectsPage();
    }

    public AllProjectsPage openAndLogin() {
        Selenide.open("/login");
        loginPage.loginWithValidCredential();
        return allProjectsPage;
    }
}
