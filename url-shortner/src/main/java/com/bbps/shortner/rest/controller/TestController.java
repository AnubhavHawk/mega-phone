package com.bbps.shortner.rest.controller;

import com.bbps.shortner.rest.util.PopulateBillerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private PopulateBillerDetails populateBillerDetails;

    @GetMapping("/my-test")
    public String test() {
        populateBillerDetails.insertDetails("BLR001");
        return "done";
    }
}
