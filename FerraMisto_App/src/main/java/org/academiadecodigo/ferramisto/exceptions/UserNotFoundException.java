package org.academiadecodigo.ferramisto.exceptions;

import javassist.NotFoundException;

/**
 * Thrown to indicate that the customer was not found
 */
public class UserNotFoundException extends NotFoundException {

    /**
     * @see NotFoundException#NotFoundException(String)
     */
    public UserNotFoundException() {
        super("User not found");
    }
}
