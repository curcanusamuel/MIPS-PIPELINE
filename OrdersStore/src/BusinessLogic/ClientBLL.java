package BusinessLogic;

import java.util.ArrayList;
import java.util.List;

import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import Model.Client;
import Validators.PhoneNumber;
import Validators.Validator;

/**
 * @author Samuel-Adrian
 * The `ClientBLL` class provides business logic operations for the `Client` model.
 */
public class ClientBLL {
    private List<Validator<Client>> validators;

    /**
     * Constructs a new `ClientBLL` object.
     * Initializes the list of validators with a `PhoneNumber` validator.
     */
    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new PhoneNumber());
    }

    /**
     * Inserts a client into the database.
     *
     * @param client the client to insert
     */
    public void insertClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.insert(client);
    }

    /**
     * Removes a client from the database.
     *
     * @param client the client to remove
     * @throws Exception if the client does not exist
     */
    public void removeClient(Client client) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        OrderDAO ord = new OrderDAO();
        if (clientDAO.findById(client.getId()) == null) {
            throw new Exception("Client does not exist!");
        }
        clientDAO.delete(client);
    }

    /**
     * Updates a client in the database.
     *
     * @param client the client to update
     * @param col    the column name to update
     * @param val    the new value for the column
     * @throws Exception if an error occurs during the update process
     */
    public void updateClient(Client client, String col, String val) throws Exception {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.update(client, col, val);
    }
}
