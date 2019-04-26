package cn.tedu.kiven.service.ex;

/**
 * 登录密码错误异常
 */
public class PasswordErrorException extends ServiceException {

    private static final long serialVersionUID = -7356717166724585806L;

    public PasswordErrorException() {
        super();
    }

    public PasswordErrorException(String message) {
        super(message);
    }

    public PasswordErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordErrorException(Throwable cause) {
        super(cause);
    }

    protected PasswordErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
