//package com.onnv.ChatAPI.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Date;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "recipient")
//public class Recipient {
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Id
//    private String id;
//
////    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
////    @JoinColumn(name = "user_room_id")
////    private UserRoom userRoom;
//
//    @Column(name = "is_readed", nullable = false)
//    private boolean isReaded;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "readed_at", nullable = false)
//    private Date readedAt;
//
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "message_id")
//    private Message message;
//
//}
