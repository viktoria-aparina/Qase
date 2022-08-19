package by.teachmeskills.pages;

import by.teachmeskills.utils.PropertiesLoader;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;

@Log4j2
@Listeners({ScreenShooter.class})
public class LoginPage {

    public LoginPage open() {
        Selenide.open("/login");
        return this;
    }

    public AllProjectsPage loginWithValidCredential() {
        $(id("inputEmail")).sendKeys(PropertiesLoader.loadProperties().getProperty("login"));
        $(id("inputPassword")).sendKeys(PropertiesLoader.loadProperties().getProperty("password"));
        $(id("btnLogin")).click();
        log.info("User was logged in successfully");
        return new AllProjectsPage();
    }

    public SelenideElement getLoginButton() {
        return $(id("btnLogin")).shouldBe(enabled);
    }
}
