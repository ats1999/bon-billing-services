package ai.bon.web_service.billing.repository;

import ai.bon.web_service.billing.model.billpayment.BillPayment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillPaymentRepository extends JpaRepository<BillPayment, String> {
  @Query(
      value =
          """
          SELECT bp.transaction_id, b.user_id, bp.payment_date
          FROM bill_payment bp
          JOIN bill b ON b.id = bp.bill_id
          WHERE is_rewarded = FALSE
            AND transaction_status = 'COMPLETED'
            AND b.paid_on <= b.due_date
          ORDER BY b.user_id, bp.payment_date
          LIMIT :limit OFFSET :offset
          """,
      nativeQuery = true)
  List<Object[]> findPaymentsForReward(@Param("limit") int limit, @Param("offset") int offset);
}
