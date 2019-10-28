package com.herokuapp.tabbackend.parser;

import com.google.gson.Gson;
import com.herokuapp.tabbackend.beans.AccessToken;
import java.io.IOException;

public class AccessTokenParser {
  //string?
  public AccessToken parseJSON(String json) throws IOException {
    Gson gson = new Gson();
    return (AccessToken)gson.fromJson(json, AccessToken.class);
  }
}
