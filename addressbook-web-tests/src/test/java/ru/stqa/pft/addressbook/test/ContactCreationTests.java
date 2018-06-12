package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withName("Иван").withPatronymic("Петрович").withSurname("Тестовый")
                .withNick("Тестик").withCompany("ООО \"Рога и копыта\"").withAddress("190000 Москва, Арбат, 5")
                .withHomephone("84951345689").withMobilephone("891601204875")
                .withEmail("test@test.ru").withGroup("test1");
        app.contact().create(contact);
        app.goTo().gotoHomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after.size(),  equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c)-> c.getId()).max().getAsInt()))));

    }

    @Test
    public void testContactCreation2() {
        app.goTo().gotoHomePage();
        app.contact().initContactCreation();
        File photo = new File("src/test/resources/39852670.jpg");
        app.contact().fillContactForm(new ContactData()
                .withName("Иван").withPatronymic("Петрович").withSurname("Тестовый")
                .withNick("Тестик").withCompany("ООО \"Рога и копыта\"").withAddress("190000 Москва, Арбат, 5")
                .withHomephone("84951345689").withMobilephone("891601204875")
                .withEmail("test@test.ru").withPhoto(photo).withGroup("test1"), true);
        app.contact().submitContactCreation();
        app.goTo().gotoHomePage();
    }

    @Test (enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/39852670.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }

}
