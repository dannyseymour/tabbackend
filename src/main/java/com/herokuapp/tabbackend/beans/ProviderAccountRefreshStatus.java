package com.herokuapp.tabbackend.beans;

public class ProviderAccountRefreshStatus {
  private ProviderAccount[] providerAccounts;

  public ProviderAccount[] getProviderAccounts{
    return providerAccounts;
  }

  public void setProviderAccounts(ProviderAccount[] providerAccounts) {
    this.providerAccounts = providerAccounts;
  }
  public int getProviderAccountLength(){
    return providerAccounts.length;
  }
}
