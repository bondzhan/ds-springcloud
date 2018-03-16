/**   
* @Title: AbstractYYFilter.java 
* @Package org.bond.yy.filter 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年3月7日 下午6:35:55 
* @version V1.0   
*/
package org.bond.yy.filter;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.bond.yy.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import io.jsonwebtoken.Claims;

/** 
* @ClassName: AbstractYYFilter 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年3月7日 下午6:35:55 
*  
*/
abstract class AbstractYYFilter extends ZuulFilter {
	
    protected	Logger log = LoggerFactory.getLogger(getClass());
    
	protected static final String PARAM_TOKEN = "access_token";

	public Object validToken() {
		RequestContext context = RequestContext.getCurrentContext();
		try {
			String accessToken = context.getRequest().getHeader(PARAM_TOKEN);
			log.info("accessToken:" + accessToken);
			log.info("params:" + context.getRequest().getParameterMap());
			log.info("contentLength:" + context.getRequest().getContentLength());
			log.info("contentType:" + context.getRequest().getContentType());

			if (StringUtils.isBlank(accessToken)) {
				log.error("Missing or invalid Authorization header.");
				setErrorDefineReponse("Missing or invalid Authorization header.");
				return null;
			}
			Claims claims = JwtUtils.parseJWT(accessToken);
			long nowTime = new Date().getTime();
			if (null == claims || nowTime > claims.getExpiration().getTime()) {
				log.error("token已过期!");
				setErrorDefineReponse("token已过期!");
				return null;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			setErrorDefineReponse("token 验证异常");
			ReflectionUtils.rethrowRuntimeException(e);
			return null;
		}
		return null;
	}
	
	public void setErrorDefineReponse(String resBody){
		RequestContext.getCurrentContext().setSendZuulResponse(false);
		RequestContext.getCurrentContext().setResponseBody(resBody);
	}
}
