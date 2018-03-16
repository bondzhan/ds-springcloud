package org.bond.yy;

import org.bond.yy.filter.AccessTokenFilter;
import org.bond.yy.filter.ErrorFilter;
import org.bond.yy.filter.GetReqFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class GatewayApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(GatewayApplication.class,args);
	}
	
	@Bean
	public AccessTokenFilter setAccessTokenFilterBean(){
		return new AccessTokenFilter();
	}
	
	@Bean
	public GetReqFilter setGetReqFilterBean(){
		return new GetReqFilter();
	}
	
//	@Bean
//	public PostFilter setPostFilterBean(){
//		return new PostFilter();
//	}
//	
//	@Bean
//	public RouteFilter setRouteFilterBean(){
//		return new RouteFilter();
//	}
//	
//	@Bean
//	public PreFilter setPreFilterBean(){
//		return new PreFilter();
//	}
//	
	@Bean
	public ErrorFilter setErrorFilterBean(){
		return new ErrorFilter();
	}
	
//	@Bean
//	public ErrorExtFilter setErrorExtFilterBean(){
//		return new ErrorExtFilter();
//	}

}
