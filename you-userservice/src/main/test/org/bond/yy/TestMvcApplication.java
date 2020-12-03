package org.bond.yy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @ClassName: TestApplication
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author bond
 * @date 2019年7月4日 下午6:14:49
 * 
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class TestMvcApplication {

	Logger log = LoggerFactory.getLogger(getClass());


	@Autowired
	private MockMvc mockMvc;


	@Test
	public void test() {

		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/getUserOrder").requestAttr("orderId", "1")).andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("abc"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
