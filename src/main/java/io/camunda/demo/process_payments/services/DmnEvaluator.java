package io.camunda.demo.process_payments.services;

import java.util.Map;

public interface DmnEvaluator {
  public Map<String, Object> evaluateDecision();
}
