package com.knm.repository;

import com.knm.entity.BillReceiverAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillReceiverAmountRepository extends JpaRepository<BillReceiverAmount,Integer> {
}
