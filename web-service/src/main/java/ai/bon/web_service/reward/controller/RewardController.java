package ai.bon.web_service.reward.controller;

import ai.bon.web_service.reward.dto.RewardDTO;
import ai.bon.web_service.reward.model.Reward;
import ai.bon.web_service.reward.model.RewardKey;
import ai.bon.web_service.reward.model.RewardStatus;
import ai.bon.web_service.reward.repository.RewardRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
public class RewardController {
  private final RewardRepository repository;

  RewardController(final RewardRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public List<RewardDTO> getRewardsOfUser(
      @RequestAttribute int userId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Pageable pageRequest = PageRequest.of(page, size);
    Page<Reward> rewardPage = repository.findAllByKeyUserId(userId, pageRequest);
    return rewardPage.map(RewardDTO::from).stream().toList();
  }

  @GetMapping("/{rewardId}")
  public RewardDTO getRewardById(@RequestAttribute int userId, @PathVariable String rewardId) {
    RewardKey rewardKey = RewardKey.builder().userId(userId).id(rewardId).build();
    return repository
        .findById(rewardKey)
        .map(RewardDTO::from)
        .orElseThrow(() -> new RuntimeException("Reward not found"));
  }

  @GetMapping("/unredeemed")
  public List<RewardDTO> getUnredeemedRewardsOfUser(
      @RequestAttribute int userId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Pageable pageRequest = PageRequest.of(page, size);
    Page<Reward> rewardPage =
        repository.findAllByKeyUserIdAndStatus(userId, RewardStatus.ISSUED, pageRequest);
    return rewardPage.map(RewardDTO::from).stream().toList();
  }
}
