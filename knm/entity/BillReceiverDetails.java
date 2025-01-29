package com.knm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bill_receiver_details")
public class BillReceiverDetails {

    @Id
    @Column(name = "invoice_no", nullable = false)
    private int invoiceNo; // Primary key

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "state_code", nullable = false)
    private int stateCode;

    @Column(name = "gst_no", length = 15)
    private String gstNo;

    @Column(name = "remarks", length = 255)
    private String remarks;

    @Column(name = "crted_by")
    private String creteadBy;

    @Column(name = "uptd_by")
    private String updatedBy;

    @Column(name = "uptd_dt")
    private Timestamp UpdatedDate;


    @OneToMany(mappedBy = "billReceiverDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BillReceiverAmount> billReceiverAmounts;

}
