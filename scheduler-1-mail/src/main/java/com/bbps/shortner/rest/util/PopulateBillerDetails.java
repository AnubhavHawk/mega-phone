package com.bbps.shortner.rest.util;

import com.bbps.shortner.rest.controller.UrlController;
import com.bbps.shortner.rest.model.BillerDetails;
import com.bbps.shortner.rest.model.BillerFieldMapping;
import com.bbps.shortner.rest.model.ContactBasedField;
import com.bbps.shortner.rest.model.TimeBasedField;
import com.bbps.shortner.rest.repository.BillerDetailsRepository;
import com.bbps.shortner.rest.repository.BillerFieldMappingRepository;
import com.bbps.shortner.rest.repository.ContactBasedFieldRepository;
import com.bbps.shortner.rest.repository.TimeBasedFIeldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PopulateBillerDetails {

    @Autowired
    private BillerFieldMappingRepository billerFieldMappingRepository;
    @Autowired
    private TimeBasedFIeldRepository timeBasedFIeldRepository;
    @Autowired
    private ContactBasedFieldRepository contactBasedFieldRepository;
    @Autowired
    private BillerDetailsRepository billerDetailsRepository;

    public void insertDetails(String billerId) {
        BillerFieldMapping billerFieldMapping = billerFieldMappingRepository.findByBillerId(billerId);
        TimeBasedField timeBasedField = timeBasedFIeldRepository.findByBillerId(billerId);
        ContactBasedField contactBasedField = contactBasedFieldRepository.findByBillerId(billerId);



        String endpointUrl = billerFieldMapping.getBillerEndpointUrl();
        // Get the data from the API.
        ResponseEntity<?> response = new RestTemplate().getForEntity(endpointUrl, List.class);
        System.out.println(response.getBody() + "\n\n----- Instance of: " + (response instanceof List));
        List<Map<String, Object>> customerList = (ArrayList) response.getBody();
        System.out.println("Customer Data list:\n");
        customerList.stream().forEach(System.out::println);
        System.out.println("------------------------");
        List<?> customerDataValues = null;

        for(Map<String, Object> data: customerList) {
            System.out.println("Data =>" + data);
            System.out.println("Data KeySet =>" + data.keySet());
            System.out.println("Data EntrySet =>" + data.entrySet().toArray()[2]);

            // Store this Biller Detail object into the DB, which will be taken care by the scheduler.
            BillerDetails billerDetails = new BillerDetails();

            customerDataValues = Arrays.stream(data.keySet().toArray()).collect(Collectors.toList());
            billerDetails.setBillerId(billerId);
            billerDetails.setDueDate(data.get(customerDataValues.get(timeBasedField.getTimeFieldIndex())).toString());
            billerDetails.setCustomerMobile(data.get(customerDataValues.get(contactBasedField.getContactFieldIndex())).toString());
            billerDetails.setTimeFormat(timeBasedField.getTimeFormat());
            try {
                billerDetails.setDueDateTimestamp(DateUtil.parse(billerDetails.getDueDate(), billerDetails.getTimeFormat()));
            }catch(Exception e) {
                System.out.println("Unable to parse date!" + billerDetails);
            }
            billerDetails.setNextTriggerDate(""); // <======================================================================================== To be handled by Akash.

//            billerDetails.setCustParams(billerFieldMapping.getBillerMessage().replaceAll("@@##@@", )); // Replace with actual link.
            String shortLink = "";
            String longLink = billerFieldMapping.getBaseUrlLong() + "?";

            // Iterate over each key and check if Biller has configured that field.
            for(String keys: data.keySet()) {
                if(keys.equalsIgnoreCase(billerFieldMapping.getField1())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField2())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField3())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField4())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField5())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField6())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField7())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField8())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField9())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField10())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField11())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField12())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField13())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField14())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField15())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField16())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField17())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField18())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField19())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField20())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField21())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField22())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField23())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField24())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField25())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField26())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField27())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField28())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField29())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField30())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField31())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField32())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField33())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField34())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField35())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField36())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField37())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField38())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField39())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField40())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField41())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField42())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField43())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField44())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField45())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField46())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField47())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField48())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField49())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
                else if(keys.equalsIgnoreCase(billerFieldMapping.getField50())) {
                    if(!longLink.endsWith("?")) { // add &
                        longLink = longLink + "&";
                    }
                    longLink = longLink + keys+"="+data.get(keys);
                }
            }

            System.out.println("========> Long Link: " + longLink);
            // Get the shortLink for this.
            String requestJson = "{\n" +
                    "    \"originalUrl\": \""+longLink+"\",\n" +
                    "    \"billerId\": \""+billerId+"\"}";
            System.out.println("Request JSON:\n" + requestJson);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
            ResponseEntity<Map> shortLinkResponse = new RestTemplate().postForEntity("http://localhost:8082/create", entity, Map.class);
            shortLink = shortLinkResponse.getBody().get("shortUrl").toString();
            System.out.println("\n\n======> This is what came back:" + shortLinkResponse);



//            new UrlController().createShortUrl() // Get the short URL from the long URL.


            billerDetails.setCustParams(billerFieldMapping.getBillerMessage().replaceAll("@@##@@", shortLink));
            billerDetails.setBillerMessage(billerDetails.getCustParams());
            billerDetailsRepository.save(billerDetails);

//            System.out.println("Due Date: " + data.get(customerDataValues.get(timeBasedField.getTimeFieldIndex())));
//            System.out.println("Contact Info: " + data.get(customerDataValues.get(contactBasedField.getContactFieldIndex())));
        }
//        customerList.stream().map(e -> e.entrySet()).map(e -> e.).forEach(System.out::println);

//        System.out.println(customerList.get(0).get(timeBasedField.getTimeFieldIndex()));
        System.out.println("Timebase Field: " + timeBasedField.getTimeFieldIndex());
        System.out.println("Contact based Field: " + contactBasedField.getContactFieldIndex());
        System.out.println(customerList.get(0).get(customerList.get(0).keySet()));
    }
}
