package com.knm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class BillDetails {


    private int invoiceNo; // Primary key


    private String name;


    private String address;


    private Timestamp date;


    private int stateCode;


    private String gstNo;


    private String remarks;

    private String creteadBy;

    private String updatedBy;

    private Timestamp UpdatedDate;

}
