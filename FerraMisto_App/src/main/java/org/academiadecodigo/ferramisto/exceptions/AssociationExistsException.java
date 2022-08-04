package org.academiadecodigo.ferramisto.exceptions;

/**
 * Thrown to indicate that an association still exists
 */
public class AssociationExistsException extends Exception {

    /**
     * @see JavaBankException#JavaBankException(String)
     */
    public AssociationExistsException() {
        super("Entity contains association with another entity");
    }
}
