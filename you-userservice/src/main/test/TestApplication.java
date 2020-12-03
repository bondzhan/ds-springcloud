import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.bond.yy.service.UserService;
import org.bond.yy.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import junit.framework.Assert;


/**   
* @Title: TestApplication.java 
* @Package  
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2019年7月4日 下午6:14:49 
* @version V1.0   
*/

/** 
* @ClassName: TestApplication 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2019年7月4日 下午6:14:49 
*  
*/
@RunWith(SpringRunner.class)
public class TestApplication {

	Logger log = LoggerFactory.getLogger(getClass());
    
	@InjectMocks
	UserService userService = new UserServiceImpl();
	
	@Mock
	RestTemplate restTemplate;
	
	@Before
	public void initData(){
//        when(userService.getUserOrderById("1")).thenReturn("james");
		Map<String, String> params1 = new HashMap<String, String>();
	    params1.put("orderId", "1");
        when(restTemplate.getForObject("http://order-service/order/getOrder?orderId={orderId}", String.class, params1)).thenReturn("james");
        
//        when(userService.getUserOrderById("2")).thenReturn("bond");
        Map<String, String> params2 = new HashMap<String, String>();
        params2.put("orderId", "2");
        when(restTemplate.getForObject("http://order-service/order/getOrder?orderId={orderId}", String.class, params2)).thenReturn("bond");

	}
	
	@Test
	public void test(){
		String result = userService.getUserOrderById("2");
		log.info("################" + result);
		Assert.assertEquals(result, "james");
	}
	
	
}
