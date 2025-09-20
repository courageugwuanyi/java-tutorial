package com.cloudtruss.peopledb_web.data;

import com.cloudtruss.peopledb_web.biz.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Spring is generating of our CRUD methods from the PersonRepository Interface
 * Basically, SpringData generates a PersonRepository class with all the Crud methods at runtime
 * So there is no implementation of the Interface
 * We can introduce our own custom methods and have them implemented at runtime by SprindData using native SQL
 *
 * In general -> introduce custom method without query but use the keywords like "find", or "get"
 * By default SpringData assumes you want to return the full record.
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long>, PagingAndSortingRepository<Person, Long> {

    @Query(nativeQuery = true, value = "select photo_file_name from person where id in :ids")
    public Set<String> findFileNamesByIds(@Param("ids") Iterable<Long> ids);
}
