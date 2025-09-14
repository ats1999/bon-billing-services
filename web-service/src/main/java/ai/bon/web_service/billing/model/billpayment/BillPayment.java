package ai.bon.web_service.billing.model.billpayment;

import ai.bon.web_service.billing.model.bill.Bill;
import jakarta.persistence.*;
import java.util.Date;
import lombok.Data;

@Entity
@Data
public class BillPayment {
  @Id private String transactionId;

  @ManyToOne(optional = false)
  private Bill bill;

  @Column(nullable = false)
  private Date paymentDate;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TransactionStatus transactionStatus;

  // store payment metadata, like gateway response, etc
  private String context;
}
