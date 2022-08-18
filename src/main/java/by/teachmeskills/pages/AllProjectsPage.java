package by.teachmeskills.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.tagName;

@Log4j2
public class AllProjectsPage {

    public AllProjectsPage open() {
        Selenide.open("/projects");
        return new AllProjectsPage();
    }

    public NewProjectPage clickCreateNewProjectButton() {
        $(By.id("createButton")).click();
        return new NewProjectPage();
    }

    public AllProjectsPage deleteProject(String projectName) {
        $x(String.format("//a[text()='%s']//ancestor::tr[@class='project-row']//a[contains(@class,'btn-dropdown')]", projectName)).click();
        $x(String.format("//a[text()='%s']//ancestor::tr[@class='project-row']//a[text()='Delete']", projectName)).click();
        $x("//button[contains(@class, 'btn-cancel')]").click();
        $(tagName("h1")).shouldBe(visible);
        log.info("The project was deleted");
        return this;
    }

    public LoginPage clickDropdownMenu() {
        $x("//span[@class='Eb2vGG']").click();
        $x("//span[text()='Sign out']").click();
        $(id("btnLogin")).shouldBe(enabled);
        return new LoginPage();
    }

    public boolean isProjectDelete(String name) {

        try {
            SelenideElement projectName =$x(String.format("//a[text()='%s']//ancestor::tr[@class='project-row']", name));
            return true;
        } catch (NoSuchElementException exception) {
            log.error("The project {} was not find, because of error {}", name, exception.getCause());
            return false;
        }
    }
}
