package BusinessLogic;

import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Model.Orders;
import Model.Product;

/**
 * @author Samuel-Adrian
 * The `OrderBLL` class provides business logic operations for the `Orders` model.
 */
public class OrderBLL {

    /**
     * Inserts an order into the database.
     *
     * @param order the order to insert
     * @throws Exception if an error occurs during the insertion process or if the client or product does not exist
     */
    public void insertOrder(Orders order) throws Exception {
        Product p;
        ProductDAO productDao = new ProductDAO();
        ClientDAO clientDao = new ClientDAO();
        OrderDAO orderDao = new OrderDAO();
        int oQ = Integer.parseInt(order.getQuantity());
        if (oQ == 0)
            throw new Exception("quantity=0!");
        if (clientDao.findById(order.getIdClient()) == null) {
            throw new Exception("Client does not exist!");
        }
        if ((p = productDao.findById(order.getProductId())) == null) {
            throw new Exception("Product does not exist!");
        }
        int pQ = Integer.parseInt(p.getQuantity());
        if (pQ >= oQ) {
            productDao.update(p, "quantity", "" + (pQ - oQ));
        } else {
            throw new Exception("Not enough stock");
        }
        orderDao.insertOrder(order);
    }

    /**
     * Deletes an order from the database.
     *
     * @param order the order to delete
     * @throws Exception if an error occurs during the deletion process or if the order does not exist
     */
    public void deleteOrder(Orders order) throws Exception {
        Product p;
        ProductDAO productDao = new ProductDAO();
        ClientDAO clientDao = new ClientDAO();
        OrderDAO orderDao = new OrderDAO();
        if (orderDao.findById(order.getId()) == null) {
            throw new Exception("Nu exista comanda");
        }
        int oQ = Integer.parseInt(order.getQuantity());
        p = productDao.findById(order.getProductId());
        int pQ = Integer.parseInt(p.getQuantity());
        productDao.update(p, "quantity", "" + (pQ + oQ));
        orderDao.delete(order);
    }
}
