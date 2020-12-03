package org.bond.yy.common.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class MongoConfig {

   //覆盖默认的MongoDbFacotry
   @Bean
   @Autowired
   public MongoDbFactory mongoDbFactory(MongoSettingsProperties properties) {
        MongoClient mongoClient = this.mongoClient(properties);
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, properties.getDatabase());
        return mongoDbFactory;
  }

  @Bean
  @Autowired
  public MongoClient mongoClient(MongoSettingsProperties properties){
       //客户端配置（连接数，副本集群验证�?
       MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
       builder.connectionsPerHost(properties.getMaxConnectionsPerHost());
       builder.minConnectionsPerHost(properties.getMinConnectionsPerHost());
       if (properties.getReplicaSet() != null) {
            builder.requiredReplicaSetName(properties.getReplicaSet());
       }

       builder.threadsAllowedToBlockForConnectionMultiplier(properties.getThreadsAllowedToBlockForConnectionMultiplier());
       builder.serverSelectionTimeout(properties.getServerSelectionTimeout());
       builder.maxWaitTime(properties.getMaxWaitTime());
       builder.maxConnectionIdleTime(properties.getMaxConnectionIdleTime());
       builder.maxConnectionLifeTime(properties.getMaxConnectionLifeTime());
       builder.connectTimeout(properties.getConnectTimeout());
       builder.socketTimeout(properties.getSocketTimeout());
       //builder.socketKeepAlive(properties.getSocketKeepAlive());
       builder.sslEnabled(properties.getSslEnabled());
       builder.sslInvalidHostNameAllowed(properties.getSslInvalidHostNameAllowed());
       builder.alwaysUseMBeans(properties.getAlwaysUseMBeans());
       builder.heartbeatFrequency(properties.getHeartbeatFrequency());
       builder.minHeartbeatFrequency(properties.getMinHeartbeatFrequency());
       builder.heartbeatConnectTimeout(properties.getHeartbeatConnectTimeout());
       builder.heartbeatSocketTimeout(properties.getHeartbeatSocketTimeout());
       builder.localThreshold(properties.getLocalThreshold());
       MongoClientOptions mongoClientOptions = builder.build();
       // MongoDB地址列表
       ServerAddress serverAddress = new ServerAddress(properties.getHost(), properties.getPort());
       // 连接认证
       MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(properties.getUsername(), properties.getDatabase(),properties.getPassword().toCharArray());

       List<MongoCredential>  mongoCredentialList = new ArrayList<>();
       mongoCredentialList.add(mongoCredential);
       //创建客户端和Factory
       MongoClient mongoClient = new MongoClient(serverAddress, mongoCredentialList, mongoClientOptions);
       return mongoClient;
  }
}
