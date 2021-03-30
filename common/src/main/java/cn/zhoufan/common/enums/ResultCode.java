package cn.zhoufan.common.enums;

public enum ResultCode {
    success(0, "成功"),
    parameter_error(1, "参数异常"),
    candidate_business_null(5, "会员导师候选列表为空！"),
    robot_goods_too_many(100, "机器人选品太多了"),
    translate_url_fail(101, "转链失败"),
    group_too_many(102, "当前最多只能创建10个群"),
    exception(-1, "系统遇到一点小问题，请稍后重试"),
    no_session(-2, "登录失效，请重新登录"),
    no_permission(-3, "无权限进行此操作"),
    sign_error(-4, "签名异常"),
    goods_over(300, "商品活动已结束"),
    sms_error(200, "短信获取太频繁了"),
    user_not_find(400, "用户不存在"),
    msg_sensitive(-5, "含有敏感词汇"),
    account_exception(-100, "账号异常"),
    goods_not_pop(-6, "不存在的商品信息"),
    server_call_error(500, "服务调用异常"),
    flow_exception(600, "触发了流控"),
    param_flow_exception(601, "触发了参数流控"),
    authority_exception(602, "触发了授权规则"),
    system_block_exception(603, "触发了系统规则"),
    degrade_exception(604, "触发了降级规则");

    public int code;
    public String msg;

    private ResultCode(int code) {
        this.code = code;
    }

    private ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(int code) {
        switch (code) {
            case -1:
                return exception.msg;
            case 0:
                return "成功";
            default:
                return "";
        }
    }

    public String toString() {
        return "ResultCode [code=" + this.code + ", msg=" + this.msg + "]";
    }
}
