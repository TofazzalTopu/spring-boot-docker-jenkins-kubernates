package com.info.demo.controller;

import com.info.demo.model.ria.SearchApiRequest;
import com.info.demo.service.impl.ria.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = {"/apiservice"})

public class RmsApiController {

    private static final Logger logger = LoggerFactory.getLogger(RmsApiController.class);

    @Autowired
    private ApiService apiService;

    @GetMapping(value = "/remittance")
    public ResponseEntity<String> searchRemittance(@RequestHeader(value = "userId", required = false) String userId,
                                                   @RequestHeader(value = "password", required = false) String password,
                                                   @RequestParam("bruserid") String bruserid,
                                                   @RequestParam("brcode") String brcode, @RequestParam("exchcode") String exchcode,
                                                   @RequestParam("pinno") String pinno, HttpServletRequest request) {
        SearchApiRequest searchApiRequest = new SearchApiRequest(bruserid, brcode, exchcode, pinno, null);
        return new ResponseEntity<>(apiService.searchRemittance(userId, password, searchApiRequest, request), HttpStatus.OK);
    }

    @PutMapping(value = "/remittance")
    public ResponseEntity<String> payRemittance(
            @RequestHeader(value = "userId", required = false) String userId,
            @RequestHeader(value = "password", required = false) String password,
            @RequestBody String data, HttpServletRequest request) {
        return new ResponseEntity<>(apiService.payRemittance(userId, password, data, request), HttpStatus.OK);
    }


}
