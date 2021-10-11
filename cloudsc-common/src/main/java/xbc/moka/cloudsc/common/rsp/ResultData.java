package xbc.moka.cloudsc.common.rsp;

import xbc.moka.cloudsc.common.enums.CloudScEnum;

import java.time.LocalDateTime;

public class ResultData<T> {
    private Integer status;
    private String message;
    private T data;
    private LocalDateTime time;

    public ResultData() {
        this.time = LocalDateTime.now();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(CloudScEnum.RC200.getStatus());
        resultData.setMessage(CloudScEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(CloudScEnum cloudScEnum) {
        return fail(cloudScEnum.getStatus(), cloudScEnum.getMessage());
    }

    public static <T> ResultData<T> fail(Integer status, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(status);
        resultData.setMessage(message);
        return resultData;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public LocalDateTime getTime() {
        return time;
    }

}
