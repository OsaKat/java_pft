package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void manageUser() throws InterruptedException {
        click(By.linkText("управление"));
        click(By.linkText("Управление пользователями"));
    }

    public void clickUser(String username) throws InterruptedException {
        click(By.linkText(username));
    }

    public void resetPassword() {
        click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
    }
}
