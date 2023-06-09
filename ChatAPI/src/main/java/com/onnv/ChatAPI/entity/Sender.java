//package com.onnv.ChatAPI.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.ManyToAny;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "sender")
//public class Sender {
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Id
//    private String id;
//
////    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
////    @JoinColumn(name = "user_room_id")
////    private UserRoom userRoom;
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "message_id")
//    private Message message;
//
//}
