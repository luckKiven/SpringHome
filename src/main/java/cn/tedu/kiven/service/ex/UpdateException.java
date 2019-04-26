package cn.tedu.kiven.service.ex;

/**
 * 修改失败异常
 */
public class UpdateException extends ServiceException {

    private static final long serialVersionUID = -3923124031464998081L;

    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
