package com.dvtrsc.restexample.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/* UidInterceptor. Registers in log real source IP (theoretically nginx) and identifies each request with a long uid, for easy grep on log 
 * D.Tordera, 20171031
 */

@Component
public class UidInterceptor extends  HandlerInterceptorAdapter {

		Random rnd = new Random();
	
		private static final Logger logger = Logger.getLogger(UidInterceptor.class);
				
		@Override 
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		{ 
			long uid = rnd.nextLong();			
			uid = uid < 0 ? -uid : uid;
			
			logger.info("[" + uid + "] Request received from " + request.getLocalAddr() + " (proxied from " + request.getHeader("X-Real-IP") + ")");
			request.setAttribute("uid", uid);	
			
			return true;
		}	
}
