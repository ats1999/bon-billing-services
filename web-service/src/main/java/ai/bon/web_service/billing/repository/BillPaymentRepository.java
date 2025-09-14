package ai.bon.web_service.billing.repository;

import ai.bon.web_service.billing.model.billpayment.BillPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillPaymentRepository extends JpaRepository<BillPayment,String> {}
