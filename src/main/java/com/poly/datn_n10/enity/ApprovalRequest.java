package com.poly.datn_n10.enity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Approval_Requests")
public class ApprovalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "propertyId")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "userId")
    private User owner;

    @Column(nullable = false, length = 10)
    private String status;

    @Column(name = "created_at", columnDefinition = "DATETIME DEFAULT GETDATE()")
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME DEFAULT GETDATE()")
    private Date updatedAt;
}

