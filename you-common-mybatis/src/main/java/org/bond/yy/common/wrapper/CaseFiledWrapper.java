/**   
* @Title: CaseFiledWrapper.java 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年7月31日 下午5:27:31 
* @version V1.0   
*/
package org.bond.yy.common.wrapper;

import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import com.google.common.base.CaseFormat;

/** 
* @ClassName: CaseFiledWrapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年7月31日 下午5:27:31 
*  
*/
public class CaseFiledWrapper extends MapWrapper {

	public CaseFiledWrapper(MetaObject metaObject, Map<String, Object> map) {
		super(metaObject, map);
	}
	
	@Override
	public String findProperty(String name, boolean useCamelCaseMapping) {
		if(useCamelCaseMapping){
            //转换为驼峰规则
			return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,name);
		}
		return name;
	}

}
