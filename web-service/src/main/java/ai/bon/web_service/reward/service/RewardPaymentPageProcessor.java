package ai.bon.web_service.reward.service;

import ai.bon.web_service.reward.dto.RewardPaymentDTO;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RewardPaymentPageProcessor {
  public static final int REWARD_THRESHOLD = 3;
  @Autowired private RewardService rewardService;
  @Autowired private RewardGenerationService rewardGenerationService;

  @Transactional
  public int process(int page, int size) {
    List<RewardPaymentDTO> rewardPayments = rewardService.getRewardPayments(page, size);

    Map<Integer, List<RewardPaymentDTO>> userToRewardPayments =
        rewardPayments.stream().collect(Collectors.groupingBy(RewardPaymentDTO::getUserId));

    for (var entry : userToRewardPayments.entrySet()) {
      var rewardPaymentsOfUser = entry.getValue();
      int totalRewards = rewardPaymentsOfUser.size() / REWARD_THRESHOLD;
      if (totalRewards == 0) continue;

      var selectedPaymentsForReward =
          rewardPaymentsOfUser.subList(0, totalRewards * REWARD_THRESHOLD);
      var rewards = rewardGenerationService.generateRewards(selectedPaymentsForReward);
      rewardService.createReward(selectedPaymentsForReward, rewards);
    }

    return rewardPayments.size();
  }
}
