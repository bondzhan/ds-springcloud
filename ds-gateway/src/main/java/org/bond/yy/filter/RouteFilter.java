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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;

/** 
* @ClassName: RouteFilter 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2017年11月17日 下午6:23:35 
*  
*/
public class RouteFilter extends ZuulFilter {
	Logger log = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		log.info("RouteFilter shouldFilter");
		return false;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() {
		log.info("RouteFilter run");
		return null;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "route";
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
