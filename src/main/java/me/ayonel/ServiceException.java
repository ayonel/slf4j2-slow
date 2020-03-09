package me.ayonel;

/**
 * Custom Service Exception
 * @author yangyun
 * @create 2020-03-07 22:31
 **/
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -8634700792767837033L;


	public ServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}


	public ServiceException(String message) {
		super(message);
	}
}
