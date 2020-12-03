/**   
* @Title: PreFilter.java 
* @Package org.bond.yy.filter 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author bond
* @date 2017��11��17�� ����6:09:58 
* @company ��Ȩ���� �����������ƹ�Ӧ�����޹�˾
* @version V1.0   
*/
package org.bond.yy.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

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
