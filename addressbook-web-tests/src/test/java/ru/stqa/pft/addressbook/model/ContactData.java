package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String name;
    private final String patronymic;
    private final String surname;
    private final String nick;
    private final String company;
    private final String address;
    private final String homephone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    private final String mobilephone;
    private final String email;
    private String group;
    private final String notes;

    public ContactData(String name, String patronymic, String surname, String nick, String company, String address, String homephone, String mobilephone, String email, String group, String notes) {
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.nick = nick;
        this.company = company;
        this.address = address;
        this.homephone = homephone;
        this.mobilephone = mobilephone;
        this.email = email;
        this.group = group;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public String getNick() {
        return nick;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public String getNotes() {
        return notes;
    }

    public String getGroup() {
        return group;
    }
}
