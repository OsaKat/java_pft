package ru.stqa.pft.addressbook.appmanager;

import com.sun.org.apache.bcel.internal.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("middlename"), contactData.getPatronymic());
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("nickname"), contactData.getNick());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("notes"), contactData.getNotes());

        if (creation) {
            new org.openqa.selenium.support.ui.Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }


    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void updateContact() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void editContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void deleteContactfromEditPage() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void deleteContactfromHomePage() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void selectContact() {
        click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }

}
