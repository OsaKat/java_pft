package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().gotoHomePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData()
                .withName("Иван").withPatronymic("Петрович").withSurname("Тестовый").withNick("Тестик").withCompany("ООО \"Рога и копыта\"").withAddress("190000 Москва, Арбат, 5").withHomephone("84951345689").withMobilephone("891601204875").withEmail("test@test.ru").withGroup("test1");
        app.contact().create(contact);
        app.goTo().gotoHomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }


}
