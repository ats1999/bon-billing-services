package ai.bon.web_service.billing.controller;

import ai.bon.web_service.billing.dto.BillPaymentDTO;
import ai.bon.web_service.billing.model.bill.Bill;
import ai.bon.web_service.billing.model.bill.BillKey;
import ai.bon.web_service.billing.model.billpayment.BillPayment;
import ai.bon.web_service.billing.model.billpayment.PaymentMethod;
import ai.bon.web_service.billing.repository.BillRepository;
import ai.bon.web_service.billing.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/payments")
public class PaymentController {
  private final PaymentService paymentService;
  private final BillRepository billRepository;

  PaymentController(final PaymentService paymentService, final BillRepository billRepository) {
    this.paymentService = paymentService;
    this.billRepository = billRepository;
  }

  @PostMapping("/bills/{billId}/pay")
  public BillPaymentDTO payBill(@RequestAttribute int userId, @PathVariable String billId) {
    BillKey billKey = BillKey.builder().id(billId).userId(userId).build();
    Bill bill =
        billRepository
            .findById(billKey)
            .orElseThrow(
                () ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Bill is not found with id: " + billId));

    if (bill.getPaidOn() != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bill is already paid");
    }

    // NOTE: PaymentMethod.NET_BANKING is hardcoded for simplicity. In real-world scenarios, it
    // should be passed in the request body
    BillPayment billPayment = paymentService.payBill(userId, billId, PaymentMethod.NET_BANKING);
    return BillPaymentDTO.from(billPayment);
  }
}
