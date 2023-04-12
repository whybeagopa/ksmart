package ksmart.mybatis.interceptor;

import java.util.Set;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CommonInterceptor implements HandlerInterceptor{

	
	private static final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);
	
	/*
	 * 핸들러 객체를 결정한 후 HandlerAdapter가 핸들러를 호출하기 전에 호출되는 메소드
	 * return = true(핸들러 메소드 실행), false(핸들러 메소드 실행 x : 핸들러까지 진입 금지)
	 * handler = controller 똑같은거 근데 왜 이름 다르게 말해 그냥 컨트롤러 쓰면 되지; 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//파라미터의 값을 확인
		Set<String> paramKeySet = request.getParameterMap().keySet();
		
		StringJoiner param = new StringJoiner(", ");
		
		// ex:) memberId: id001, memberPw: pw001
		for(String key : paramKeySet) {
			param.add(key + ": " + request.getParameter(key));
		}
		
		log.info("ACCESS INFO ================================= ");
		log.info("PORT  	  	::::::::: {}", request.getLocalPort());
		log.info("ServerName  	::::::::: {}", request.getServerName());
		log.info("HTTPMethod  	::::::::: {}", request.getMethod());
		log.info("URLI  	  	::::::::: {}", request.getRequestURI());
		log.info("CLIENT IP   	::::::::: {}", request.getRemoteAddr());
		log.info("Parameter   	::::::::: {}", param);
		log.info("ACCESS INFO ================================= ");
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	/**
	 * HandlerAdapter가 실제로 핸들러를 호출한 후 DispatcherServlet이 뷰를 렌더링하기 전에 호출되는 메소드
	 * 
	 * 
	 */
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	/**
	 * DispatcherServlet이 뷰를 렌더링하기 전에 호출되는 메소드
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
