package by.teachmeskills.providers;

import by.teachmeskills.dto.Project;
import by.teachmeskills.dto.ProjectAccess;
import com.github.javafaker.Faker;

public class ProjectProvider {

    Faker faker = new Faker();

    public Project getProject() {
        return Project.builder()
                                 .name(faker.company().name())
                                 .code(faker.code().ean8())
                                 .description(faker.programmingLanguage().name())
                                 .accessLevel(ProjectAccess.PUBLIC)
                                 .build();
    }
}
