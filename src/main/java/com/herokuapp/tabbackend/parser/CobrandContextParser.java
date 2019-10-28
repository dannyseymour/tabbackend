package com.herokuapp.tabbackend.parser;

import com.google.gson.Gson;
import com.herokuapp.tabbackend.beans.CobrandContext;
import java.io.IOException;

public class CobrandContextParser {
  private String token = this.getClass().getName();
  public CobrandContext parseJSON(String json) throws IOException{
    Gson gson = new Gson();
    return (CobrandContext)gson.fromJson(json, CobrandContext.class)
  }
}
