package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Василий", "Иванович", "Тестовый", "Тестик", "ООО \"Рога и копыта\"", "190000 Москва, Арбат, 5", "84951345689", "891601204875", "test@test.ru", null, "notesnotesnotes"));
        }
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(0);
        app.getContactHelper().fillContactForm(new ContactData("Василий", "Иванович", "Тестовый", "Тестик", "ООО \"Рога и копыта\"", "190000 Москва, Арбат, 5", "84951345689", "891601204875", "test@test.ru", null, "notesnotesnotes"), false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }


}
