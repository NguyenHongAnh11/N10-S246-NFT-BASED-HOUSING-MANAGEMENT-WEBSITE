package com.poly.datn_n10.enity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Properties", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"address", "type"}) // Đặt ràng buộc unique trên cột address và type
})
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer propertyId;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "userId")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "nft_id", referencedColumnName = "nftId")
    private NFT nft;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 10)
    private String status;

    // Tự động thêm ngày tạo
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    // Tự động cập nhật ngày sửa
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(length = 255)
    private String images;
}
