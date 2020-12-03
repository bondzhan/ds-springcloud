/**   
* @Title: XyResonseEntity.java 
* @Package com.xingyun.xyb2b.common.response 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2016年8月23日 下午3:16:11 
* @version V1.0   
*/
package org.bond.yy.common.response;

import org.bond.yy.common.constant.Xyb2bRetCodeConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/** 
* @ClassName: XyResonseEntity 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
 * @param <T>
 * @param <T>
* @date 2016年8月23日 下午3:16:11 
*  
*/
public class ResonseEntity<T> {
	
	private int retCode;
	private String retMsg;
	private T retEntity;
	
	public int getRetCode() {
		return retCode;
	}
	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public T getRetEntity() {
		return retEntity;
	}
	public void setRetEntity(T retEntity) {
		this.retEntity = retEntity;
	}
	
	public ResonseEntity(int retCode, String retMsg, T retEntity) {
		super();
		this.retCode = retCode;
		this.retMsg = retMsg;
		this.retEntity = retEntity;
	}
	
	public ResonseEntity(int retCode, String retMsg) {
		super();
		this.retCode = retCode;
		this.retMsg = retMsg;
	}
	
	/**
	 * 
	* @Title: 请求处理成功同时返回数据
	* @param @param 返回的实体对象
	* @param @return  返回实体数据
	* @return ResponseEntity<T>    返回类型 
	* @author bond
	* @throws
	 */
	public static <T> ResponseEntity<T> ok(T retEntity){
		return (ResponseEntity<T>) ResponseEntity.ok(new ResonseEntity<T>(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(),retEntity));
	}
	
	/**
	* @Title: 请求处理成功 
	* @param @return  只返回状态码200
	* @return ResponseEntity<T>    返回类型 
	* @author bond
	* @throws
	 */
	public static <T> ResponseEntity<T> ok(){
		return (ResponseEntity<T>) ResponseEntity.ok(new ResonseEntity<T>(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase()));
	}
	
	/**
	* @Title: 请求逻辑出错,返回错误码 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param xyRetcode
	* @param @return  参数说明 
	* @return ResponseEntity<T>    返回类型 
	* @author bond
	* @throws
	 */
	public static <T> ResponseEntity<T> badReq(Xyb2bRetCodeConstant xyRetcode){
		return (ResponseEntity<T>) ResponseEntity.ok(new ResonseEntity<T>(xyRetcode.value(),xyRetcode.getReasonPhrase()));
	}
	
	/**
	 * 自定义提示
	* @Title: ok 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param retEntity
	* @param @param xyRetcode
	* @param @return  参数说明 
	* @return ResponseEntity<T>    返回类型 
	* @author sup
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public static <T> ResponseEntity<T> ok(T retEntity, Xyb2bRetCodeConstant xyRetcode){
		return (ResponseEntity<T>) ResponseEntity.ok(new ResonseEntity<T>(xyRetcode.value(),xyRetcode.getReasonPhrase(),retEntity));
	}
	
	/**
	 * @param xyRetcode 错误代码
	 * @param msg 具体错误信息
	 */
	@SuppressWarnings("unchecked")
	public static <T> ResponseEntity<T> ok(Xyb2bRetCodeConstant xyRetcode,String retMsg){
		return (ResponseEntity<T>) ResponseEntity.ok(new ResonseEntity<T>(xyRetcode.value(),retMsg));
	}
	
}
