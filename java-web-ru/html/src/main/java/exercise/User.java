package exercise;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public User() {
    }

    public User(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public User setIdSupplier(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstnameSupplier(String firstname) {
        this.firstName = firstname;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastnameSupplier(String lastname) {
        this.lastName = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmailSupplier(String email) {
        this.email = email;
        return this;
    }
}
