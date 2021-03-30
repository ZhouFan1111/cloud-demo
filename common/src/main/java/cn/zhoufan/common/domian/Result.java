package cn.zhoufan.common.domian;

import cn.hutool.core.util.StrUtil;
import cn.zhoufan.common.enums.ResultCode;

/**
 * @Author zhoufan
 * @Date 2021/3/30 14:16
 */
public class Result<T> {
    private boolean success;
    private int code;
    private String msg;
    private T data;
    public static final Result<?> DEFAULT_ERROR;

    public static <T> Result<T> success() {
        return new Result(true, ResultCode.success.code, ResultCode.success.msg, (Object)null);
    }

    public static <T> Result<T> success(T data) {
        return new Result(true, ResultCode.success.code, ResultCode.success.msg, data);
    }


    public static <T> Result<T> error(ResultCode resultCode) {
        return new Result(false, resultCode.code, resultCode.msg, (Object)null);
    }

    public static <T> Result<T> error(int code, String msg) {
        return new Result(false, code, msg, (Object)null);
    }

    public static <T> Result<T> parameterError(String message) {
        return new Result(false, ResultCode.parameter_error.code, message, (Object)null);
    }

    public static <T> Result<T> exceptionError(String message) {
        return new Result(false, ResultCode.exception.code, message, (Object)null);
    }

    public boolean isSuccess() {
        return ResultCode.success.code == this.code;
    }

    public String getMsg() {
        return StrUtil.isNotBlank(this.msg) ? this.msg : ResultCode.getMsg(this.code);
    }

    public static <T> Result.ResultBuilder<T> builder() {
        return new Result.ResultBuilder();
    }

    public int getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public Result<T> setSuccess(final boolean success) {
        this.success = success;
        return this;
    }

    public Result<T> setCode(final int code) {
        this.code = code;
        return this;
    }

    public Result<T> setMsg(final String msg) {
        this.msg = msg;
        return this;
    }

    public Result<T> setData(final T data) {
        this.data = data;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Result)) {
            return false;
        } else {
            Result<?> other = (Result)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isSuccess() != other.isSuccess()) {
                return false;
            } else if (this.getCode() != other.getCode()) {
                return false;
            } else {
                label40: {
                    Object this$msg = this.getMsg();
                    Object other$msg = other.getMsg();
                    if (this$msg == null) {
                        if (other$msg == null) {
                            break label40;
                        }
                    } else if (this$msg.equals(other$msg)) {
                        break label40;
                    }

                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Result;
    }


    public String toString() {
        return "Result(success=" + this.isSuccess() + ", code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }

    public Result(final boolean success, final int code, final String msg, final T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }

    static {
        DEFAULT_ERROR = new Result(false, ResultCode.exception.code, ResultCode.exception.msg, (Object)null);
    }

    public static class ResultBuilder<T> {
        private boolean success;
        private int code;
        private String msg;
        private T data;

        ResultBuilder() {
        }

        public Result.ResultBuilder<T> success(final boolean success) {
            this.success = success;
            return this;
        }

        public Result.ResultBuilder<T> code(final int code) {
            this.code = code;
            return this;
        }

        public Result.ResultBuilder<T> msg(final String msg) {
            this.msg = msg;
            return this;
        }

        public Result.ResultBuilder<T> data(final T data) {
            this.data = data;
            return this;
        }

        public Result<T> build() {
            return new Result(this.success, this.code, this.msg, this.data);
        }

        public String toString() {
            return "Result.ResultBuilder(success=" + this.success + ", code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + ")";
        }
    }
}
