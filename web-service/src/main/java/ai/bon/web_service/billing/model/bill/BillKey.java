package ai.bon.web_service.billing.model.bill;

import jakarta.persistence.Embeddable;

@Embeddable
public class BillKey {
  private int userId;
  private String id;
}
