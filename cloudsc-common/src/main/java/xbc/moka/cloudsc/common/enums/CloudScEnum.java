package xbc.moka.cloudsc.common.enums;

public enum CloudScEnum {

    FIELD_REQUIRED(10001, "请求参数无效"),

    RC200(200, "操作成功"),
    RC400(400, "请求路径或参数不正确"),
    RC403(403, "没有权限访问相应资源"),
    RC404(404, "请求的接口或资源不存在"),
    RC405(405, "请求格式不正确"),
    RC408(408, "请求超时"),
    RC500(500, "服务端发生错误"),

    THRESHOLD(900, "达到阈值了，请不要请求了！")

    ;
    private Integer status;
    private String message;

    CloudScEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
