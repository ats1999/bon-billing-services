package ai.bon.web_service.billing.model.bill;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import java.util.Date;
import lombok.Data;

@Entity
@Data
public class Bill {
  @EmbeddedId private BillKey key;
  private double amount;
  private Date createdAt;
  private Date dueDate;
  private Date paidOn;

  // e.g, type=EGIFT, bill name = WiFi bill, breakup = [{serverUses:100},{discount:20},...]
  private String context;
}
