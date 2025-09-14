package ai.bon.web_service.reward.cron;

import ai.bon.web_service.reward.service.RewardPaymentPageProcessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RewardScheduler {
  private final RewardPaymentPageProcessor rewardPaymentPageProcessor;

  RewardScheduler(RewardPaymentPageProcessor rewardPaymentPageProcessor) {
    this.rewardPaymentPageProcessor = rewardPaymentPageProcessor;
  }

  // Runs 10 seconds after the previous execution completes
  @Scheduled(fixedDelay = 10 * 1000)
  public void processRewards() {
    int page = 0, size = 50;
    int processed;
    do {
      processed = rewardPaymentPageProcessor.process(page, size);
      System.out.println("processed: " + processed);
      page++;
    } while (processed == size);
  }
}
