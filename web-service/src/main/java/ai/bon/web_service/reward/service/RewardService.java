package ai.bon.web_service.reward.service;

import ai.bon.web_service.reward.dto.RewardPaymentDTO;
import ai.bon.web_service.reward.model.Reward;
import java.util.List;

public interface RewardService {
  List<RewardPaymentDTO> getRewardPayments(int page, int size);

  void createReward(List<RewardPaymentDTO> rewardPayments, List<Reward> rewards);
}
