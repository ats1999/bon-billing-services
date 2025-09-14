package ai.bon.web_service.billing.service;

public interface RandomUniqueIdGenerator<S> {
  String generate(S slug);
}
