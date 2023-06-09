//package com.onnv.ChatAPI.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import lombok.*;
//
//@Data
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "friend", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"myself_id", "friend_id"})
//})
//public class Friend {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(nullable = false)
//    private String id;
//
//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "myself_id")
//    @JsonIgnore
//    private User myself;
//
//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "friend_id")
//    @JsonIgnore
//    private User friend;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "relationship", nullable = false)
//    private Relationship relationship;
//
//
//    public enum Relationship {
//        NONE,
//        SEND_REQUEST,
//        RECEIVE_REQUEST,
//        FRIEND,
//        BLOCKED,
//    }
//}
