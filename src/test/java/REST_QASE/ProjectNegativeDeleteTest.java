package REST_QASE;

import REST_QASE.clients.ProjectApiClients;
import REST_QASE.dto.Project;
import REST_QASE.dto.ProjectResponse;
import REST_QASE.providers.ProjectProvider;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectNegativeDeleteTest {

    ProjectApiClients projectApiClients = new ProjectApiClients();

    @Test
    public void deleteWithInvalidCodeTest() {

        ProjectResponse deletedProject = projectApiClients.deleteProject("321", HttpStatus.SC_NOT_FOUND);

        assertThat(deletedProject.isStatus()).as("The project wasn't deleted")
                                             .isEqualTo(false);

        assertThat(deletedProject.getErrorMessage()).as("The error message is incorrect")
                                                    .isEqualTo("Project is not found.");
    }

    @Test
    public void deleteWithInvalidTokenTest() {

        Project expectedProject = new ProjectProvider().getProject();
        ProjectResponse postActualProject = projectApiClients.postProject(expectedProject, HttpStatus.SC_OK);
        ProjectResponse deletedProject = projectApiClients.deleteWithInvalidTokenProject(postActualProject.getResult()
                                                                                                          .getCode(), HttpStatus.SC_UNAUTHORIZED);

        assertThat(deletedProject.isStatus()).as("The project wasn't deleted")
                                             .isEqualTo(false);

        assertThat(deletedProject.getErrorMessage()).as("The error message is incorrect")
                                                    .isEqualTo("API token is invalid");
    }
}
