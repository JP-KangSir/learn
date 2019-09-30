package com.learning.system.demo.repository;

import com.learning.system.demo.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * ElasticsearchRepository --> ElasticsearchCrudRepository --> PagingAndSortingRepository --> CrudRepository
 * @author zhengkai.blog.csdn.net
 */
@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, Integer> {

}
