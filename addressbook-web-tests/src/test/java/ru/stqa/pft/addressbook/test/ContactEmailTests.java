package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Иван").withSurname("Тестовый")
                    .withEmail("test@test.ru").withEmail2("test2@test.ru").withEmail3("test3@test.ru"));
            app.goTo().gotoHomePage();
        }
    }
    @Test
    public void testContactEmails(){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all2().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(contactInfoFromEditForm.getAllEmails()));
    }

}
