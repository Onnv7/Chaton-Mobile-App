package com.onnv.ChatAPI.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.onnv.ChatAPI.entity.Test;
import com.onnv.ChatAPI.entity.Test1;
import com.onnv.ChatAPI.response.ApiResponse;
import com.onnv.ChatAPI.service.Test1Service;
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
public class Test1Controller {
    private final Test1Service test1Service;

    @Autowired
    public Test1Controller(Test1Service testService) {
        this.test1Service = testService;
    }

    @PostMapping("/add-test1")
    public ResponseEntity<ApiResponse> sendFriendRequest(@RequestBody JsonNode body) {
        try {
            String id = body.get("id").textValue();
            System.out.println("+>>>>>>" + id);
            ApiResponse response = new ApiResponse();
            Object result = test1Service.saveDB(id);
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
