package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    public void gotoModificationContactById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }


    public void deleteContactfromHomePage() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("middlename"), contactData.getPatronymic());
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("nickname"), contactData.getNick());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("email"), contactData.getEmail());
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact,true);
        submitContactCreation();
        contactCashe = null;
    }

    public void modify(ContactData contact) {
        gotoModificationContactById(contact.getId());
        fillContactForm(contact);
        submitContactCreation();
        contactCashe = null;
        returnHomePage();

    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContactfromHomePage();
        contactCashe = null;
    }

    public void returnHomePage() {
        click(By.linkText("home"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCashe = null;


    public Contacts all() {
        if (contactCashe != null) {
            return new Contacts(contactCashe);
        }

        contactCashe = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements){
            List<WebElement> list = wd.findElements(By.cssSelector("td"));
            String name = list.get(2).getText();
            String surname = list.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCashe.add(new ContactData().withId(id).withName(name).withSurname(surname));
        }
        return new Contacts(contactCashe);
    }

}
