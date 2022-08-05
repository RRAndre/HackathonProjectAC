package org.academiadecodigo.ferramisto.exceptions;

import org.academiadecodigo.ferramisto.errors.ErrorMessage;

public class TransactionInvalidException extends Exception{
    public TransactionInvalidException() {
        super(ErrorMessage.TRANSACTION_INVALID);
    }

}
