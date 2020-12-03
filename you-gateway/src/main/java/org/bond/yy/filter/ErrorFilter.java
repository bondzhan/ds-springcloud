/**   
* @Title: ErrorFilter.java 
* @Package org.bond.yy.filter 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author bond
* @date 2018��2��24�� ����11:58:17 
* @version V1.0   
*/
package org.bond.yy.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class ErrorFilter extends ZuulFilter {
	Logger log = LoggerFactory.getLogger(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		Throwable throwable = ctx.getThrowable();
		log.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());
		ctx.set("error.status_code", ctx.getResponseStatusCode());
		ctx.set("error.exception", throwable.getCause());
		ctx.setResponseStatusCode(ctx.getResponseStatusCode());
		ctx.setResponseBody(ctx.getResponseBody());
		ctx.getResponse().setContentType("text/html;charset=UTF-8");
        log.info(ctx.getResponseBody());
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "error";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
