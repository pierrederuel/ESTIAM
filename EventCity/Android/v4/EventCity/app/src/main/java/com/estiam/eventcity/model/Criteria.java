package com.estiam.eventcity.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ESTIAM on 18/06/2017.
 */

public class Criteria {

  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }
}
