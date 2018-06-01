package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion () {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Василий", "Иванович", "Тестовый", "Тестик", "ООО \"Рога и копыта\"", "190000 Москва, Арбат, 5", "84951345689", "891601204875", "test@test.ru", null, "notesnotesnotes"));
        }
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().editContact(0);
        app.getContactHelper().deleteContactfromEditPage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }

    @Test
    public void testContactDeletion2 () {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Василий", "Иванович", "Тестовый", "Тестик", "ООО \"Рога и копыта\"", "190000 Москва, Арбат, 5", "84951345689", "891601204875", "test@test.ru", null,"notesnotesnotes"));
        }
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().deleteContactfromHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);

    }
}
