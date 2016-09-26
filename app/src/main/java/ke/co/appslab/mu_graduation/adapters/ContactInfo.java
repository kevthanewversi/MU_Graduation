package ke.co.appslab.mu_graduation.adapters;

import java.util.ArrayList;

/**
 * Created by root on 1/20/16.
 */
public class ContactInfo {
    public String name;
    public String surname;
    public static final  String NAME_PREFIX = "Name_";
    public static final String SURNAME_PREFIX = "Surname_";

    private static int lastContactId = 0;

    public ContactInfo(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public static ArrayList<ContactInfo> createContactsList(int numContacts) {
        ArrayList<ContactInfo> contacts = new ArrayList<ContactInfo>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new ContactInfo("Person " + ++lastContactId,"Kevin"));
        }

        return contacts;
    }
}
