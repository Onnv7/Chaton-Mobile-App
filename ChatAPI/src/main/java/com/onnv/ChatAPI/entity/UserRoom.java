package com.onnv.ChatAPI.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_room")
public class UserRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "nick_name")
    private String nickname;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Message> messages;

//    @OneToMany(mappedBy = "userRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Recipient> recipients;
//
//    @OneToMany(mappedBy = "userRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Sender> sender;
}
