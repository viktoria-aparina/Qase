package REST_QASE;

import REST_QASE.clients.ProjectApiClients;
import REST_QASE.dto.Project;
import REST_QASE.dto.ProjectResponse;
import REST_QASE.dto.allProjects.AllProjects;
import REST_QASE.providers.ProjectProvider;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class ProjectCrudTest {

    ProjectApiClients projectApiClients = new ProjectApiClients();
    Faker faker = new Faker();
    private final int limit = faker.number().numberBetween(1, 10);
    private final int offset = faker.number().numberBetween(1, 10);

    @Test
    public void createProjectTest() {

        Project expectedProject = new ProjectProvider().getProject();
        ProjectResponse postActualProject = projectApiClients.postProject(expectedProject, HttpStatus.SC_OK);

        assertThat(postActualProject.getResult().getCode()).as("The code in the response doesn't match expected code")
                                                           .isEqualTo(expectedProject.getCode());

        ProjectResponse getActualProject = projectApiClients.getProject(postActualProject.getResult()
                                                                                         .getCode(), HttpStatus.SC_OK);

        assertThat(expectedProject).as("Project are different").usingRecursiveComparison()
                                   .comparingOnlyFields("title", "code")
                                   .isEqualTo(getActualProject);
    }

    @Test
    public void deleteProjectTest() {

        Project expectedProject = new ProjectProvider().getProject();
        ProjectResponse postActualProject = projectApiClients.postProject(expectedProject, HttpStatus.SC_OK);
        ProjectResponse deletedProject = projectApiClients.deleteProject(postActualProject.getResult()
                                                                                          .getCode(), HttpStatus.SC_OK);

        assertThat(deletedProject.isStatus()).as("The project wasn't deleted").isEqualTo(true);

        Response deletedProjectResponse = projectApiClients.getProjectResponse(postActualProject.getResult().getCode());

        assertThat(deletedProjectResponse.statusCode()).as("Status code is invalid in case for request")
                                                       .isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void getAllProjectsTest() {

        AllProjects actualAllProjects = projectApiClients.getAllProjects(limit, offset);
        assertThat(actualAllProjects.isStatus()).as("All project wasn't loaded").isEqualTo(true);

        Response actualAllProjectsResponse = projectApiClients.getAllProjectResponse(limit, offset);
        assertThat(actualAllProjectsResponse.jsonPath().get("result.count").toString()).as("Count of project is incorrect")
                                                                                       .isEqualTo(valueOf(limit));
    }
}
