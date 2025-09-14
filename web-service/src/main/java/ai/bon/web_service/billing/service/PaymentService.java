package ai.bon.web_service.billing.service;

import ai.bon.web_service.billing.model.billpayment.BillPayment;
import ai.bon.web_service.billing.model.billpayment.PaymentMethod;

public interface PaymentService {
  BillPayment payBill(int userId, String billId, PaymentMethod paymentMethod);
}
