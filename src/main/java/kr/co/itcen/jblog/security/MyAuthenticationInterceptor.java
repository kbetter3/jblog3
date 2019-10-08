package kr.co.itcen.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
				response.sendRedirect(request.getContextPath() + "/");
				return false;
			}
		}
		
		return true;
	}
}
