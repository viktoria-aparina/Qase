package by.teachmeskills;

import by.teachmeskills.dto.Project;
import by.teachmeskills.pages.AllProjectsPage;
import by.teachmeskills.pages.NewProjectPage;
import by.teachmeskills.pages.ProjectPage;
import by.teachmeskills.providers.Providers;
import by.teachmeskills.steps.LoginSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTest {

    @BeforeMethod
    public void openAndLogin() {
        new LoginSteps().openAndLogin();
    }

    @Test
    public void deleteTest() {
        new AllProjectsPage().clickCreateNewProjectButton();
        Project project = new Providers().getProject();
        new NewProjectPage().createNewProject(project).submitForm();
        new ProjectPage().clickProjectsButton();
        new AllProjectsPage().open();

        new AllProjectsPage().deleteProject(project.getName()).isProjectDelete(project.getName());
    }
}
