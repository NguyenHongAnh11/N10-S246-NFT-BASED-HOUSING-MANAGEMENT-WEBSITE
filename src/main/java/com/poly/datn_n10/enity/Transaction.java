package com.poly.datn_n10.enity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "nft_id", referencedColumnName = "nftId")
    private NFT nft;

    @ManyToOne
    @JoinColumn(name = "from_user_id", referencedColumnName = "userId")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id", referencedColumnName = "userId")
    private User toUser;

    @Column(nullable = false, unique = true, length = 255)
    private String transactionHash;

    @Column(name = "timestamp", columnDefinition = "DATETIME DEFAULT GETDATE()")
    private Date timestamp;
}

