package ai.bon.web_service.reward.service;

import ai.bon.web_service.billing.model.billpayment.BillPayment;
import ai.bon.web_service.billing.repository.BillPaymentRepository;
import ai.bon.web_service.reward.dto.RewardPaymentDTO;
import ai.bon.web_service.reward.model.Reward;
import ai.bon.web_service.reward.repository.RewardRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RewardServiceImpl implements RewardService {
  private final BillPaymentRepository billPaymentRepository;
  private final RewardRepository rewardRepository;

  RewardServiceImpl(
      final BillPaymentRepository billPaymentRepository, final RewardRepository rewardRepository) {
    this.billPaymentRepository = billPaymentRepository;
    this.rewardRepository = rewardRepository;
  }

  @Override
  public List<RewardPaymentDTO> getRewardPayments(int page, int size) {
    List<Object[]> rewardPaymentsData =
        billPaymentRepository.findPaymentsForReward(size, page * size);

    return rewardPaymentsData.stream()
        .map(
            data ->
                RewardPaymentDTO.builder()
                    .transactionId((String) data[0])
                    .userId((Integer) data[1])
                    .paymentDate((java.util.Date) data[2])
                    .build())
        .toList();
  }

  @Override
  public void createReward(List<RewardPaymentDTO> rewardPayments, List<Reward> rewards) {
    for (var rewardPayment : rewardPayments) {
      BillPayment billPayment =
          billPaymentRepository.findById(rewardPayment.getTransactionId()).orElseThrow();
      billPayment.setRewarded(true);
      // .save(..) is deferred until transaction commits, so its actual batch insert
      billPaymentRepository.save(billPayment);
    }

    rewardRepository.saveAll(rewards);
  }
}
