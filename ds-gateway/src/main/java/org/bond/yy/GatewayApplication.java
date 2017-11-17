/**   
* @Title: GatewayApplication.java 
* @Package org.bond.yy 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author bond
* @date 2017��11��15�� ����7:23:45 
* @company ��Ȩ���� �����������ƹ�Ӧ�����޹�˾
* @version V1.0   
*/
package org.bond.yy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/** 
* @ClassName: GatewayApplication 
* @Description: TODO(������һ�仰��������������) 
* @author bond
* @date 2017��11��15�� ����7:23:45 
*  
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class GatewayApplication {

	/** 
	* @Title: main 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param args  ����˵�� 
	* @return void    �������� 
	* @author bond
	* @throws 
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(GatewayApplication.class,args);
	}

}