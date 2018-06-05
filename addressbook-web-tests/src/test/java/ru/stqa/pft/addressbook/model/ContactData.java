package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String name;
    private final String patronymic;
    private final String surname;
    private final String nick;
    private final String company;
    private final String address;
    private final String homephone;
    private final String mobilephone;
    private final String email;
    private final String group;

    public ContactData(int id, String name, String patronymic, String surname, String nick, String company, String address, String homephone, String mobilephone, String email,String group) {
        this.id = id;
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
    }

    public ContactData(String name, String patronymic, String surname, String nick, String company, String address, String homephone, String mobilephone, String email,String group) {
        this.id = Integer.MAX_VALUE;;
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
    }

    public int getId() { return id; }

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

    public String getGroup() {
        return group;
    }

    public void setId(int id) { this.id = id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}
