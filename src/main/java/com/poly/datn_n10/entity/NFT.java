package com.poly.datn_n10.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "NFTs")
public class NFT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nftId;

    @Column(nullable = false, unique = true, length = 255)
    private String tokenId;

    @Column(nullable = false, length = 255)
    private String contractAddress;

    @Column(length = 255)
    private String metadataUrl;

    @Column(name = "minted_at", columnDefinition = "DATETIME DEFAULT GETDATE()")
    private Date mintedAt;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "userId")
    private User owner;
}

