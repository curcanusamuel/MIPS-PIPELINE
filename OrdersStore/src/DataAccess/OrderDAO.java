package DataAccess;

import javax.swing.JTable;

import Model.Orders;

/**
 * @author Samuel-Adrian
 * The `OrderDAO` class provides data access operations for the `Orders` model.
 */
public class OrderDAO extends AbstractDAO<Orders> {

    /**
     * Constructs a new instance of the `OrderDAO` class.
     */
    public OrderDAO() {
        super();
    }

    /**
     * Inserts an order into the database.
     *
     * @param order the order to insert
     */
    public void insertOrder(Orders order) {
        super.insert(order);
    }

    /**
     * Retrieves the order table as a JTable.
     *
     * @return the JTable representing the order table
     */
    public JTable getOrderTable() {
        return this.getTable();
    }

    /**
     * Removes an order from the database.
     *
     * @param order the order to remove
     * @throws Exception if an error occurs during the removal process
     */
    public void removeOrder(Orders order) throws Exception {
        super.delete(order);
    }

    /**
     * Finds an order by its ID.
     *
     * @param id the ID of the order
     * @return the found order, or null if not found
     */
    public Orders findById(int id) {
        return super.findById(id);
    }
}
