package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteGroupTests extends TestBase {

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
            Groups groups = app.db().groups();
            app.contact().create(new ContactData()
                    .withName("Иван").withSurname("Тестовый")
                    .withAddress("190000 Москва, Арбат, 5").withMobilephone("891601204875")
                    .withEmail("test@test.ru").inGroup(groups.iterator().next()), true);
            app.goTo().gotoHomePage();
        }
    }

    @BeforeMethod
    public void beforeMethodContactWithGroup() {
        app.goTo().gotoHomePage();
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        int i = 0;
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() == 0)
                i++;
        }
        if (i == contacts.size()) {
            app.goTo().gotoHomePage();
            app.contact().create(new ContactData()
                    .withName("Иван").withSurname("Тестовый")
                    .withAddress("190000 Москва, Арбат, 5").withMobilephone("891601204875")
                    .withEmail("test@test.ru").inGroup(groups.iterator().next()), true);
            app.goTo().gotoHomePage();
        }
    }

    @Test
    public void testContactDelGroup() {
        app.goTo().gotoHomePage();
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData group = app.contact().groupForContactDel(contacts, groups);
        ContactData contact = app.contact().contactForDel(contacts, group);
        ContactData contactBefore = app.db().contact(contact.getId());
        app.contact().deleteFromGroup(contact, group);
        ContactData contactAfter = app.db().contact(contact.getId());
        assertThat(contactBefore.getGroups(), equalTo(contactAfter.getGroups().withAdded(group)));

    }
}
