package cn.hacz.edu.vo;

import lombok.Data;

/**
 * @author guodd
 * @since 1.0
 */
@Data
public class UserInfoRes {

    /**
     * type : test
     * username : username_1760
     *
     * @timestamp : 2019-09-19T09:28:44.853Z
     * status : 0
     * @version : 1
     * id : 1760
     * email : 1760@qq.com
     * password : e22dd5dabde45eda5a1a67772c8e25dd
     */

    private String type;
    private String username;
    private int status;
    private int id;
    private String email;
    private String password;

}
