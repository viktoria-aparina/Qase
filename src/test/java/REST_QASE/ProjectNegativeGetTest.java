package REST_QASE;

import REST_QASE.clients.ProjectApiClients;
import REST_QASE.dto.Project;
import REST_QASE.dto.ProjectResponse;
import REST_QASE.providers.ProjectProvider;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectNegativeGetTest {

    ProjectApiClients projectApiClients = new ProjectApiClients();

    @Test
    public void getProjectWithNonExistentCodeTest() {

        ProjectResponse postActualProject = projectApiClients.getProject("321", HttpStatus.SC_NOT_FOUND);

        assertThat(postActualProject.isStatus()).as("Project was found without code").isEqualTo(false);
        assertThat(postActualProject.getErrorMessage()).as("The error message is incorrect")
                                                       .isEqualTo("Project is not found.");
    }

    @Test
    public void getProjectWithEmptyCodeTest() {

        ProjectResponse postActualProject = projectApiClients.getProject(" ", HttpStatus.SC_NOT_FOUND);

        assertThat(postActualProject.isStatus()).as("Project was found without code").isEqualTo(false);
        assertThat(postActualProject.getErrorMessage()).as("The error message is incorrect")
                                                       .isEqualTo("Project code is invalid");
    }

    @Test
    public void getProjectWithInvalidToken() {

        Project expectedProject = new ProjectProvider().getProject();
        ProjectResponse postActualProject = projectApiClients.getWithInvalidTokenProject(expectedProject.getCode(), HttpStatus.SC_UNAUTHORIZED);

        assertThat(postActualProject.getErrorMessage()).as("Error message is incorrect")
                                                       .isEqualTo("API token is invalid");
    }
}
