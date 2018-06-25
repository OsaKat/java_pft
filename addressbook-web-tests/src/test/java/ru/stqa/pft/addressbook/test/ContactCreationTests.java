package ru.stqa.pft.addressbook.test;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation1(ContactData contact) {
        Contacts before = app.db().contacts();
        app.goTo().gotoHomePage();
        app.contact().create(contact, true);
        app.goTo().gotoHomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c)-> c.getId()).max().getAsInt()))));
        verifyContactListInUI();
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
                .withEmail("test@test.ru"));
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
