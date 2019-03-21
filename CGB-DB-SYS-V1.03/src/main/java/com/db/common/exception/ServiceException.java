package com.db.common.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -3135239055465723987L;

    public ServiceException() {
        super();
    }
    public ServiceException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }
    public ServiceException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
