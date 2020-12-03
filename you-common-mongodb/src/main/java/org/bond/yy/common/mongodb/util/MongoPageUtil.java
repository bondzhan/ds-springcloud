package org.bond.yy.common.mongodb.util;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MongoPageUtil {


    /**
     * 根据query查询条件返回文档条数
     * @Title: count
     * @param mongoTemplate
     * @param query
     * @param collectionName
     * @return long
     * @Author Thstone
     * @date 2018/11/1
     * @throws
     */

    public static long count(MongoTemplate mongoTemplate,Query query, String collectionName){
       return  mongoTemplate.count(query,collectionName);
    }

    /**
     * 根据query查询条件返回文档条数
     * @Title: count
     * @param mongoTemplate
     * @param query
     * @param entityClass
     * @return long
     * @Author Thstone
     * @date 2018/11/1
     * @throws
     */
    public static long count(MongoTemplate mongoTemplate,Query query,  Class<?> entityClass){
        return mongoTemplate.count(query,entityClass);
    }


    /**
     * 分页查询
     * @Title: startPage
     * @param mongoTemplate
     * @param query
     * @param collectionName
     * @param pageIndex
     * @param pageSize
     * @return MongoPage<E>
     * @Author Thstone
     * @date 2018/11/1
     * @throws
     */
    public static <E> MongoPage<E> startPage(MongoTemplate mongoTemplate, Query query, String collectionName, int pageIndex, int pageSize){
      MongoPage<E> mongoPage = new MongoPage<E>(mongoTemplate,query,collectionName,pageIndex,pageIndex);
      return mongoPage;

    }

    /**
     * 分页查询
     * @Title: count
     * @param mongoTemplate
     * @param query
     * @param entityClass
     * @param pageIndex
     * @param pageSize
     * @return MongoPage<E>
     * @Author Thstone
     * @date 2018/11/1
     * @throws
     */

    public static <E> MongoPage<E> startPage(MongoTemplate mongoTemplate, Query query, Class<?> entityClass,Integer pageIndex,Integer pageSize){
        MongoPage<E> mongoPage = new MongoPage<E>(mongoTemplate,query,entityClass,pageIndex,pageIndex);
        return mongoPage;
    }
}
