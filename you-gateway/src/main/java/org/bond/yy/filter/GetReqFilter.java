/**   
* @Title: GetReqFilter.java 
* @Package org.bond.yy.filter 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年3月7日 上午11:23:42 
* @version V1.0   
*/
package org.bond.yy.filter;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.netflix.zuul.context.RequestContext;

public class GetReqFilter extends AbstractYYFilter{
	@Value("${system.config.getReqFilter.ignoreUrls}")
	private Set<String> ignoreUrls;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		return StringUtils.equalsIgnoreCase(RequestContext.getCurrentContext().getRequest().getMethod(), "get")
				&& !ignoreUrls.contains(RequestContext.getCurrentContext().getRequest().getRequestURI().toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() {
		return validToken();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "route";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 9;
	}

}
