package com.urlshort.repository;

import com.urlshort.entity.UrlShortEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UrlShortRepository extends CrudRepository<UrlShortEntity, String> {


    @Query("select object(u) from UrlShortEntity u " +
            "where u.expire < ?1 ")
    List<UrlShortEntity> findByExpire(Date expiredBefore, Pageable p);

}
