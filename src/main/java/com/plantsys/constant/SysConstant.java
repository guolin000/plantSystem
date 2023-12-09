package com.plantsys.constant;


/**
 * 常量接口状态码
 */
public interface SysConstant {

    String USER_LOGIN_ERROR_MSG="用户名或密码不正确";
    String USER_LOGIN_CODE_ERROR_MSG = "请选择登陆身份";
    String USER_REGISTER_CODE_SUCCESS_MSG = "注册成功，请登录";
    String USER_REGISTER_CODE_ERROR_MSG = "用户名已存在";
    /**
     *可用状态
     */
    Integer AVAILABLE_TRUE = 1;
    Integer AVAILABLE_FALSE = 0;

    /**
     * 用户类型
     */
    Integer USER_ROLE_ADMIN = 1;
    Integer USER_ROLE_STAFF = 2;
    Integer USER_ROLE_READER = 3;


    /**
     * 是否展开
     */
    Integer SPREAD_TRUE = 1;
    Integer SPREAD_FALSE = 0;

    /**
     *操作状态
     * */

    String ADD_SUCCESS="添加成功";
    String ADD_ERROR="添加失败";

    String OPERATE_SUCCESS = "操作成功";
    String OPERATE_ERRO = "操作失败";

    String UPDATE_SUCCESS="修改成功";
    String UPDATE_ERROR="修改失败";

    String DELETE_SUCCESS="删除成功";
    String DELETE_ERROR="删除失败";

    String RESET_SUCCESS="重置成功";
    String RESET_ERROR="重置失败";

    String DISPATCH_SUCCESS="分配成功";
    String DISPATCH_ERROR="分配失败";

    Integer CODE_SUCCESS = 0;//操作成功
    Integer CODE_ERROR = -1;//操作失败

    /**
     * 公用常量
     */
    Integer CODE_ZERO = 0;
    Integer CODE_ONE = 1;
    Integer CODE_TWO = 2;
    Integer CODE_THREE = 3;

    /**
     * 用户默认密码
     */
    String USER_DEFAULT_PWD="123456";

    /**
     * 临时文件标记
     */
    String FILE_UPLOAD_TEMP = "_temp";
}
