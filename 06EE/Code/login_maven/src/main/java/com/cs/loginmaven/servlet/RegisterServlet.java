package com.cs.loginmaven.servlet;


import com.cs.loginmaven.bean.User;
import com.cs.loginmaven.impl.DataBaseImp;
import com.cs.loginmaven.utils.FileUpLoadUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author ：TrialCat
 * @description：注册处理
 * @date ：2024/05/15 20:58
 */

public class RegisterServlet{

    /**
     * 注册处理方法
     * @param req ：请求
     * @param resp ：响应
     */
    public static void dispose(HttpServletRequest req, HttpServletResponse resp) throws Exception {
       // 将表单信息封装
        Map<String, String> params = FileUpLoadUtils.parseRequest(req);
        // 实体化
        User user = new User();
        BeanUtils.populate(user, params);

        //判断密码和确认密码是否保持一致
        String password = user.getPassword();
        String confirmPassword = user.getConfirmPassword();
        if(password == null || confirmPassword == null){
            resp.getWriter().println("密码不能为空！");
            return;
        }
        if(!password.equals(confirmPassword)){
            resp.getWriter().println("两次密码不一致!");
            return;
        }

        // 插入数据库
        Integer column = DataBaseImp.insertUser(user);
        if(column == 0){
            System.out.println("插入失败！");
        }
        // 插入成功,跳转只登录界面
        resp.getWriter().println("即将跳转至登录界面！");
        resp.setHeader("refresh","1;url="+ req.getContextPath() + "/index.html");
    }
}
