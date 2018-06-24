package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsGroup() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1"));
        }
    }

    @BeforeMethod
    public void ensurePreconditionsContact() {
        if (app.db().contacts().size() == 0) {
            app.goTo().gotoHomePage();
            app.contact().create(new ContactData()
                    .withName("Иван").withSurname("Тестовый")
                    .withAddress("190000 Москва, Арбат, 5").withMobilephone("891601204875")
                    .withEmail("test@test.ru"));
            app.goTo().gotoHomePage();
        }
    }

    @Test (enabled = true)
    public void testContactAddGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData editedContact = app.contact().searchContactForGroup(contacts, groups);
        GroupData group = app.contact().groupForContact(editedContact, groups);
        ContactData contactBefore = app.db().contact(editedContact.getId());
        app.goTo().gotoHomePage();
        app.contact().contactAddGroup(editedContact, group);
        ContactData contactAfter = app.db().contact(editedContact.getId());
        assertThat(contactBefore.getGroups(), equalTo(contactAfter.getGroups().without(group)));
    }
}
