package REST_QASE.clients;

import REST_QASE.dto.Project;
import REST_QASE.dto.ProjectResponse;
import REST_QASE.dto.allProjects.AllProjects;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;

import java.util.Map;

@Log4j2
public class ProjectApiClients extends BaseApiClient {

    private final static String PROJECT_URI = "v1/project";
    private static final String PROJECT_URI_WITH_CODE = PROJECT_URI + "/{code}";
    private static final String PROJECT_CODE = "code";
    private static final String ALL_PROJECT_URI = PROJECT_URI + "?limit={limit}}&offset={offset}";
    private static final String PROJECT_LIMIT = "limit";
    private static final String PROJECT_OFFSET = "offset";

    public ProjectResponse postProject(Project project, int httpStatusCode) {
        Response response = post(PROJECT_URI, project);
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(ProjectResponse.class);
    }

    public ProjectResponse postWithInvalidTokenProject(Project project, int httpStatusCode) {
        Response response = postWithInvalidToken(PROJECT_URI, project);
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(ProjectResponse.class);
    }

    public ProjectResponse getProject(String code, int httpStatusCode) {
        Response response = get(PROJECT_URI_WITH_CODE, Map.of(PROJECT_CODE, code));
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(ProjectResponse.class);
    }

    public ProjectResponse getWithInvalidTokenProject(String code, int httpStatusCode) {
        Response response = getWithInvalidToken(PROJECT_URI_WITH_CODE, Map.of(PROJECT_CODE, code));
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(ProjectResponse.class);
    }

    public Response getProjectResponse(String code) {
        return get(PROJECT_URI_WITH_CODE, Map.of(PROJECT_CODE, code));
    }

    public ProjectResponse deleteProject(String code, int httpStatusCode) {
        Response response = delete(PROJECT_URI_WITH_CODE, Map.of(PROJECT_CODE, code));
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(ProjectResponse.class);
    }

    public ProjectResponse deleteWithInvalidTokenProject(String code, int httpStatusCode) {
        Response response = deleteWithInvalidToken(PROJECT_URI_WITH_CODE, Map.of(PROJECT_CODE, code));
        return response.then()
                       .statusCode(httpStatusCode)
                       .extract()
                       .body()
                       .as(ProjectResponse.class);
    }

    public AllProjects getAllProjects(int limit, int offset) {
        Response response = getAll(PROJECT_URI, Map.of(PROJECT_LIMIT, limit, PROJECT_OFFSET, offset));
        return response.then()
                       .statusCode(HttpStatus.SC_OK)
                       .extract()
                       .body()
                       .as(AllProjects.class);
    }

    public Response getAllProjectResponse(int limit, int offset) {
        return getAll(PROJECT_URI, Map.of(PROJECT_LIMIT, limit, PROJECT_OFFSET, offset));
    }
}


