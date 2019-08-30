package com.cyq7on.crud.common.security;

import com.cyq7on.crud.common.utils.JsonUtil;
import com.cyq7on.crud.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Long
 * @Date: Create in 2018/4/6 11:18
 * @Description:
 */
@Slf4j
public class UserTokenFilter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) request);

        if (StringUtils.isEmpty(token)) {
            return null;
        }

        return new UserToken(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return ((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name());
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String token = getRequestToken((HttpServletRequest) request);
        if (StringUtils.isEmpty(token)) {
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            String errorInfo = JsonUtil.obj2String(Result.fail(401));
            try {
                servletResponse.setCharacterEncoding("UTF-8");
                servletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                servletResponse.setContentType("application/json;charset=UTF-8");
//                servletResponse.setHeader("Access-Control-Allow-Origin","*");

                response.getWriter().print(errorInfo);
                return false;
            } catch (IOException exception) {
            }
        }
        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        String errorInfo = JsonUtil.obj2String(Result.fail(401));
        try {
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            servletResponse.setContentType("application/json;charset=UTF-8");
//            servletResponse.setHeader("Access-Control-Allow-Origin","*");
            response.getWriter().print(errorInfo);
        } catch (IOException exception) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader("Authorization");

        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isEmpty(token)) {
            token = httpRequest.getParameter("Authorization");
        }

        return token;
    }

}
