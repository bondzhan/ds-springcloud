/**   
* @Title: SeverApplication.java 
* @Package org.bond.yy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2017年11月16日 下午3:13:37 
* @company 版权所有 深圳市天行云供应链有限公司
* @version V1.0   
*/
package org.bond.yy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/** 
* @ClassName: SeverApplication 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2017年11月16日 下午3:13:37 
*  
*/
@EnableEurekaServer
@SpringBootApplication
public class ServerApplication {

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
		SpringApplication.run(ServerApplication.class, args);
	}

}
