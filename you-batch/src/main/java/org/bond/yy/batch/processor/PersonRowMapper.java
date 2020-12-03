/**   
* @Title: PersonRowMapper.java 
* @Package org.bond.batch 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年4月10日 下午4:44:49 
* @version V1.0   
*/
package org.bond.yy.batch.processor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bond.yy.batch.model.Person;
import org.springframework.jdbc.core.RowMapper;


/** 
* @ClassName: PersonRowMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年4月10日 下午4:44:49 
*  
*/
public class PersonRowMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person p = new Person();
//		System.out.println("-----------------------person_id-"
//				+ rs.getInt("person_id"));
		p.setPersonId(rs.getInt("person_id"));
		p.setFirstName(rs.getString("first_name"));
		p.setLastName(rs.getString("last_name"));
		return p;
	}

}
