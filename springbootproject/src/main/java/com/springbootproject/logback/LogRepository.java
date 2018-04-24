package com.springbootproject.logback;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * mongoDB 资源管理器（把MyLog 实体类保存到MongoDB;还可以进行CRUD操作 ）
 *
 * @author WenXin
 */
public interface LogRepository extends MongoRepository<MyLog, String> {
}
