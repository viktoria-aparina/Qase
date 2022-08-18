package by.teachmeskills.steps;

import by.teachmeskills.pages.AllProjectsPage;
import by.teachmeskills.pages.ProjectPage;

import static com.codeborne.selenide.Selenide.$x;

public class DeleteProjectSteps {

    AllProjectsPage allProjectsPage;
    ProjectPage projectPage;

    public DeleteProjectSteps() {
        this.allProjectsPage = new AllProjectsPage();
        this.projectPage = new ProjectPage();
    }

    public AllProjectsPage deleteProject() {
       projectPage.deleteProject();
       return allProjectsPage;
    }
}
