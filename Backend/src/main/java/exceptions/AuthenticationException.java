package exceptions;

/**
 *
 * @author Gruppe 3 - Gert, Lene & Mikkel
 */
public class AuthenticationException extends Exception{

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException() {
        super("Could not be Authenticated");
    }  
}
