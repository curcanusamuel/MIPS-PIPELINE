package Model;

/**
 * @author Samuel-Adrian
 * The `Client` class represents a client with their personal information.
 */
public class Client {
    private long id;
    private String name;
    private String address;
    private String number;

    /**
     * Constructs a new instance of the `Client` class with default values.
     */
    public Client() {
    }

    /**
     * Constructs a new instance of the `Client` class with the specified values.
     *
     * @param id      the client's ID
     * @param name    the client's name
     * @param address the client's address
     * @param number  the client's phone number
     */
    public Client(long id, String name, String address, String number) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.number = number;
    }

    /**
     * Constructs a new instance of the `Client` class with the specified values.
     * The ID will be automatically generated.
     *
     * @param name    the client's name
     * @param address the client's address
     * @param email   the client's email address
     * @param number  the client's phone number
     */
    public Client(String name, String address, String email, String number) {
        this.name = name;
        this.address = address;
        this.number = number;
    }

    /**
     * Sets the ID of the client.
     *
     * @param id the client's ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the name of the client.
     *
     * @param name the client's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the address of the client.
     *
     * @param address the client's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the phone number of the client.
     *
     * @param number the client's phone number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Retrieves the phone number of the client.
     *
     * @return the client's phone number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Retrieves the ID of the client.
     *
     * @return the client's ID
     */
    public long getId() {
        return id;
    }

    /**
     * Retrieves the name of the client.
     *
     * @return the client's name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the address of the client.
     *
     * @return the client's address
     */
    public String getAddress() {
        return address;
    }
}
