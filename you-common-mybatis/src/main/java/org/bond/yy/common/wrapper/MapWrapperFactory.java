/**   
* @Title: MapWrapperFactory.java 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年7月31日 下午5:32:25 
* @version V1.0   
*/
package org.bond.yy.common.wrapper;

import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

/** 
 * 通过包装工厂来创建自定义的包装类,
 * 通过hasWrapperFor判断参数不为空,
 * 并且类型是Map的时候才使用扩展的MapWrapperFactory
* @ClassName: MapWrapperFactory 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2018年7月31日 下午5:32:25 
*  
*/
public class MapWrapperFactory implements ObjectWrapperFactory {

	@Override
	public boolean hasWrapperFor(Object object) {
		return object != null && object instanceof Map;
	}
	
	@Override
	public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
		return new CaseFiledWrapper(metaObject,(Map)object);
	}

}
