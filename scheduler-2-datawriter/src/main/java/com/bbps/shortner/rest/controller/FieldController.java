package com.bbps.shortner.rest.controller;

import com.bbps.shortner.rest.model.BillerFieldMapping;
import com.bbps.shortner.rest.model.ContactBasedField;
import com.bbps.shortner.rest.model.TimeBasedField;
import com.bbps.shortner.rest.repository.BillerFieldMappingRepository;
import com.bbps.shortner.rest.repository.ContactBasedFieldRepository;
import com.bbps.shortner.rest.repository.TimeBasedFIeldRepository;
import com.bbps.shortner.rest.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@CrossOrigin
public class FieldController {

    @Autowired
    private BillerFieldMappingRepository billerFieldMappingRepository;
    @Autowired
    private TimeBasedFIeldRepository timeBasedFIeldRepository;
    @Autowired
    private ContactBasedFieldRepository contactBasedFieldRepository;

    @PostMapping("/save-biller-fields")
    public ResponseEntity<?> saveBillerFields(@RequestBody LinkedHashMap<String, Object> requestBody) {
        System.out.println("Request Body:\n" + requestBody + "\n\n------------------\n");

        // Extract all the request Body content.
        List<String> fieldList = (ArrayList<String>) (requestBody.containsKey("apiFields") ? requestBody.get("apiFields") : null);
        if(fieldList == null || fieldList.size() == 0) {
            return ResponseEntity.badRequest().body("Field List can't be empty");
        }
        String billerId = (String) (requestBody.containsKey("billerId") ? requestBody.get("billerId") : null);
        if(billerId == null || billerId.trim() == "") {
            return ResponseEntity.badRequest().body("Biller Id can't be empty");
        }
        String billerMsg = (String) (requestBody.containsKey("blrmessage") ? requestBody.get("blrmessage") : null);
        if(billerMsg == null || billerMsg.trim() == "") {
            return ResponseEntity.badRequest().body("billerMsg can't be empty");
        }
        String endpointUrl = (String) (requestBody.containsKey("endpointUrl") ? requestBody.get("endpointUrl") : null);
        if(endpointUrl == null || endpointUrl.trim() == "") {
            return ResponseEntity.badRequest().body("endpointUrl can't be empty");
        }
        String timeFormat = (String) (requestBody.containsKey("timeFormat") ? requestBody.get("timeFormat") : null);
        if(timeFormat == null || endpointUrl.trim() == "") {
            return ResponseEntity.badRequest().body("timeFormat can't be empty");
        }
        int timeFieldIndex = (int) (requestBody.containsKey("timeField") ? requestBody.get("timeField") : null);
        if(timeFieldIndex <= -1) {
            return ResponseEntity.badRequest().body("timeField can't be empty");
        }
        int timeLeftForTrigger = (int) (requestBody.containsKey("timeRemainingInHours") ? requestBody.get("timeRemainingInHours") : null);
        if(timeLeftForTrigger <= -1) {
            return ResponseEntity.badRequest().body("TimeRemainingInHours can't be empty");
        }
        int contactFieldIndex = (int) (requestBody.containsKey("contactField") ? requestBody.get("contactField") : null);
        if(contactFieldIndex <= -1) {
            return ResponseEntity.badRequest().body("contactField can't be empty");
        }
        String baseUrlLong = (String) (requestBody.containsKey("paymentEndpoint") ? requestBody.get("paymentEndpoint") : null);
        if(baseUrlLong == null || endpointUrl.trim() == "") {
            return ResponseEntity.badRequest().body("paymentEndpoint can't be empty");
        }

//        System.out.println(fieldList);
//        System.out.println("TimeField: " + timeField + " TimeFormat: " + timeFormat);
//        System.out.println("ContactField: " + contactField);
//        System.out.println("Endpoint Url: " + endpointUrl);
//        System.out.println("Biller Id: " + billerId);

        int fieldCounter = 1;
        BillerFieldMapping billerFieldMapping = billerFieldMappingRepository.findByBillerId(billerId);
        if( billerFieldMapping == null) {
            billerFieldMapping = new BillerFieldMapping();
        }

        billerFieldMapping.setBillerId(billerId);
        billerFieldMapping.setBillerEndpointUrl(endpointUrl);
        billerFieldMapping.setBillerMessage(billerMsg);
        billerFieldMapping.setTimeLeftForTrigger(timeLeftForTrigger);
        billerFieldMapping.setBaseUrlLong(baseUrlLong);


        // Store all fields
        for(String field: fieldList) {
            if(fieldCounter == 0) { billerFieldMapping.setField1(field); }
            else if(fieldCounter == 1) { billerFieldMapping.setField1(field); }
            else if(fieldCounter == 2) { billerFieldMapping.setField2(field); }
            else if(fieldCounter == 3) { billerFieldMapping.setField3(field); }
            else if(fieldCounter == 4) { billerFieldMapping.setField4(field); }
            else if(fieldCounter == 5) { billerFieldMapping.setField5(field); }
            else if(fieldCounter == 6) { billerFieldMapping.setField6(field); }
            else if(fieldCounter == 7) { billerFieldMapping.setField7(field); }
            else if(fieldCounter == 8) { billerFieldMapping.setField8(field); }
            else if(fieldCounter == 9) { billerFieldMapping.setField9(field); }
            else if(fieldCounter == 10) { billerFieldMapping.setField10(field); }
            else if(fieldCounter == 10) { billerFieldMapping.setField10(field); }
            else if(fieldCounter == 11) { billerFieldMapping.setField11(field); }
            else if(fieldCounter == 12) { billerFieldMapping.setField12(field); }
            else if(fieldCounter == 13) { billerFieldMapping.setField13(field); }
            else if(fieldCounter == 14) { billerFieldMapping.setField14(field); }
            else if(fieldCounter == 15) { billerFieldMapping.setField15(field); }
            else if(fieldCounter == 16) { billerFieldMapping.setField16(field); }
            else if(fieldCounter == 17) { billerFieldMapping.setField17(field); }
            else if(fieldCounter == 18) { billerFieldMapping.setField18(field); }
            else if(fieldCounter == 19) { billerFieldMapping.setField19(field); }
            else if(fieldCounter == 20) { billerFieldMapping.setField20(field); }
            else if(fieldCounter == 21) { billerFieldMapping.setField21(field); }
            else if(fieldCounter == 22) { billerFieldMapping.setField22(field); }
            else if(fieldCounter == 23) { billerFieldMapping.setField23(field); }
            else if(fieldCounter == 24) { billerFieldMapping.setField24(field); }
            else if(fieldCounter == 25) { billerFieldMapping.setField25(field); }
            else if(fieldCounter == 26) { billerFieldMapping.setField26(field); }
            else if(fieldCounter == 27) { billerFieldMapping.setField27(field); }
            else if(fieldCounter == 28) { billerFieldMapping.setField28(field); }
            else if(fieldCounter == 29) { billerFieldMapping.setField29(field); }
            else if(fieldCounter == 30) { billerFieldMapping.setField30(field); }
            else if(fieldCounter == 31) { billerFieldMapping.setField31(field); }
            else if(fieldCounter == 32) { billerFieldMapping.setField32(field); }
            else if(fieldCounter == 33) { billerFieldMapping.setField33(field); }
            else if(fieldCounter == 34) { billerFieldMapping.setField34(field); }
            else if(fieldCounter == 35) { billerFieldMapping.setField35(field); }
            else if(fieldCounter == 36) { billerFieldMapping.setField36(field); }
            else if(fieldCounter == 37) { billerFieldMapping.setField37(field); }
            else if(fieldCounter == 38) { billerFieldMapping.setField38(field); }
            else if(fieldCounter == 39) { billerFieldMapping.setField39(field); }
            else if(fieldCounter == 40) { billerFieldMapping.setField40(field); }
            else if(fieldCounter == 41) { billerFieldMapping.setField41(field); }
            else if(fieldCounter == 42) { billerFieldMapping.setField42(field); }
            else if(fieldCounter == 43) { billerFieldMapping.setField43(field); }
            else if(fieldCounter == 44) { billerFieldMapping.setField44(field); }
            else if(fieldCounter == 45) { billerFieldMapping.setField45(field); }
            else if(fieldCounter == 46) { billerFieldMapping.setField46(field); }
            else if(fieldCounter == 47) { billerFieldMapping.setField47(field); }
            else if(fieldCounter == 48) { billerFieldMapping.setField48(field); }
            else if(fieldCounter == 49) { billerFieldMapping.setField49(field); }
            else if(fieldCounter == 50) { billerFieldMapping.setField50(field); }

            fieldCounter++;
        }
        billerFieldMappingRepository.save(billerFieldMapping);

        // Store time based field
        TimeBasedField timeBasedField = timeBasedFIeldRepository.findByBillerId(billerId);
        if(timeBasedField == null) {
            timeBasedField = new TimeBasedField();
        }
        timeBasedField.setBillerId(billerId);
        timeBasedField.setTimeFormat(timeFormat);
        timeBasedField.setTimeFieldIndex(timeFieldIndex);

        timeBasedFIeldRepository.save(timeBasedField);

        // Store contact based field
        ContactBasedField contactBasedField = contactBasedFieldRepository.findByBillerId(billerId);
        if(contactBasedField == null) {
            contactBasedField = new ContactBasedField();
        }
        contactBasedField.setBillerId(billerId);
        contactBasedField.setContactFieldIndex(contactFieldIndex);
        contactBasedFieldRepository.save(contactBasedField);

        return ResponseEntity.ok().body("{\"result\": true}");
    }

    @PostMapping("/validate-date")
    public ResponseEntity<?> validateDate(@RequestBody LinkedHashMap<String, String> requestBody) {
        System.out.println("RequestBody: " + requestBody);
        try {
            String timeString = requestBody.get("timeString");
            String timeFormat = requestBody.get("timeFormat");
            System.out.println(timeString + " -> " + timeFormat);
            DateUtil.parse(timeString, timeFormat);
        }
        catch (Exception e) {
            return ResponseEntity.ok().body("{\"result\": false}");
        }
        return ResponseEntity.ok().body("{\"result\": true}");
    }
}
