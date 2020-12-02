public class Contact implements Comparable<Contact> {
    public String firstName;
    public String lastName;

    public Contact(String f, String l) {
        firstName = f; lastName = l;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

    public int compareTo(Contact c) {
        // TODO Q1
        // Check if the contacts have the same last names
        if(lastName.equals(c.lastName))
        {
            // Compare the first names
            return (firstName.toLowerCase()).compareTo(c.firstName.toLowerCase());

        }
        else
        {
            // Compare the last names
            return (lastName.toLowerCase()).compareTo(c.lastName.toLowerCase());
        }
    }
}
