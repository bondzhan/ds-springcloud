/**   
* @Title: DashboardApplication.java 
* @Package org.bond.yy 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年3月14日 下午3:50:32 
* @version V1.0   
*/
package org.bond.yy;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/** 
* @ClassName: DashboardApplication 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年3月14日 下午3:50:32 
*  
*/
@EnableHystrixDashboard
@SpringCloudApplication
public class DashboardApplication {

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
		SpringApplication.run(DashboardApplication.class, args);
	}

}
