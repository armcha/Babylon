package com.luseen.babylon;

/**
 * Created by Chatikyan on 18.07.2017.
 */

public class BabylonException extends RuntimeException {



    public BabylonException(String message) {
        super(message);
    }

    public BabylonException(String message, Throwable cause) {
        super(message, cause);
    }
}
