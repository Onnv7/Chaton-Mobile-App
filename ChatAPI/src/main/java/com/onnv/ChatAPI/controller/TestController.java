package com.onnv.ChatAPI.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.onnv.ChatAPI.entity.Test;
import com.onnv.ChatAPI.response.ApiResponse;
import com.onnv.ChatAPI.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/tests")
public class TestController {
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

//    @PostMapping("/send-add-request")
//    public ResponseEntity<ApiResponse> sendFriendRequest(@RequestBody Test body) {
//        try {
//            ApiResponse response = new ApiResponse();
//            Object result = testService.savedb("id");
//            if(result == null) {
//                response = new ApiResponse(false, "Send failure", result);
//                return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
//            }
//            response = new ApiResponse(true, "Send successfully", result);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//    }
    @PostMapping("/add-test")
    public ResponseEntity<ApiResponse> addTest(@RequestBody Test body) {
        try {
            ApiResponse response = new ApiResponse();
            Object result = testService.savedb(body);
            if(result == null) {
                response = new ApiResponse(false, "Send failure", result);
                return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
            }
            response = new ApiResponse(true, "Send successfully", result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
