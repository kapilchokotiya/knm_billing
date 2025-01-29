package com.knm.repository;

import com.knm.entity.BillReceiverDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillReceiverDetailsRepository extends JpaRepository<BillReceiverDetails,Integer> {
    //Optional<BillReceiverDetails> findByInvoiceNo(int invoiceNo);

    List<BillReceiverDetails> findAllByInvoiceNo(int invoiceNo);

    //boolean existsByinvoice(int invoiceNo);

    boolean existsByinvoiceNo(int invoiceNo);

    // List<BillReceiverDetails> findAllByInvoiceNo(int invoiceNo);
}
