package com.herokuapp.tabbackend.beans;

public class RefreshInfo {
  private String refreshStatus;
  private String statusCode;
  private String statusMessage;
  private String lastRefreshed;
  private String lastRefreshedAttempt;
  private String nextRefreshScheduled;
  private String additionalStatus;

  public String getRefreshStatus() {
    return refreshStatus;
  }

  public void setRefreshStatus(String refreshStatus) {
    this.refreshStatus = refreshStatus;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  public String getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  public String getLastRefreshed() {
    return lastRefreshed;
  }

  public void setLastRefreshed(String lastRefreshed) {
    this.lastRefreshed = lastRefreshed;
  }

  public String getLastRefreshedAttempt() {
    return lastRefreshedAttempt;
  }

  public void setLastRefreshedAttempt(String lastRefreshedAttempt) {
    this.lastRefreshedAttempt = lastRefreshedAttempt;
  }

  public String getNextRefreshScheduled() {
    return nextRefreshScheduled;
  }

  public void setNextRefreshScheduled(String nextRefreshScheduled) {
    this.nextRefreshScheduled = nextRefreshScheduled;
  }

  public String getAdditionalStatus() {
    return additionalStatus;
  }

  public void setAdditionalStatus(String additionalStatus) {
    this.additionalStatus = additionalStatus;
  }
}
