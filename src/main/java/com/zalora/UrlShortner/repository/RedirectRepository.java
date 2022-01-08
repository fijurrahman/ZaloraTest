package com.zalora.UrlShortner.repository;

import com.zalora.UrlShortner.entity.URLEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedirectRepository extends JpaRepository<URLEntity, Long> {

    Optional<URLEntity> findByAlias(String alias);

    Boolean existsByAlias(String alias);
}
