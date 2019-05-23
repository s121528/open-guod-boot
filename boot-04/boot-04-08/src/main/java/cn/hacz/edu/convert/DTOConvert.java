package cn.hacz.edu.convert;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/5/22 时间:13:47
 * @JDK 1.8
 * @Description 功能模块：
 */
public interface DTOConvert<S, T, Y> {
    S doForward(T t);
    Y doBackward(S s);
}