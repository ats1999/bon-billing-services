package ai.bon.web_service.billing.repository;

import ai.bon.web_service.billing.model.bill.Bill;
import ai.bon.web_service.billing.model.bill.BillKey;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, BillKey> {
  Page<Bill> findAllByKeyUserId(int userId, Pageable pageable);

  Page<Bill> findAllByKeyUserIdAndPaidOnIsNull(int userId, Pageable pageable);

  Page<Bill> findAllByKeyUserIdAndPaidOnIsNullAndDueDateIsBefore(
      int userId, Date date, Pageable pageable);
}
