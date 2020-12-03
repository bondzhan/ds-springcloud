package org.bond.yy.batch.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Thstone
 * @version V1.0
 * @Title:
 * @Package com.xingyun.s2b2c.common.base
 * @Description: (ApplicationContext工具类)
 * @date 2018/9/3 16:25
 */
@Component
public class ApplicationContext implements ApplicationContextAware {

    private static org.springframework.context.ApplicationContext ctx;

    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext)
            throws BeansException {
        ApplicationContext.ctx = applicationContext;
    }

    public static Object getBean(String beanName){
        return ctx.getBean(beanName);
    }
    
    public static boolean isContain(String beanName) {
    	return ctx.containsBean(beanName);
    }
}
