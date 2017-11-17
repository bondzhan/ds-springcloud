/**   
* @Title: GatewayApplication.java 
* @Package org.bond.yy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2017年11月15日 下午7:23:45 
* @company 版权所有 深圳市天行云供应链有限公司
* @version V1.0   
*/
package org.bond.yy;

import org.bond.yy.filter.PostFilter;
import org.bond.yy.filter.PreFilter;
import org.bond.yy.filter.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/** 
* @ClassName: GatewayApplication 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2017年11月15日 下午7:23:45 
*  
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class GatewayApplication {

	/** 
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args  参数说明 
	* @return void    返回类型 
	* @author bond
	* @throws 
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(GatewayApplication.class,args);
	}
	
	@Bean
	public PostFilter setPostFilterBean(){
		return new PostFilter();
	}
	
	@Bean
	public RouteFilter setRouteFilterBean(){
		return new RouteFilter();
	}
	
	@Bean
	public PreFilter setPreFilterBean(){
		return new PreFilter();
	}

}
