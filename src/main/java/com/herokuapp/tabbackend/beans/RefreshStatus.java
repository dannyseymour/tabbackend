package com.herokuapp.tabbackend.beans;

public class RefreshStatus {
  private String providerAccountId;
  private RefreshInfo refreshInfo;

  public String getProviderAccountId() {
    return providerAccountId;
  }

  public void setProviderAccountId(String providerAccountId) {
    this.providerAccountId = providerAccountId;
  }

  public RefreshInfo getRefreshInfo() {
    return refreshInfo;
  }

  public void setRefreshInfo(RefreshInfo refreshInfo) {
    this.refreshInfo = refreshInfo;
  }
}
