package exceptions;

/**
 *
 * @author Gruppe 3 - Gert, Lene & Mikkel
 */
public class NotFoundException extends Exception{

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super("Requested item could not be found");
    }  
}