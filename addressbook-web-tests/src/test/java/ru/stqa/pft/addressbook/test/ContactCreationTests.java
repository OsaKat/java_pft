package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().gotoHomePage();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData()
                .withName("Иван").withPatronymic("Петрович").withSurname("Тестовый").withNick("Тестик").withCompany("ООО \"Рога и копыта\"").withAddress("190000 Москва, Арбат, 5").withHomephone("84951345689").withMobilephone("891601204875").withEmail("test@test.ru").withGroup("test1");
        app.contact().create(contact);
        app.goTo().gotoHomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c)-> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);

    }


}
