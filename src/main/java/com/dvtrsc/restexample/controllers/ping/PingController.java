package com.dvtrsc.restexample.controllers.ping;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dvtrsc.restexample.FH;
import com.dvtrsc.restexample.entities.Ping;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;


/*
 * PingController. Check if API is alive. It returns server Unix timestamp. Can be used for synchronization.  
 * D.Tordera, 20171031
 */

@RestController
@RequestMapping("/ping")
public class PingController {
 
	private static final Logger logger = Logger.getLogger(PingController.class);
		
	@ApiOperation(value="Check API working status (with echo & validation)")
	@ApiImplicitParams({
		@ApiImplicitParam(name="U", value="login user (USER/p4ssw0rd or any user and pass=user)", required=true, dataType= "string", paramType="header"),
		@ApiImplicitParam(name="ST", value="salt (Unix timestamp)", required=true, dataType="long", paramType="header"),
		@ApiImplicitParam(name="SH", value="salted hash (hexsha256 of hexsha256(password) concat with hexsha256(salt), all lowercase)", required=true, dataType="string", paramType="header")
	})

	@RequestMapping(value="/{echo}", method=RequestMethod.GET, produces="application/json")				
	public Ping doPing(HttpServletRequest request, @PathVariable("echo") String echo)
	{
		logger.info(FH.uid(request) + "Ping called with echo " + echo + " & responsed");		
		return new Ping(echo);
	}
	
	@ApiOperation(value="Check API working status")	
	@RequestMapping(value="", method=RequestMethod.GET, produces="application/json")
	public Ping doPing(HttpServletRequest request)
	{	
		logger.info(FH.uid(request) + "Ping called/responsed");		
		return new Ping("pong");
	}
}
