package org.bond.yy.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.zuul.context.RequestContext;

@RestController
public class ErrorController extends AbstractErrorController {

	Logger log = LoggerFactory.getLogger(getClass());

	public ErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.boot.autoconfigure.web.ErrorController#getErrorPath()
	 */
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}

	@RequestMapping("/error")
	public ResponseEntity error(HttpServletRequest request) {
		HttpStatus status = getStatus(request);
		RequestContext ctx = RequestContext.getCurrentContext();
		log.info(ctx.getResponseBody());
		if (status.is5xxServerError()) {
			// log maybe
		}
		return new ResponseEntity(ctx.getResponseBody(),status);
	}

}
