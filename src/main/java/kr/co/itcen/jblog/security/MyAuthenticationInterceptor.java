package kr.co.itcen.jblog.security;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;

import kr.co.itcen.jblog.result.ApiResult;
import kr.co.itcen.jblog.type.ResponseCode;

public class MyAuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		if (handlerMethod.getMethodAnnotation(MyAuth.class) != null || handlerMethod.getMethod().getDeclaringClass().getAnnotation(MyAuth.class) != null) {
			HttpSession session = request.getSession();
			
			if (session == null || session.getAttribute("authUser") == null) {
				if (request.getServletPath().startsWith("/api/")) {
					// api 요청일 경우 json 형태로 return
					Gson gson = new Gson();
					ApiResult<Object> apiResult = new ApiResult<>(ResponseCode.FORBIDDEN);
					
					PrintWriter pw = response.getWriter();
					pw.println(gson.toJson(apiResult));
					pw.close();
					
					return false;
				} else {
					response.sendRedirect(request.getContextPath() + "/");
					return false;
				}
			}
		}
		
		return true;
	}
}
