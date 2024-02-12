package BusinessLogic;

import DataAccess.ProductDAO;
import Model.Product;

/**
 * @author Samuel-Adrian
 * The `ProductBLL` class provides business logic operations for the `Product` model.
 */
public class ProductBLL {
    ProductDAO productDao = new ProductDAO();

    /**
     * Inserts a product into the database.
     *
     * @param product the product to insert
     */
    public void insertProduct(Product product) {
        productDao.insert(product);
    }

    /**
     * Removes a product from the database.
     *
     * @param prod the product to remove
     * @throws Exception if the product does not exist
     */
    public void removeProduct(Product prod) throws Exception {
        if (productDao.findById(prod.getId()) == null) {
            throw new Exception("Nu exista acest produs");
        } else {
            productDao.delete(prod);
        }
    }

    /**
     * Updates a product in the database.
     *
     * @param product the product to update
     * @param col     the column name to update
     * @param val     the new value for the column
     * @throws Exception if an error occurs during the update process
     */
    public void updateProduct(Product product, String col, String val) throws Exception {
        productDao.update(product, col, val);
    }
}
