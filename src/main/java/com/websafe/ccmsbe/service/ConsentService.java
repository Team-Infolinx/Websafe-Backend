package com.websafe.ccmsbe.service;

import com.websafe.ccmsbe.entity.Consent;
import com.websafe.ccmsbe.entity.CookieCategory;
import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.exception.WebsiteNotFoundException;
import com.websafe.ccmsbe.repository.ConsentRepository;
import com.websafe.ccmsbe.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ConsentService {

    private final WebsiteRepository websiteRepository;
    private final ConsentRepository consentRepository;

    @Autowired
    public ConsentService(WebsiteRepository websiteRepository, ConsentRepository consentRepository) {
        this.websiteRepository = websiteRepository;
        this.consentRepository = consentRepository;
    }

    public Consent addConsent(Long websiteId, List<CookieCategory> allowedCategories) {
        Website website = websiteRepository.findById(websiteId).orElseThrow(
                () -> new WebsiteNotFoundException("Website not found with id " + websiteId)
        );
        Consent consent = new Consent();
        LocalDate currentDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
        consent.setCreatedDate(sqlDate);
        consent.setCreatedAt(new Time(System.currentTimeMillis()));
        consent.setAllowedCookieCategories(allowedCategories);
        if (allowedCategories == null || allowedCategories.isEmpty()) {
            consent.setIsGiven("false");
        }else {
            consent.setIsGiven("true");
        }
        consent.setWebsite(website);
        return consentRepository.save(consent);
    }

}
