/**   
* @Title: BatchController.java 
* @Package org.bond.yy.batch.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2019年3月13日 下午5:23:25 
* @version V1.0   
*/
package org.bond.yy.batch.controller;

import org.bond.yy.batch.base.ApplicationContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** 
* @ClassName: BatchController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2019年3月13日 下午5:23:25 
*  
*/
@RestController
@RequestMapping("/batch")
public class BatchController {
	
	@Autowired
	JobLauncher jobLauncher;
	
	@RequestMapping(value = "/startjob1", method = RequestMethod.GET)
	public void startJob1(){
		try {
			 JobExecution execution = jobLauncher.run((Job)ApplicationContext.getBean("job1"), new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
			 System.out.println(execution.getStatus());
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/startjob2", method = RequestMethod.GET)
	public void startJob2(){
		try {
			 JobExecution execution = jobLauncher.run((Job)ApplicationContext.getBean("job2"), new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
			 System.out.println(execution.getStatus());
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/personToFileJob", method = RequestMethod.GET)
	public void personToFileJob(){
		try {
			JobParametersBuilder jobParameter =	new JobParametersBuilder();
			jobParameter.addLong("timestamp", System.currentTimeMillis());
			jobParameter.addString("firstName", "AAAAAAA");
			 JobExecution execution = jobLauncher.run((Job)ApplicationContext.getBean("personToFileJob"), jobParameter.toJobParameters());
			 System.out.println(execution.getStatus());
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
