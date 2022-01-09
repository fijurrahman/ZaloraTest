package com.zalora.UrlShortner.repository;

import com.zalora.UrlShortner.entity.URLtbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This Interface is responsible to find and validate the alias
 */

@Repository
public interface RedirectRepository extends JpaRepository<URLtbl, Long> {

    Optional<URLtbl> findByAlias(String alias);

    Boolean existsByAlias(String alias);
}
