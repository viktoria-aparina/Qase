package by.teachmeskills;

import by.teachmeskills.dto.Project;
import by.teachmeskills.pages.AllProjectsPage;
import by.teachmeskills.pages.NewProjectPage;
import by.teachmeskills.pages.ProjectPage;
import by.teachmeskills.providers.ProjectProvider;
import by.teachmeskills.steps.DeleteProjectSteps;
import by.teachmeskills.steps.LoginSteps;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;


public class ProjectTest {

    @BeforeMethod
    public void openAndLogin() {
        new LoginSteps().openAndLogin();
    }

    @Test
    public void createNewProjectTest() {

        new AllProjectsPage().clickCreateNewProjectButton();
        assertTrue(new NewProjectPage().getTitle().isDisplayed());

        Project project = new ProjectProvider().getProject();
        new NewProjectPage().createNewProject(project).submitForm();

        new ProjectPage().clickProjectsButton();
        new AllProjectsPage().open();

        SelenideElement projectName =$x(String.format("//a[text()='%s']//ancestor::tr[@class='project-row']", project.getName()));

        assertThat(projectName).as("The project doesn't exist on page with all projects").isNotNull();

        Project actualProject = new ProjectPage().clickDropdownButton(project.getName())
                                                 .getActualProjectInfo();

        assertThat(actualProject).as("Projects are different").isEqualTo(project);
    }

    @AfterMethod
    public void deleteProject() {
        new DeleteProjectSteps().deleteProject();
    }
}
