//package com.onnv.ChatAPI.controller;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.onnv.ChatAPI.entity.User;
//import com.onnv.ChatAPI.response.ApiResponse;
//import com.onnv.ChatAPI.service.FriendService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//@RestController
//@RequestMapping(value = "/api/friends")
//public class FriendController {
//    private final FriendService friendService;
//
//    @Autowired
//    public FriendController(FriendService friendService) {
//        this.friendService = friendService;
//    }
//
//    @PostMapping("/send-add-request")
//    public ResponseEntity<ApiResponse> sendFriendRequest(@RequestBody JsonNode info) {
//        try {
//            ApiResponse response = new ApiResponse();
//            Object result = friendService.sendFriendRequest(info);
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
//
////    @PatchMapping("/accept-friend-request")
////    public ResponseEntity<ApiResponse> acceptFriendRequest(@RequestBody Friend friend) {
////        try {
////            boolean result = friendService.acceptFriendRequest(friend);
////            return new ResponseEntity<ApiResponse>(
////                    new ApiResponse().builder()
////                    .result(result)
////                    .message(result ? "Accepted friend request" : "Accept request failed")
////                    .build(),
////                    HttpStatus.BAD_REQUEST
////            );
////        } catch (Exception e) {
////            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
////        }
////    }
//}
