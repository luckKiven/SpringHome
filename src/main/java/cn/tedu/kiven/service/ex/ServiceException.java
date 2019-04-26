package cn.tedu.kiven.service.ex;

/**
 * 所有业务异常的基类,继承RuntimeException
 */
public class ServiceException extends RuntimeException {


    private static final long serialVersionUID = 8301590283362894953L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
