package com.wandoofinance.qahomework.repository;


import com.wandoofinance.qahomework.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

   List<Payment> findByUserId(Long id);

}
