package com.knm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bill_receiver_amount")
public class BillReceiverAmount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "serial_no", nullable = false)
    private int serialNo;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "quantity", nullable = false)
    private double quantity;

    @Column(name = "unit", nullable = false, length = 50)
    private String unit;

    @Column(name = "rate", nullable = false)
    private double rate;

    @Column(name = "cgst", nullable = false)
    private double cgst;

    @Column(name = "sgst", nullable = false)
    private double sgst;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "after_tax_total", nullable = false)
    private double afterTaxTotal;

    @ManyToOne
    @JoinColumn(name = "invoice_no", referencedColumnName = "invoice_no", nullable = false)
    private BillReceiverDetails billReceiverDetails;


}
