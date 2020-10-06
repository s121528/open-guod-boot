package cn.hacz.edu.exception;


import cn.hacz.edu.vo.ResultEnum;

/**
 * project - GitHub整理 Gitee开源系统
 *
 * @author guod
 * @version 3.0
 */
public class SelfException extends RuntimeException {
    private String code;

    public SelfException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
