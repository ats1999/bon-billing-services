package ai.bon.web_service.billing.dto;

import ai.bon.web_service.billing.model.billpayment.PaymentMethod;
import ai.bon.web_service.billing.model.billpayment.TransactionStatus;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillPaymentDTO {
  private String transactionId;
  private BillDTO bill;
  private Date paymentDate;
  private PaymentMethod paymentMethod;
  private TransactionStatus transactionStatus;
  // store payment metadata, like gateway response, etc
  private String context;

  public static BillPaymentDTO from(
      ai.bon.web_service.billing.model.billpayment.BillPayment billPayment) {
    return BillPaymentDTO.builder()
        .transactionId(billPayment.getTransactionId())
        .bill(BillDTO.from(billPayment.getBill()))
        .paymentDate(billPayment.getPaymentDate())
        .paymentMethod(billPayment.getPaymentMethod())
        .transactionStatus(billPayment.getTransactionStatus())
        .context(billPayment.getContext())
        .build();
  }
}
