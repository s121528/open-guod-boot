package cn.hacz.edu.exception;


import cn.hacz.edu.base.vo.ApiResultEnum;

/**
 * project - GitHub整理 Gitee开源系统
 *
 * @author guod
 * @version 3.0
 */
public class SelfException extends RuntimeException {
    private Integer code;

    public SelfException(ApiResultEnum apiResultEnum) {
        super(apiResultEnum.getMessage());
        this.code = apiResultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
