package com.bysj.utils;

/**
 * 消息帮助类
 */
public class MessageHelper {
    // 响应业务状态
    private Integer status;
    // 响应消息
    private String msg;
    // 响应中的数据
    private Object data;

    private MessageHelper(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private MessageHelper(Integer status, String msg, Object data) {
        this(status, msg);
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 自定义构建消息
     *
     * @param status
     * @param msg
     * @return
     */
    public static MessageHelper build(Integer status, String msg) {
        return new MessageHelper(status, msg);
    }

    /**
     * 自定义构建消息
     *
     * @param status
     * @param msg
     * @param data
     * @return
     */
    public static MessageHelper build(Integer status, String msg, Object data) {
        return new MessageHelper(status, msg, data);
    }

    /**
     * 操作成功
     *
     * @return
     */
    public static MessageHelper ok() {
        return new MessageHelper(0, "操作成功！");
    }

    /**
     * 操作成功
     *
     * @param data
     * @return
     */
    public static MessageHelper ok(Object data) {
        return new MessageHelper(0, "操作成功！", data);
    }

    /**
     * 操作失败
     *
     * @return
     */
    public static MessageHelper no() {
        return new MessageHelper(1, "操作失败！");
    }
}
