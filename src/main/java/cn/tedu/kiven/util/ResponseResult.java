package cn.tedu.kiven.util;


import java.io.Serializable;

/**
 * 服务器返回给客户端的json所用类型
 * @param <T>
 */
public class ResponseResult<T> implements Serializable {
        private Integer state;
        private String message;
        private T data;


    public ResponseResult() {
    }

    public ResponseResult(Integer state) {
        this.state = state;
    }



    public ResponseResult(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ResponseResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public ResponseResult(Integer state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
