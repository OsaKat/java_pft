package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage(String groups) {
        click(By.linkText(groups));
    }

    public void gotoHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }
}
