package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().create(new ContactData()
                    .withName("Иван").withPatronymic("Петрович").withSurname("Тестовый").withNick("Тестик").withCompany("ООО \"Рога и копыта\"").withAddress("190000 Москва, Арбат, 5").withHomephone("84951345689").withMobilephone("891601204875").withEmail("test@test.ru").withGroup("test1"));
            app.goTo().gotoHomePage();
        }
    }

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.getContactHelper().list();
        int index = before.size() - 1;
        app.getContactHelper().selectContact(index);
        app.getContactHelper().deleteContactfromHomePage();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);

    }
}
