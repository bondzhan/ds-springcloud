/**   
* @Title: Xyb2bException.java 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2016年8月22日 下午5:49:45 
* @version V1.0   
*/
package org.bond.yy.common.exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


/** 
* @ClassName: Xyb2bException 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2016年8月22日 下午5:49:45 
*  
*/
@ControllerAdvice
public class Xyb2bException extends Throwable implements HandlerExceptionResolver {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(Xyb2bException.class);
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		try {
			logger.error(exception(ex));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		// 输出错误Json
		ModelAndView mav = new ModelAndView();
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("retCode", "500");
		result.put("retMsg", "服务器异常,请与管理员联系");
		view.setAttributesMap(result);
		mav.setView(view);
		return mav;
	}
	
	/**
	 * 将异常信息转化成字符串
	 * @param t
	 * @return
	 * @throws IOException 
	 */
	private String exception(Throwable t) throws IOException{
	    if(t == null)
	        return null;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    try{
	        t.printStackTrace(new PrintStream(baos));
	    }finally{
	        baos.close();
	    }
	    return baos.toString();
	}

}
