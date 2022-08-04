package org.academiadecodigo.ferramisto.exceptions;

import javassist.NotFoundException;

/**
 * Thrown to indicate that the account was not found
 */
public class AccountNotFoundException extends NotFoundException {

    /**
     * @see NotFoundException#NotFoundException(String)
     */
    public AccountNotFoundException() {
        super("Account not found");
    }
}
