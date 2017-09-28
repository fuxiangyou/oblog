package cn.owenfu.common;

/**
 * Created with IntelliJ IDEA.
 * User: Owen
 * Date: 2017/9/17
 * Time: 16:46
 * Just Do It
 * Result description:
 * 返回结果包装类
 */
public class Result {

    private String code; //返回消息代码

    private String message; //返回码信息

    private Object data; //数据


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}

