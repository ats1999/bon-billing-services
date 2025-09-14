package ai.bon.web_service.reward.repository;

import ai.bon.web_service.reward.model.Reward;
import ai.bon.web_service.reward.model.RewardKey;
import ai.bon.web_service.reward.model.RewardStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends JpaRepository<Reward, RewardKey> {
  Page<Reward> findAllByKeyUserId(int userId, Pageable pageable);

  Page<Reward> findAllByKeyUserIdAndStatus(int userId, RewardStatus status, Pageable pageable);
}
