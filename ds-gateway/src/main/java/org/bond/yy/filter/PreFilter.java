/**   
* @Title: PreFilter.java 
* @Package org.bond.yy.filter 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2017年11月17日 下午6:09:58 
* @company 版权所有 深圳市天行云供应链有限公司
* @version V1.0   
*/
package org.bond.yy.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @ClassName: PreFilter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author bond
 * @date 2017年11月17日 下午6:09:58
 * 
 */
public class PreFilter extends ZuulFilter {
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		log.info("PreFilter shouldFilter");
		return true;
	}

	@Override
	public Object run() {
		log.info("PreFilter run");
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(request.getRemoteAddr() + " : " + request.getRequestURI());
//		doSomething();
//		ctx.setSendZuulResponse(false);
//        int i = 1/0;
//		ctx.setResponseBody("PreFilter########################################");
//		ctx.getResponse().setContentType("text/html;charset=UTF-8");
		return null;
	}

	private void doSomething() {
		throw new RuntimeException("Exist some errors...");
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
