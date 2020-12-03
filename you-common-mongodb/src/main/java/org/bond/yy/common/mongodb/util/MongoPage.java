package org.bond.yy.common.mongodb.util;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class MongoPage<E> extends ArrayList<E> {

    /**
     * 页码，从1�?�?
     */
    private int pageNum;

    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 起始�?
     */
    private int startRow;
    /**
     * 末行
     */
    private int endRow;
    /**
     * 总数
     */
    private long total;
    /**
     * 总页�?
     */

    private int pages;

    private MongoTemplate mongoTemplate;

    private Query query;

    private String collectionName;

    private Class<?> entityClass;

    private List<?> result;

    public MongoPage(){
       super(0);
    }

    public MongoPage(MongoTemplate mongoTemplate,Query query,String collectionName,int pageNum,int pageSize){
       this.mongoTemplate = mongoTemplate;
       this.query = query;
       this.collectionName = collectionName;
       this.pageNum = pageNum;
       this.pageSize = pageSize;
       this.total = count();

    }

   public MongoPage(MongoTemplate mongoTemplate,Query query,Class<?> entityClass,int pageNum,int pageSize){
        this.mongoTemplate = mongoTemplate;
        this.query = query;
        this.entityClass = entityClass;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = count();
        calculateStartAndEndRow();
        setSkipAndLimit();
        this.result = mongoTemplate.find(query,entityClass);
    }

    private long count(){
       if(collectionName == null){
           return mongoTemplate.count(query,entityClass);
       }
        return  mongoTemplate.count(query,collectionName);
    }

    private void setSkipAndLimit(){
        query.limit(startRow);
        query.skip(endRow);
    }


    /**
     * 计算起止行号
     */
    private void calculateStartAndEndRow() {
        this.startRow = this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize : 0;
        int caculateEndRow = this.startRow + this.pageSize * (this.pageNum > 0 ? 1 : 0);
        this.endRow = this.total > caculateEndRow ? (int) this.total :caculateEndRow;
    }



    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public List<?> getResult() {
        return result;
    }

    public void setResult(List<?> result) {
        this.result = result;
    }
}
