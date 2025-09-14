package ai.bon.web_service.reward.service;

import ai.bon.web_service.billing.service.RandomUniqueIdGenerator;
import ai.bon.web_service.reward.dto.RewardPaymentDTO;
import ai.bon.web_service.reward.model.Reward;
import ai.bon.web_service.reward.model.RewardKey;
import ai.bon.web_service.reward.model.RewardStatus;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RewardGenerationServiceImpl implements RewardGenerationService {
  private final RandomUniqueIdGenerator<Integer> idGenerator;

  RewardGenerationServiceImpl(RandomUniqueIdGenerator<Integer> idGenerator) {
    this.idGenerator = idGenerator;
  }

  @Override
  public List<Reward> generateRewards(List<RewardPaymentDTO> selectedRewardPayments) {
    // NOTE: This is a placeholder implementation. Replace with actual reward generation logic.
    // We can decide different reward for different type of user
    // We can generate different reward for different amounts of payment

    int totalRewards = selectedRewardPayments.size() / RewardPaymentPageProcessor.REWARD_THRESHOLD;
    List<Reward> rewards = new ArrayList<>();
    for (int i = 0; i < totalRewards; i++) {
      var rewardKey =
          RewardKey.builder()
              .id(idGenerator.generate(i))
              .userId(selectedRewardPayments.get(0).getUserId())
              .build();
      var reward = new Reward();
      reward.setKey(rewardKey);
      reward.setIssuedAt(Date.from(new Date().toInstant()));
      reward.setStatus(RewardStatus.ISSUED);
      reward.setValue("Free Coffee Voucher");
      reward.setDescription("Congratulations! You've earned a Free Coffee Voucher.");
      reward.setExpiryDate(Date.from(Instant.now())); // NOTE: better expiry support is required

      rewards.add(reward);
    }

    return rewards;
  }
}
