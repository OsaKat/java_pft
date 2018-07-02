package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends BaseHelper {
    public LoginHelper(ApplicationManager app) {
        super(app);
    }

    public void log(String username, String password) throws InterruptedException {
        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"), username);
        click(By.cssSelector("input.width-40"));
        type(By.name("password"), password);
        click(By.cssSelector("input.width-40"));
    }

    public void finish(String confLink, String pass1) throws InterruptedException {
        wd.get(confLink);
        type(By.name("password"), "1");
        type(By.name("password_confirm"), "1");
        click(By.cssSelector("button.width-100"));
    }

    public void logout() {
    }
}
