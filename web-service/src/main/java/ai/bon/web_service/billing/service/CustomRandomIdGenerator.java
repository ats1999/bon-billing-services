package ai.bon.web_service.billing.service;

import java.security.SecureRandom;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class CustomRandomIdGenerator implements RandomUniqueIdGenerator<Integer> {
  private static final SecureRandom random = new SecureRandom();
  private static final String BASE32_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";

  @Override
  public String generate(Integer slug) {
    // 5 bytes timestamp (last 40 bits)
    long timestamp = Instant.now().toEpochMilli() & 0xFFFFFFFFFFL;

    // 3 bytes userId + random (24 bits)
    int userRandom = (int) ((slug & 0xFFFF) ^ random.nextInt(1 << 16));

    // Combine 40 bits + 24 bits = 64 bits
    long combined = (timestamp << 24) | (userRandom & 0xFFFFFF);

    // Encode 64-bit number to Base32 (~13 chars)
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 13; i++) {
      int index = (int) ((combined >> (60 - 5 * i)) & 0x1F); // 5 bits per char
      sb.append(BASE32_CHARS.charAt(index));
    }

    return sb.toString();
  }
}
