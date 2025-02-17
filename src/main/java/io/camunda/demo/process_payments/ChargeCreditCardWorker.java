package io.camunda.demo.process_payments;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.camunda.client.CamundaClient;
import io.camunda.client.api.response.EvaluateDecisionResponse;
import io.camunda.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.client.impl.oauth.OAuthCredentialsProviderBuilder;

@Component
public class ChargeCreditCardWorker {
  private final static Logger LOG = LoggerFactory.getLogger(ChargeCreditCardWorker.class);

  @JobWorker(type = "charge-credit-card")
  public Map<String, Object> chargeCreditCard(@Variable(name = "totalWithTax") Double totalWithTax) {
  private static final String zeebeGrpc = "[Zeebe Address e.g. f887f1a6-7c2b-48ce-809a-e11e5a6ba31a.dsm-1.zeebe.camunda.io:443]";
  private static final String zeebeRest = "[Zeebe Address e.g. https://dsm-1.zeebe.camunda.io/f887f1a6-7c2b-48ce-809a-e11e5a6ba31a]";
  private static final String audience = "[Zeebe Token Audience, e.g., zeebe.camunda.io]";
  private static final String clientId = "[Client ID, e.g., FmT7K8gVv_FcwiUhc8U-fAJ9wph0Kn~P]";
  private static final String clientSecret = "[Client Secret]";
  private static final String oAuthAPI = "[OAuth API, e.g., https://login.cloud.camunda.io/oauth/token] ";

  @Autowired
  private CamundaClient client;

  public void chargeCreditCard(String[] args) {
    OAuthCredentialsProvider credentialsProvider = new OAuthCredentialsProviderBuilder()
        .authorizationServerUrl(oAuthAPI)
        .audience(audience)
        .clientId(clientId)
        .clientSecret(clientSecret)
        .build();

    System.out.println("Evaluating decision");
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("currency", "VND");
    variables.put("exchangeRate", 25000);
    variables.put("currentPaymentAmount", 2000);
    variables.put("currentPaymentAmountVND", 50000000);

    final EvaluateDecisionResponse decisionEvaluation = client
        .newEvaluateDecisionCommand()
        .decisionId("check_payment_amount")
        .variables("{\"lightsaberColor\": \"blue\"}")
        .send()
        .join();

    LOG.info("response info get decision: {}", decisionEvaluation);
  }
}
