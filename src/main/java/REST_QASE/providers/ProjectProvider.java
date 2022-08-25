package REST_QASE.providers;

import REST_QASE.dto.Project;
import REST_QASE.dto.ProjectAccess;
import REST_QASE.dto.Result;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class ProjectProvider {

    Faker faker = new Faker();

    public Project getProject() {
        return Project.builder()
                      .title(faker.company().name())
                      .code(RandomStringUtils.randomAlphabetic(5).toUpperCase())
                      .description(faker.internet().url())
                      .accessLevel(ProjectAccess.NONE)
                      .build();
    }

    public Project getProjectOnlyWithTitle() {
        return Project.builder()
                      .title(faker.company().name())
                      .build();
    }

    public Project getProjectWithInvalidCode() {
        return Project.builder()
                      .title(faker.company().name())
                      .code(RandomStringUtils.randomAlphabetic(5).toUpperCase())
                      .build();
    }
}
