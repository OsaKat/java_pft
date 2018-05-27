package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Василий", "Иванович", "Тестовый", "Тестик", "ООО \"Рога и копыта\"", "190000 Москва, Арбат, 5", "84951345689", "891601204875", "test@test.ru", null, "notesnotesnotes"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().editContact(0);
        app.getContactHelper().fillContactForm(new ContactData("Василий", "Иванович", "Тестовый", "Тестик", "ООО \"Рога и копыта\"", "190000 Москва, Арбат, 5", "84951345689", "891601204875", "test@test.ru", null, "notesnotesnotes"), false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }


}
