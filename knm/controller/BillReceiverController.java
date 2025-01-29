package com.knm.controller;

import com.knm.entity.*;
import com.knm.service.BillReceiverService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class BillReceiverController {
    // Methods here


    @Autowired
    private BillReceiverService billReceiverService;

    //private static final Logger logger = (Logger) LoggerFactory.getLogger(BillReceiverController.class);

    @PostMapping("/addBill")
    public ResponseEntity<?> addBill(@RequestBody BillReceiverRequest billReceiverRequest) {
        try {
             BillReceiverDetails savedDetails = billReceiverService.addBill(
                    billReceiverRequest.getBillDetails(),
                    billReceiverRequest.getBillReceiverAmounts()
            );
             Map<String, Object> response = new HashMap<>();
            response.put("message", "Invoice saved successfully.");
            response.put("invoiceNo", savedDetails.getInvoiceNo());
            response.put("Date",savedDetails.getDate());
            //  response.put("data", savedDetails);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {

            System.out.println("Failed to save invoice"+e);

            // Return an error response with a meaningful message
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Failed to save invoice.");
            errorResponse.put("error", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @PostMapping("/receive")
    public ResponseEntity<BillReceiverDetails> receiveBill(@RequestBody BillReceiverDetails billReceiverDetails) {
        try {
            // Call service method to save the billReceiverDetails
            BillReceiverDetails savedBill = billReceiverService.BillReceive(billReceiverDetails);
            return ResponseEntity.ok(savedBill);  // Return the saved bill details in the response
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);  // In case of error, return a bad request response
        }
    }

    @CrossOrigin(origins = "http://127.0.0.1:5501")
    @GetMapping("/getBillReceive")
    public ResponseEntity<?> getBillReceives() {
        try {
            List<BillReceiverDetails> invoices = billReceiverService.getBillReceive();  // Service to fetch data
            return ResponseEntity.ok(invoices);  // Return the list of invoices as JSON
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching invoices");
        }
    }

    @CrossOrigin(origins = "http://127.0.0.1:5501")
        @GetMapping("/get/{invoiceNo}")
        public ResponseEntity<?> getDetailsByInvoiceNo(@PathVariable int invoiceNo) {
            Response response=new Response();
            try {
                List<BillReceiverDetails> details   = billReceiverService.getDetailsByInvoiceNo(invoiceNo);

                return ResponseEntity.ok(details);

            } catch (RuntimeException e) {
                return ResponseEntity.status(404).body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(500).body("An unexpected error occurred.");
            }
        }
    }


