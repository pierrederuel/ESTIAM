package com.estiam.eventcity.model;

import java.util.ArrayList;

/**
 * Created by ESTIAM on 18/06/2017.
 */

public class EventCityResponse <T> {

  String code;
  String message;
  ArrayList<T> data;
  Criteria criteria;
  Integer limit;
  Integer start;
  Integer end;
  Integer page;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ArrayList<T> getData() {
    return data;
  }

  public void setData(ArrayList<T> data) {
    this.data = data;
  }

  public Criteria getCriteria() {
    return criteria;
  }

  public void setCriteria(Criteria criteria) {
    this.criteria = criteria;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getStart() {
    return start;
  }

  public void setStart(Integer start) {
    this.start = start;
  }

  public Integer getEnd() {
    return end;
  }

  public void setEnd(Integer end) {
    this.end = end;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }
}
