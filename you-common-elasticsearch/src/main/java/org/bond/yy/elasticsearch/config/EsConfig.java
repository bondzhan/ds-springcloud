package org.bond.yy.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Thstone
 * @version V1.0
 * @Title:
 * @Package com.xingyun.xyb2b.elasticsearch.config
 * @Description: (用一句话描述该文件做�?�?)
 * @date 2018/12/12 20:43
 */
@Configuration
public class EsConfig {

    

    @Bean
    @Autowired
    public RestHighLevelClient getClient(EsSettingsProperties esSettingsProperties){
        
        RestHighLevelClient client = new RestHighLevelClient(
    	        RestClient.builder(
    	        		new HttpHost(esSettingsProperties.getIp(), esSettingsProperties.getPort(), "http")));
        return  client;
    }


}
