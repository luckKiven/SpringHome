package cn.tedu.kiven.controller.ex;


/**
 * 文件上传异常的基类
 * @author tedu-uid
 *
 */
public abstract class FileUploadException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5412078968404307821L;

	public FileUploadException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileUploadException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileUploadException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileUploadException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
