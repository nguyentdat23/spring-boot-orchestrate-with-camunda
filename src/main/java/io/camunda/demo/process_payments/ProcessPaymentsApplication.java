package io.camunda.demo.process_payments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessPaymentsApplication {

  private static final Logger LOG = LoggerFactory.getLogger(ProcessPaymentsApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ProcessPaymentsApplication.class, args);
  }
}
