package com.bbps.shortner.rest.service;

import com.bbps.shortner.rest.repository.UrlRepository;
import com.bbps.shortner.rest.model.LongUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    public LongUrl getLongUrlfromShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }
    public LongUrl getFromOriginalUrl(String originalUrl) {
        return urlRepository.findByOriginalUrl(originalUrl);
    }
    public LongUrl save(LongUrl longUrl) {
        LongUrl created = getFromOriginalUrl(longUrl.getOriginalUrl());
        if( created == null) {
            created = urlRepository.save(longUrl);
        }
        return created;
    }
    public boolean removeUrl(LongUrl longUrl) {
        urlRepository.delete(longUrl);
        return true;
    }
}
