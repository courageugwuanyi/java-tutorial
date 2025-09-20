package com.cloudtruss.peopledb.exception;

public class UnableToSaveException extends RuntimeException {
    // RunTimeEx - is not anticipated. We may not have control about it. Don't have to be handled by the calling method
    // CheckedEx - is anticipated. We can do something about it. We can recover from it. extends the Exception class

    public UnableToSaveException(String message) {
        super(message);
    }
}
