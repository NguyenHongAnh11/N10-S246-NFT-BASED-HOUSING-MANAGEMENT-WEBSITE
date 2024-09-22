package com.poly.datn_n10.dao;

import com.poly.datn_n10.entity.NFT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NFTDao extends JpaRepository<NFT, Integer> {
    // Custom query methods (if needed) can be defined here
    NFT findByTokenId(String tokenId);
}