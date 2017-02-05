package org.huebner.frederic.complaintapp.backend.exceptions;

import javax.persistence.OptimisticLockException;

public class UpdateConflictException extends RuntimeException {

//    private static final long serialVersionUID = -3234488669666455938L;

    public UpdateConflictException() {
        super();
    }

    public UpdateConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateConflictException(String message) {
        super(message);
    }

    public UpdateConflictException(Throwable cause) {
        super(cause);
    }

}
