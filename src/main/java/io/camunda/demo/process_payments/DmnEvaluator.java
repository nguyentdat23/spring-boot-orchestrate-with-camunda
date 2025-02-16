package io.camunda.demo.process_payments;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class DmnEvaluator {

  private DmnEngine dmnEngine;

  public DmnEvaluator() {
    // Initialize the Decision Engine
    dmnEngine = DmnEngineConfiguration.createDefaultDmnEngineConfiguration()
        .buildEngine();
  }

  public Map<String, Object> evaluateDecision(String dmloadModelPath, String inputVariableName, Object inputValue) {
    try {
      InputStream dmnInputStream = this.getClass().getClassLoader().getResourceAsStream(dmloadModelPath);

      // prepare variables for decision evaluation
      VariableMap variables = Variables.putValue("inputValue", inputValue);

      Map<String, Object> result = new HashMap<>();

      return result;

    } catch (Exception e) {
      throw new RuntimeException("Error evaluating DMN decision", e);
    }
  }
}
