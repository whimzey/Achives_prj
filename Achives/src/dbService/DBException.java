package dbService;


public class DBException extends Exception {
    
	private static final long serialVersionUID = 1L;

	public DBException(Throwable throwable) {
        super(throwable);
    }
}
