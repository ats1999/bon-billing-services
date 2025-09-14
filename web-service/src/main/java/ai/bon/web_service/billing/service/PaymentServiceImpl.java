package ai.bon.web_service.billing.service;

import ai.bon.web_service.billing.model.bill.Bill;
import ai.bon.web_service.billing.model.bill.BillKey;
import ai.bon.web_service.billing.model.billpayment.BillPayment;
import ai.bon.web_service.billing.model.billpayment.PaymentMethod;
import ai.bon.web_service.billing.model.billpayment.TransactionStatus;
import ai.bon.web_service.billing.repository.BillPaymentRepository;
import ai.bon.web_service.billing.repository.BillRepository;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {
  private final EntityManager entityManager;
  private final BillRepository billRepository;
  private final BillPaymentRepository billPaymentRepository;
  private final RandomUniqueIdGenerator<Integer> randomUniqueIdGenerator;

  PaymentServiceImpl(
      final EntityManager entityManager,
      final BillRepository billRepository,
      final BillPaymentRepository billPaymentRepository,
      final RandomUniqueIdGenerator<Integer> randomUniqueIdGenerator) {
    this.entityManager = entityManager;
    this.billRepository = billRepository;
    this.billPaymentRepository = billPaymentRepository;
    this.randomUniqueIdGenerator = randomUniqueIdGenerator;
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  @Override
  public BillPayment payBill(int userId, String billId, PaymentMethod paymentMethod) {
    // NOTE: In real-world scenarios, payment processing logic with a payment gateway should be here
    // For simplicity, we assume the payment is always successful
    BillKey billKey = BillKey.builder().id(billId).userId(userId).build();
    Bill bill = billRepository.findById(billKey).orElseThrow();
    Date today = Date.from(Instant.now());

    bill.setPaidOn(today);
    billRepository.save(bill);

    BillPayment billPayment = new BillPayment();
    billPayment.setBill(bill);
    billPayment.setPaymentDate(today);
    billPayment.setPaymentMethod(paymentMethod);
    billPayment.setTransactionId(randomUniqueIdGenerator.generate(userId));
    billPayment.setTransactionStatus(TransactionStatus.COMPLETED);
    billPaymentRepository.save(billPayment);
    bill.setPaidOn(today);

    return billPayment;
  }
}
