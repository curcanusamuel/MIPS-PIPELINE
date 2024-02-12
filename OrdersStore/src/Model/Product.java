package Model;

/**
 * @author Samuel-Adrian
 * The `Product` class represents a product with its details.
 */
public class Product {
    private long id;
    private String name;
    private String price;
    private String quantity;

    /**
     * Constructs a new instance of the `Product` class with the specified values.
     *
     * @param id       the product ID
     * @param name     the product name
     * @param price    the product price
     * @param quantity the product quantity
     */
    public Product(long id, String name, String price, String quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Constructs a new instance of the `Product` class with default values.
     */
    public Product() {
    }

    /**
     * Retrieves the product ID.
     *
     * @return the product ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the product ID.
     *
     * @param id the product ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Retrieves the product name.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     *
     * @param name the product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the product price.
     *
     * @return the product price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     *
     * @param price the product price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Retrieves the product quantity.
     *
     * @return the product quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Sets the product quantity.
     *
     * @param quantity the product quantity
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
