package ai.bon.web_service.reward.service;

import ai.bon.web_service.reward.dto.RewardPaymentDTO;
import ai.bon.web_service.reward.model.Reward;

import java.util.List;

public interface RewardGenerationService {
    List<Reward> generateRewards(List<RewardPaymentDTO> selectedRewardPayments);
}
