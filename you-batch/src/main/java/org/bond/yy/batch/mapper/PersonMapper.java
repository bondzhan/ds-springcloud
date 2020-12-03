package org.bond.yy.batch.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.bond.yy.batch.model.Person;

public interface PersonMapper {
	
    List<Person> queryUser(String firstName);
}
