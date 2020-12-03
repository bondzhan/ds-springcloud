/**   
* @Title: OrderServiceApplication.java 
* @Package org.bond.yy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年3月9日 下午5:37:47 
* @version V1.0   
*/
package org.bond.yy;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/** 
* @ClassName: OrderServiceApplication 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年3月9日 下午5:37:47 
*  
*/
@SpringCloudApplication
@ComponentScan(basePackages="org.bond.yy")
public class OrderServiceApplication {

	public static void main(String args[]){
		SpringApplication.run(OrderServiceApplication.class, args);
	}
		
}
