package Validators;

import java.util.regex.Pattern;

import Model.Client;

/**
 * @author Samuel-Adrian
 * The `PhoneNumber` class implements the `Validator` interface to validate phone numbers for client objects.
 */
public class PhoneNumber implements Validator<Client> {

    private static final String NUMBER_PATTERN = "^07\\d{8}$";

    /**
     * Validates the phone number of a client.
     *
     * @param client the client object to validate
     * @throws IllegalArgumentException if the phone number is not valid
     */
    @Override
    public void validate(Client client) {
        Pattern pattern = Pattern.compile(NUMBER_PATTERN);
        if (!pattern.matcher(client.getNumber()).matches()) {
            throw new IllegalArgumentException("Number is not a valid number!");
        }
    }
}
