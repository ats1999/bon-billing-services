package ai.bon.web_service.billing.controller;

import ai.bon.web_service.billing.dto.BillDTO;
import ai.bon.web_service.billing.model.bill.Bill;
import ai.bon.web_service.billing.repository.BillRepository;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bills")
public class BillController {
  private final BillRepository billRepository;

  BillController(final BillRepository billRepository) {
    this.billRepository = billRepository;
  }

  @GetMapping
  public List<BillDTO> getBills(
      @RequestAttribute int userId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Pageable pageRequest = PageRequest.of(page, size);
    Page<Bill> billPage = billRepository.findAllByKeyUserId(userId, pageRequest);
    return billPage.stream().map(BillDTO::from).toList();
  }

  @GetMapping("/unpaid")
  public List<BillDTO> getUnPaidBills(
      @RequestAttribute int userId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Pageable pageRequest = PageRequest.of(page, size);
    Page<Bill> billPage = billRepository.findAllByKeyUserIdAndPaidOnIsNull(userId, pageRequest);
    return billPage.stream().map(BillDTO::from).toList();
  }

  @GetMapping("/overdue")
  public List<BillDTO> getOverdueBills(
      @RequestAttribute int userId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Pageable pageRequest = PageRequest.of(page, size);
    Date today = Date.from(Instant.now());
    Page<Bill> billPage =
        billRepository.findAllByKeyUserIdAndPaidOnIsNullAndDueDateIsBefore(
            userId, today, pageRequest);
    return billPage.stream().map(BillDTO::from).toList();
  }
}
