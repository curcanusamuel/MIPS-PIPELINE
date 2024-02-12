package DataAccess;

import javax.swing.JTable;

import Model.Client;

/**
 * @author Samuel-Adrian
 * The `ClientDAO` class provides data access operations for the `Client` model.
 */
public class ClientDAO extends AbstractDAO<Client> {

    /**
     * Constructs a new instance of the `ClientDAO` class.
     */
    public ClientDAO() {
        super();
    }

    /**
     * Retrieves the client table as a JTable.
     *
     * @return the JTable representing the client table
     */
    public JTable getClientTable() {
        return this.getTable();
    }

    /**
     * Finds a client by its ID.
     *
     * @param id the ID of the client
     * @return the found client, or null if not found
     */
    public Client findById(int id) {
        return super.findById(id);
    }

    /**
     * Inserts a client into the database.
     *
     * @param client the client to insert
     */
    public void insert(Client client) {
        super.insert(client);
    }

    /**
     * Removes a client from the database.
     *
     * @param client the client to remove
     * @throws Exception if an error occurs during the removal process
     */
    public void remove(Client client) throws Exception {
        super.delete(client);
    }
}
