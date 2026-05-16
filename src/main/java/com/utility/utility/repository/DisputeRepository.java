package com.utility.utility.repository;

import java.util.List;
import com.utility.utility.model.Dispute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisputeRepository extends JpaRepository<Dispute, Long> {
    List<Dispute> findByBillId(Long billId);
}
