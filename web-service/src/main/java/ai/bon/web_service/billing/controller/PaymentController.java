package ai.bon.web_service.billing.controller;

import ai.bon.web_service.billing.dto.BillPaymentDTO;
import ai.bon.web_service.billing.model.billpayment.BillPayment;
import ai.bon.web_service.billing.model.billpayment.PaymentMethod;
import ai.bon.web_service.billing.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
  private final PaymentService paymentService;

  PaymentController(final PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping("/bills/{billId}/pay")
  public BillPaymentDTO payBill(@RequestAttribute int userId, @PathVariable String billId) {
    // NOTE: PaymentMethod.NET_BANKING is hardcoded for simplicity. In real-world scenarios, it
    // should be passed in the request body
    BillPayment billPayment = paymentService.payBill(userId, billId, PaymentMethod.NET_BANKING);
    return BillPaymentDTO.from(billPayment);
  }
}
