package ai.bon.web_service.billing.dto;

import ai.bon.web_service.billing.model.bill.Bill;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillDTO {
  private String id;
  private int userId;
  private double amount;
  private Date createdAt;
  private Date dueDate;
  private Date paidOn;
  private String context;

  public static BillDTO from(Bill bill) {
    return BillDTO.builder()
        .id(bill.getKey().getId())
        .userId(bill.getKey().getUserId())
        .amount(bill.getAmount())
        .createdAt(bill.getCreatedAt())
        .dueDate(bill.getDueDate())
        .paidOn(bill.getPaidOn())
        .context(bill.getContext())
        .build();
  }
}
