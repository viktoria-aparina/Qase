package REST_QASE;

import REST_QASE.clients.ProjectApiClients;
import REST_QASE.dto.Project;
import REST_QASE.dto.ProjectResponse;
import REST_QASE.providers.ProjectProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ProjectNegativePostTest {

    ProjectApiClients projectApiClients = new ProjectApiClients();

    @Test
    public void createProjectOnlyWithTitleTest() {

        Project expectedProject = new ProjectProvider().getProjectOnlyWithTitle();
        ProjectResponse postActualProject = projectApiClients.postProject(expectedProject, HttpStatus.SC_BAD_REQUEST);

        assertThat(postActualProject.isStatus()).as("The project was created without code").isEqualTo(false);

        assertThat(postActualProject.getErrorMessage()).as("Error message is incorrect")
                                                       .isEqualTo("Data is invalid.");
    }

    @Test
    public void createProjectOnlyWithCodeTest() {

        Project expectedProject = new ProjectProvider().getProjectOnlyWithTitle();
        ProjectResponse postActualProject = projectApiClients.postProject(expectedProject, HttpStatus.SC_BAD_REQUEST);

        assertThat(postActualProject.isStatus()).as("The project was created without title").isEqualTo(false);

        assertThat(postActualProject.getErrorMessage()).as("Error message is incorrect")
                                                       .isEqualTo("Data is invalid.");
    }

    @Test
    public void createProjectWithInvalidToken() {

        Project expectedProject = new ProjectProvider().getProject();
        ProjectResponse postActualProject = projectApiClients.postWithInvalidTokenProject(expectedProject, HttpStatus.SC_UNAUTHORIZED);

        assertThat(postActualProject.getErrorMessage()).as("Error message is incorrect")
                                                       .isEqualTo("API token is invalid");
    }
}
