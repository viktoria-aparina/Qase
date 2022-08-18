package by.teachmeskills;

import by.teachmeskills.pages.LoginPage;
import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.id;

public class LoginTest {

    @Test
    public void loginWithValidCredential() {
        ScreenShooter.captureSuccessfulTests = true;
        new LoginPage().open()
                       .loginWithValidCredential();
        getWebDriver().manage().window().maximize();

        $(id("createButton")).shouldHave(text("Create new project"));

        assertThat($(id("createButton")).getText()).as("The user wasn't logged in")
                                                   .isEqualTo("Create new project");
    }
}
