package ru.stqa.pft.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.List;

public class ResetPasswordTest extends TestBase {

    @BeforeSuite
    public void StartMailServer(){
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws IOException, MessagingException, InterruptedException {
        UserData user = app.db().users().iterator().next();
        app.login().log(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.goTo().manageUser();
        app.goTo().clickUser(user.getName());
        app.goTo().resetPassword();
        String email = user.getEmail();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
        String confLink = findConfLink(mailMessages, email);
        app.login().finish(confLink, "1");
        Assert.assertTrue(app.newSession().login(user.getName(), "1"));
    }

    private String findConfLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        System.out.println(mailMessage.text);
        return regex.getText(mailMessage.text);
    }

    @AfterSuite
    public void StopMailServer(){
        app.mail().stop();
    }
}
