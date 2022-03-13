package com.bbps.shortner.rest.controller;

import com.bbps.shortner.rest.model.AdminConfiguration;
import com.bbps.shortner.rest.model.CreationResponse;
import com.bbps.shortner.rest.model.LongUrl;
import com.bbps.shortner.rest.service.AdminConfigurationService;
import com.bbps.shortner.rest.service.UrlService;
import com.bbps.shortner.rest.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@CrossOrigin
@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;
    @Autowired
    private AdminConfigurationService adminConfigurationService;


    // For creating the new short URL
    @PostMapping("/create")
    public ResponseEntity<?> createShortUrl(@RequestBody LinkedHashMap<Object, Object> creationRequest) {

        // Get the admin configurations, so users can't create URL with long expirations than set by Admin
        AdminConfiguration adminConfiguration = adminConfigurationService.getAdminConfiguration();

        // CreationRequest (longUrl, duration, username)
        Long validity = adminConfiguration.getDefaultValidity();
        if(creationRequest.containsKey("validity") && validity <= adminConfiguration.getMaxValidity()) {
            validity = new Long((int) creationRequest.get("validity"));
        }

        String originalUrl = (String) creationRequest.get("originalUrl");
        String billerId = (String) creationRequest.get("billerId");

        LongUrl longUrl = null;

        String shortCode = generateCode(7);

        LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));

        LongUrl createdLongUrl = urlService.save(new LongUrl(originalUrl, shortCode, currentTime, currentTime.plusSeconds(validity), billerId));
        return ResponseEntity.ok(new CreationResponse(createdLongUrl.getOriginalUrl(), adminConfiguration.getDefaultDomain()+"/"+createdLongUrl.getShortUrl(), createdLongUrl.getShortUrl(), createdLongUrl.getCreateAt(),createdLongUrl.getValidTill(),createdLongUrl.getCreator()));
    }

    @PostMapping("/bulk-create")
    public ResponseEntity<?> createBulkShortURL(@RequestBody Map<Object, Object> requestBody) {
        List<String> originalUrlList = (List<String>) requestBody.get("originalUrlList");

        AdminConfiguration adminConfiguration = adminConfigurationService.getAdminConfiguration();
        Long validity = adminConfiguration.getDefaultValidity();
        if(requestBody.containsKey("validity") && validity <= adminConfiguration.getMaxValidity()) {
            validity = new Long((int) requestBody.get("validity"));
        }
        String billerId = (String) requestBody.get("billerId");
        List<Object> shortUrlResponseList = new ArrayList<>();
        for(String originalUrl: originalUrlList) {
            String shortCode = generateCode(7);
            LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
            try {
                LongUrl createdLongUrl = urlService.save(new LongUrl(originalUrl, shortCode, currentTime, currentTime.plusSeconds(validity), billerId));
                CreationResponse cr = new CreationResponse(createdLongUrl.getOriginalUrl(), adminConfiguration.getDefaultDomain()+"/"+createdLongUrl.getShortUrl(), createdLongUrl.getShortUrl(), createdLongUrl.getCreateAt(),createdLongUrl.getValidTill(),createdLongUrl.getCreator());
                shortUrlResponseList.add(cr);
            }
            catch (Exception e) {
                shortUrlResponseList.add(new LinkedHashMap<>().put(billerId, null));
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok(shortUrlResponseList);
    }

    // For getting the original URL
    @GetMapping("/{code}")
    public ResponseEntity<?> routeToLongUrl(HttpServletResponse response, @PathVariable("code") String code){
        try {
            String originalUrl = null; // get this from JPA
            LongUrl longUrl = urlService.getLongUrlfromShortUrl(code);
            if(longUrl != null && longUrl.getValidTill().isAfter(LocalDateTime.now())) {
                originalUrl = longUrl.getOriginalUrl();
            }else {
                return ResponseEntity.badRequest().body("URL Not Available or expired!");
            }
            response.sendRedirect(originalUrl);
        }
        catch (IOException e) {
            return ResponseEntity.badRequest().body("URL Not Found!");
        }
        return ResponseEntity.noContent().build();
    }

    // Delete the URL from the records.
    @GetMapping("/expire/{code}")
    public ResponseEntity<?> expireUrl(HttpServletResponse response, @PathVariable("code") String code){
        LongUrl longUrl = null;
        String originalUrl = null; // get this from JPA
        longUrl = urlService.getLongUrlfromShortUrl(code);
        if(longUrl != null) {
            urlService.removeUrl(longUrl);
            return ResponseEntity.ok(new LinkedHashMap<String, String>().put(longUrl.getShortUrl(), "Deleted"));
        }
        else {
            return ResponseEntity.badRequest().body("Url Doesn't exist");
        }
    }
    @GetMapping("/home")
    public String getHome() {
        return "Hello from Home";
    }

    public String generateCode(int length) {
        String shortCode = null;
        while(true) {
            shortCode = new StringUtils().getRandomString(7);
            if(urlService.getLongUrlfromShortUrl(shortCode) == null) {
                return shortCode;
            }
        }
    }
}