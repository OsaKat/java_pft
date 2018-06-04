package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Иван", "Петрович", "Тестовый", "Тестик", "ООО \"Рога и копыта\"", "190000 Москва, Арбат, 5", "84951345689", "891601204875", "test@test.ru", "test1"));
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size() - 1);
        app.getContactHelper().fillContactForm(new ContactData("Петр", "Иванович", "Тестовый", "Тестик", "ООО \"Рога и копыта\"", "190000 Москва, Арбат, 5", "84951345689", "891601204875", "test@test.ru", null), false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }

}
