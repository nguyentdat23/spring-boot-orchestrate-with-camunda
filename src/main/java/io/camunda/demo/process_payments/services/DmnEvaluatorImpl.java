package io.camunda.demo.process_payments.services;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.camunda.client.CamundaClient;
import io.camunda.client.api.response.EvaluateDecisionResponse;

@Service // Mark as a service for Spring to manage
public class DmnEvaluatorImpl implements DmnEvaluator {

  private final CamundaClient client;

  private final static Logger LOG = LoggerFactory.getLogger(DmnEvaluatorImpl.class);
  final String defaultAddress = "http://localhost:26500";

  // @Autowired // Constructor injection is preferred
  public DmnEvaluatorImpl(CamundaClient client) {
    this.client = client;
  }

  public Map<String, Object> evaluateDecision() {
    if (client == null) {
      LOG.error("Couldn't evaluate because client is not connected");
      throw new NullPointerException("Couldn't evaluate because client is not connected");
    }

    try {
      Map<String, Object> variables = new HashMap<>();
      variables.put("currency", "USD");
      variables.put("foreignAmount", 19);
      variables.put("localAmount", 500000);
      variables.put("exchangeRate", 25000);

      EvaluateDecisionResponse result = client
          .newEvaluateDecisionCommand()
          .decisionId("check-request-money-amount")
          .variables(variables)
          .send()
          .join();

      Map<String, Object> resp = new HashMap<>();
      resp.put("isValid", result.getDecisionOutput());
      LOG.info("Response from evaluating is: {}", resp);

      return resp;

    } catch (Exception e) {
      LOG.error("Failed to evaluating with msg error: {}", e.getMessage());
      throw new RuntimeException("Error evaluating DMN decision: {}", e);
    }
  }
}
