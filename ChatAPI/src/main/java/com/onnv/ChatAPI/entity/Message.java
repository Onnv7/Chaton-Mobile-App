package com.onnv.ChatAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "message")
public class Message {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    @Column(name = "content", nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "send_at", nullable = false)
    private Date sendAt;

//    @OneToOne(mappedBy = "message", cascade = CascadeType.ALL)
//    private Recipient recipient;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private UserRoom sender;

}
