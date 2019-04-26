package cn.tedu.kiven.service.ex;


/**
 * 用户名重复异常
 */
public class UserConflictException extends ServiceException {


    private static final long serialVersionUID = -2512497142099836738L;

    public UserConflictException() {
        super();
    }

    public UserConflictException(String message) {
        super(message);
    }

    public UserConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserConflictException(Throwable cause) {
        super(cause);
    }

    protected UserConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
