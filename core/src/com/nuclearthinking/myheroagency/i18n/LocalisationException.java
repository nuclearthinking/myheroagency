package com.nuclearthinking.myheroagency.i18n;

/**
 * Date: 08.05.2016
 * Time: 0:33
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class LocalisationException extends Exception {
    public LocalisationException() {
        super();
    }

    public LocalisationException(String message) {
        super(message);
    }

    public LocalisationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocalisationException(Throwable cause) {
        super(cause);
    }
}
