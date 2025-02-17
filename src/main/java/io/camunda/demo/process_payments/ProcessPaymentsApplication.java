package io.camunda.demo.process_payments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.demo.process_payments.services.DmnEvaluator;

@SpringBootApplication
public class ProcessPaymentsApplication implements CommandLineRunner {

  private final DmnEvaluator dmnEvaluator;

  @Autowired
  public ProcessPaymentsApplication(DmnEvaluator dmnEvaluator) {
    this.dmnEvaluator = dmnEvaluator;
  }

  public static void main(String[] args) {
    SpringApplication.run(ProcessPaymentsApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    dmnEvaluator.evaluateDecision();
    // LOG.info("started a process instance: {}", event.getProcessInstanceKey());
  }
}
