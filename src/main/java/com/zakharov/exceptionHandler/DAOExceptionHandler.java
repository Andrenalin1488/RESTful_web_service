package com.zakharov.exceptionHandler;

import org.hibernate.HibernateException;

public class DAOExceptionHandler extends HibernateException {

    private String message;


    public DAOExceptionHandler(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
