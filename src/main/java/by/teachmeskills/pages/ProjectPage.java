package by.teachmeskills.pages;

import by.teachmeskills.dto.ProjectAccess;
import by.teachmeskills.dto.Project;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.By.*;
import static org.openqa.selenium.By.id;

@Log4j2
public class ProjectPage {

    public AllProjectsPage clickProjectsButton() {
        $(xpath("//a[text()='Projects']")).click();
        $(id("createButton")).shouldBe(enabled);
        return new AllProjectsPage();
    }

    public ProjectPage clickDropdownButton(String projectName) {
        $x(String.format("//a[text()='%s']//ancestor::tr[@class='project-row']//a[contains(@class,'btn-dropdown')]", projectName)).click();
        $x(String.format("//a[text()='%s']//ancestor::tr[@class='project-row']//a[text()='Settings']", projectName)).click();
        return this;
    }

    public Project getActualProjectInfo() {
        Project actualProject = Project.builder()
                .name($(id("inputTitle")).getValue())
                .code($(id("inputCode")).getValue())
                .description($(id("inputDescription")).getValue())
                .accessLevel(ProjectAccess.valueOf($(xpath("//input[@checked]")).getValue().toUpperCase()))
                .build();
        log.info("The project was created successfully");
        return actualProject;
    }

    public AllProjectsPage deleteProject() {
        $x("//a[contains(@class, 'btn-cancel')]").click();
        $x("//button[contains(@class, 'btn-cancel')]").click();
        return new AllProjectsPage();
    }
}
