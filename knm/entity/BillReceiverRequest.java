package com.knm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillReceiverRequest {

    private BillDetails billDetails;
    private List<BillReceiverAmount> billReceiverAmounts;

    // Getters and Setters
    public BillDetails getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(BillDetails billDetails) {
        this.billDetails = billDetails;
    }

    public List<BillReceiverAmount> getBillReceiverAmounts() {
        return billReceiverAmounts;
    }

    public void setBillReceiverAmounts(List<BillReceiverAmount> billReceiverAmounts) {
        this.billReceiverAmounts = billReceiverAmounts;
    }
}
