/**   
* @Title: RouteFilter.java 
* @Package org.bond.yy.filter 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2017年11月17日 下午6:23:35 
* @company 版权所有 深圳市天行云供应链有限公司
* @version V1.0   
*/
package org.bond.yy.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/** 
* @ClassName: RouteFilter 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2017年11月17日 下午6:23:35 
*  
*/
public class PostFilter extends ZuulFilter {
	Logger log = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		log.info("PostFilter shouldFilter");
		return true;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() {
		log.info("PostFilter run");
        RequestContext ctx = RequestContext.getCurrentContext();  
        HttpServletRequest request = ctx.getRequest();  
        log.info(request.getRequestURI());
		return null;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "post";
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
