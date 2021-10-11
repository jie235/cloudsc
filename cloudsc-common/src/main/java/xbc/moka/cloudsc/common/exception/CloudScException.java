package xbc.moka.cloudsc.common.exception;

import xbc.moka.cloudsc.common.enums.CloudScEnum;

public class CloudScException extends Exception {

    private Integer status;
    private String message;

    public CloudScException(CloudScEnum cloudScEnum) {
        this.status = cloudScEnum.getStatus();
        this.message = cloudScEnum.getMessage();
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
