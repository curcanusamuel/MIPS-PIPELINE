package Model;

/**
 * @author Samuel-Adrian
 * The `Orders` class represents an order with its details.
 */
public class Orders {
    private long Id;
    private long idClient;
    private long productId;
    private String quantity;

    /**
     * Constructs a new instance of the `Orders` class with the specified values.
     *
     * @param Id         the order ID
     * @param idClient   the client ID associated with the order
     * @param productId  the product ID associated with the order
     * @param quantity   the quantity of the ordered product
     */
    public Orders(long Id, long idClient, long productId, String quantity) {
        this.idClient = idClient;
        this.productId = productId;
        this.quantity = quantity;
        this.Id = Id;
    }

    /**
     * Constructs a new instance of the `Orders` class with default values.
     */
    public Orders() {
    }

    /**
     * Returns a string representation of the `Orders` object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Orders [id=" + Id + ", idClient=" + idClient + ", productId=" + productId + ", quantity=" + quantity
                + "]";
    }

    /**
     * Retrieves the order ID.
     *
     * @return the order ID
     */
    public long getId() {
        return Id;
    }

    /**
     * Sets the order ID.
     *
     * @param id the order ID
     */
    public void setId(long id) {
        this.Id = id;
    }

    /**
     * Retrieves the client ID associated with the order.
     *
     * @return the client ID
     */
    public long getIdClient() {
        return idClient;
    }

    /**
     * Sets the client ID associated with the order.
     *
     * @param idClient the client ID
     */
    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    /**
     * Retrieves the product ID associated with the order.
     *
     * @return the product ID
     */
    public long getProductId() {
        return productId;
    }

    /**
     * Sets the product ID associated with the order.
     *
     * @param productId the product ID
     */
    public void setProductId(long productId) {
        this.productId = productId;
    }

    /**
     * Retrieves the quantity of the ordered product.
     *
     * @return the quantity of the ordered product
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the ordered product.
     *
     * @param quantity the quantity of the ordered product
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
