package cn.tedu.kiven.controller.ex;


/**
 * 文件大小不匹配异常
 * @author tedu-uid
 *
 */
public class FileSizeException extends FileUploadException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5224868791112845743L;

	public FileSizeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileSizeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileSizeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileSizeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}	
