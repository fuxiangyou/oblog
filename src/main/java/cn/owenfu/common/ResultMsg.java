package cn.owenfu.common;

/**
 * Created with IntelliJ IDEA.
 * User: Owen
 * Date: 2017/9/17
 * Time: 16:46
 * Just Do It
 * ResultMsg description:
 * 返回结果枚举
 */
public enum ResultMsg {

    SUCCESSAA("200", "添加/修改文章成功！"),
    SUCCESSAD("200", "删除文章成功！"),
    EXCEPTIONAA("202", "添加/修改文章异常，请联系管理员"),
    EXCEPTIONAD("202", "删除文章异常，请联系管理员"),

    SUCCESSCA("200", "添加/修改分类成功！"),
    SUCCESSCD("200", "删除分类成功！"),
    EXCEPTIONCA("202", "添加/修改分类异常，请联系管理员"),
    EXCEPTIONCD("202", "删除分类异常，请联系管理员"),

    SUCCESSLA("200", "添加/修改标签成功！"),
    SUCCESSLD("200", "删除标签成功！"),
    EXCEPTIONLA("202", "添加/修改标签异常，请联系管理员"),
    EXCEPTIONLD("202", "删除标签异常，请联系管理员"),

    SUCCESS("200", "获取成功"),
    NULL("201", "查询不到资源"),
    NULLA("201", "查询不到对应资源"),
    EXCEPTION("202", "出现异常请联系管理员");


    private final String code;

    private final String msg;


    private ResultMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ResultMsg getResultMsg(String name) {
        return ResultMsg.valueOf(name);
    }

    public static void main(String[] args) {
        System.out.println(ResultMsg.SUCCESS.getCode());
        System.out.println("------------------------------------------");

        System.out.println(ResultMsg.SUCCESS.getMsg());
        System.out.println("------------------------------------------");
        ResultMsg resultMsg = ResultMsg.valueOf("NULL");
        System.out.println(resultMsg.getCode() + "---" + resultMsg.getMsg());
    }

}
