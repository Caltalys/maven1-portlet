package org.springframework.data.rest.example;

import java.io.Serializable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID>, QueryDslPredicateExecutor<T> {
	long countRsql(@Param("rsql") String query);
	boolean existRsql(@Param("rsql") String query);
	Page<T> findByRsql(@Param("rsql") String query, Pageable unusedDummy);
	Page<T> pageRsql(@Param("rsql") String query, Pageable pageable);
}