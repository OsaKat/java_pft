package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion () {
        app.getContactHelper().editContact();
        app.getContactHelper().deleteContactfromEditPage();
    }

    @Test
    public void testContactDeletion2 () {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContactfromHomePage();
    }
}
