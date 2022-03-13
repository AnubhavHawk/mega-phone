package com.bbps.shortner.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bbps.shortner.rest.model.LongUrl;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<LongUrl, Long> {
    LongUrl findByShortUrl(String shortUrl);
    LongUrl findByOriginalUrl(String originalUrl);
}