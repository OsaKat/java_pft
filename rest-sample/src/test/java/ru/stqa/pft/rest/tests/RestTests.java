package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class RestTests extends TestBase {

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = app.rest().getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
        int issueId = app.rest().createIssue(newIssue);
        Set<Issue> newIssues = app.rest().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

    @Test
    public void testFixed() throws IOException {
        int idIssue = 4;
        skipIfNotFixed(idIssue);
        assertFalse(isIssueOpen(idIssue));
    }

    @Test
    public void testNotFixed() throws IOException {
        int idIssue = 26;
        skipIfNotFixed(idIssue);
        assertTrue(isIssueOpen(idIssue));
    }

    @Test
    public void testRandom() throws IOException {
        Set<Issue> issues = app.rest().getIssue();
        Issue issue = issues.iterator().next();
        skipIfNotFixed(issue.getId());
        assertTrue(isIssueOpen(issue.getId()));
    }
}
