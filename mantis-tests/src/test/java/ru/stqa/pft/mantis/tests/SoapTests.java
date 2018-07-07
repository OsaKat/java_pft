package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testGetIssues() throws RemoteException, ServiceException, MalformedURLException {
        Set<Issue> issues = app.soap().getIssues();
        System.out.println(issues.size());
        for (Issue issue : issues) {
            System.out.println(issue.getId());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue()
                .withSummary("Test issue")
                .withDescription("Test issue description")
                .withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }

    @Test
    public void testNoFixed() throws MalformedURLException, RemoteException, ServiceException {
        int idTask = 2;
        skipIfNotFixed(idTask);
        app.soap().getMantisConnect();
        System.out.println(isIssueOpen(idTask));
    }

    @Test
    public void testFixed() throws MalformedURLException, RemoteException, ServiceException {
        int idTask = 4;
        skipIfNotFixed(idTask);
        app.soap().getMantisConnect();
        System.out.println(isIssueOpen(idTask));
    }

    @Test
    public void testRandom() throws MalformedURLException, RemoteException, ServiceException {
        app.soap().getMantisConnect();
        Issue issue = app.soap().getIssues().iterator().next();
        System.out.println(issue.getStatus());
        skipIfNotFixed(issue.getId());
        app.soap().getMantisConnect();
        System.out.println(isIssueOpen(issue.getId()));
    }
}
