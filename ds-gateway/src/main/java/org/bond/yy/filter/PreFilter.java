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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;

/** 
* @ClassName: PreFilter 
* @Description: TODO(������һ�仰��������������) 
* @author bond
* @date 2017��11��17�� ����6:09:58 
*  
*/
public class PreFilter extends ZuulFilter{

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		log.info("PreFilter shouldFilter");
		return false;
	}

	@Override
	public Object run() {
		log.info("PreFilter run");
		return null;
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
