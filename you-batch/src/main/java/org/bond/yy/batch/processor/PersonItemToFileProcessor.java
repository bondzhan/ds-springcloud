/**   
* @Title: PersonItemToFileProcessor.java 
* @Package org.bond.batch 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年4月11日 上午11:07:45 
* @version V1.0   
*/
package org.bond.yy.batch.processor;

import org.bond.yy.batch.model.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


/** 
* @ClassName: PersonItemToFileProcessor 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年4月11日 上午11:07:45 
*  
*/
@Component
public class PersonItemToFileProcessor implements ItemProcessor<Person, String> {

	@Override
	public String process(Person person) throws Exception {
		// TODO Auto-generated method stub
		return person.getPersonId() + "," + person.getFirstName() + "," + person.getLastName();
	}

}
