package cn.tedu.kiven.controller.ex;


/**
 * 文件为空异常,上传没有提交文件数据,或选择文件为0字节的情况
 * @author tedu-uid
 *
 */
public class FileEmptyException extends FileUploadException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3061848173638258126L;

	public FileEmptyException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileEmptyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileEmptyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileEmptyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
