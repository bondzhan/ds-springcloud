/**   
* @Title: ScheduledTaskService.java 
* @Package com.xingyun.xyb2b.databatch.schedule 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2018年4月13日 下午5:25:48 
* @version V1.0   
*/
package org.bond.yy.batch.schedule;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ScheduledTaskService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author bond
 * @date 2018年4月13日 下午5:25:48
 * 
 */
//@Service
public class ScheduledTaskService {
//	@Autowired
//	JobLauncher jobLauncher;
//
//	@Autowired
//	Job importPersonToFileJob;
//	
//	@Autowired
//	Job updateDuetimeOrderJob;

	public JobParameters jobParameters;

//	@Scheduled(fixedRate = 1000)
	public void execute() throws Exception {
//		jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
//		jobLauncher.run(importPersonToFileJob, jobParameters);
	}
	
	/**
	 * 定时任务-获取超期未通关订单并将对应订单是否通关状态更新
	* @Title: GoodsOrderExecute  
	* @Description: 获取超期未通关订单-定时任务（每天8点、12点、16点自动更新），定时任务单位以毫秒计
	* @param @throws Exception    参数  
	* @return void    返回类型  
	* @author X.W
	* @throws
	 */
	//@Scheduled(cron="0 0 8,12,16,18 * * ?")
//	@Scheduled(fixedRate = 1000*60)
	public void GoodsOrderExecute() throws Exception {
//		jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
//		jobLauncher.run(updateDuetimeOrderJob, jobParameters);
	}
}
