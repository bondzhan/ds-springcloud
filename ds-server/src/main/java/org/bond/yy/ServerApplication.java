/**   
* @Title: SeverApplication.java 
* @Package org.bond.yy 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author bond
* @date 2017��11��16�� ����3:13:37 
* @company ��Ȩ���� �����������ƹ�Ӧ�����޹�˾
* @version V1.0   
*/
package org.bond.yy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/** 
* @ClassName: SeverApplication 
* @Description: TODO(������һ�仰��������������) 
* @author bond
* @date 2017��11��16�� ����3:13:37 
*  
*/
@EnableEurekaServer
@SpringBootApplication
public class ServerApplication {

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
		SpringApplication.run(ServerApplication.class, args);
	}

}
