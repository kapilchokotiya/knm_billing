package com.knm.service;

import com.knm.entity.*;
import com.knm.repository.BillReceiverAmountRepository;
import com.knm.repository.BillReceiverDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BillReceiverService {
    @Autowired
    private BillReceiverAmountRepository billReceiverAmountRepository;

    @Autowired
    private BillReceiverDetailsRepository billReceiverDetailsRepository;
    @Autowired
    private Response response;

    public BillReceiverDetails addBill(BillDetails billDetails, List<BillReceiverAmount> billReceiverAmounts) {

        boolean isExist = billReceiverDetailsRepository.existsByinvoiceNo(billDetails.getInvoiceNo());
        if (isExist) {
            throw new IllegalArgumentException("InvoiceNo already exists");
        }
        BillReceiverDetails billReceiverDetails = new BillReceiverDetails();
        billReceiverDetails.setInvoiceNo(billDetails.getInvoiceNo());
        billReceiverDetails.setName(billDetails.getName());
        billReceiverDetails.setAddress(billDetails.getAddress());
        billReceiverDetails.setDate(billDetails.getDate());
        billReceiverDetails.setStateCode(billDetails.getStateCode());
        billReceiverDetails.setGstNo(billDetails.getGstNo());
        billReceiverDetails.setRemarks(billDetails.getRemarks());
        billReceiverDetails.setCreteadBy("Admin");

        // Save the BillReceiverDetails object
        BillReceiverDetails savedDetails = billReceiverDetailsRepository.save(billReceiverDetails);

        // Map BillReceiverAmount and link to BillReceiverDetails
        List<BillReceiverAmount> receiverAmounts = billReceiverAmounts.stream().map(amount -> {
            BillReceiverAmount receiverAmount = new BillReceiverAmount();
            receiverAmount.setSerialNo(amount.getSerialNo());
            receiverAmount.setDescription(amount.getDescription());
            receiverAmount.setQuantity(amount.getQuantity());
            receiverAmount.setUnit(amount.getUnit());
            receiverAmount.setRate(amount.getRate());
            receiverAmount.setCgst(amount.getCgst());
            receiverAmount.setSgst(amount.getSgst());
            receiverAmount.setAmount(amount.getAmount());
            receiverAmount.setAfterTaxTotal(amount.getAfterTaxTotal());
            receiverAmount.setBillReceiverDetails(savedDetails); // Link to BillReceiverDetails
            return receiverAmount;
        }).toList();

        // Save all BillReceiverAmounts
        billReceiverAmountRepository.saveAll(receiverAmounts);

        // Set the list of amounts in the saved BillReceiverDetails
        savedDetails.setBillReceiverAmounts(receiverAmounts);

        // Return the saved BillReceiverDetails
        return savedDetails;
    }

    public BillReceiverDetails updatedBill() {
        return null;
    }

    public BillReceiverDetails BillReceive(BillReceiverDetails billReceiverDetails) {
        return billReceiverDetailsRepository.save(billReceiverDetails);
    }

    public List<BillReceiverDetails> getBillReceive() {
        return billReceiverDetailsRepository.findAll();
    }

    public List<BillReceiverDetails> getDetailsByInvoiceNo(int invoiceNo) {
        // Fetch details
        List<BillReceiverDetails> detailsList = billReceiverDetailsRepository.findAllByInvoiceNo(invoiceNo);

        if (detailsList == null || detailsList.isEmpty()) {
            throw new RuntimeException("No records found for invoice number: " + invoiceNo);
        }

        return detailsList;
    }

    public BillReceiverDetails updatebybill(BillDetails billDetails) {


        return null;
    }

}


