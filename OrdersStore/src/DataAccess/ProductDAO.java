package DataAccess;

import javax.swing.JTable;

import Model.Product;

/**
 * @author Samuel-Adrian
 * The `ProductDAO` class provides data access operations for the `Product` model.
 */
public class ProductDAO extends AbstractDAO<Product> {

    /**
     * Constructs a new instance of the `ProductDAO` class.
     */
    public ProductDAO() {
        super();
    }

    /**
     * Finds a product by its ID.
     *
     * @param id the ID of the product
     * @return the found product, or null if not found
     */
    public Product findById(int id) {
        return super.findById(id);
    }

    /**
     * Retrieves the product table as a JTable.
     *
     * @return the JTable representing the product table
     */
    public JTable getProductTable() {
        return this.getTable();
    }

    /**
     * Inserts a product into the database.
     *
     * @param product the product to insert
     */
    public void insert(Product product) {
        super.insert(product);
    }

    /**
     * Removes a product from the database.
     *
     * @param product the product to remove
     * @throws Exception if an error occurs during the removal process
     */
    public void remove(Product product) throws Exception {
        super.delete(product);
    }

    /**
     * Updates a product in the database.
     *
     * @param product the product to update
     * @param col     the column name to update
     * @param val     the new value for the column
     * @return the updated product
     */
    public Product update(Product product, String col, String val) {
        return super.update(product, col, val);
    }
}
