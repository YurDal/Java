package collections;
/**
 * This class represents a exception for a queue
 * @author Yurdaer Dalkic
 *
 */
public class QueueException extends RuntimeException {
    public QueueException() {}
    public QueueException( String message ) {
        super( message );
    }
}
