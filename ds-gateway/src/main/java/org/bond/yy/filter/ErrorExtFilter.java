/**   
* @Title: ErrorExtFilter.java 
* @Package org.bond.yy.filter 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author bond
* @date 2018��2��26�� ����6:29:56 
* @version V1.0   
*/
package org.bond.yy.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;

import com.netflix.zuul.context.RequestContext;

/**
 * @ClassName: ErrorExtFilter
 * @Description: TODO(������һ�仰��������������)
 * @author bond
 * @date 2018��2��26�� ����6:29:56
 * 
 */
public class ErrorExtFilter extends SendErrorFilter {

	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return 30;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		Throwable throwable = ctx.getThrowable();
		log.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());
		ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		ctx.set("error.exception", throwable.getCause());
		ctx.setResponseBody("ErrorExtFilter########################################");
		return null;
	}

}
