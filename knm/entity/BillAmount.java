package com.knm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class BillAmount {
    private int id;

    private int serialNo;

    private String description;

    private double quantity;

    private String unit;

    private double rate;

    private double cgst;

    private double sgst;

    private double amount;

    private double afterTaxTotal;


}
