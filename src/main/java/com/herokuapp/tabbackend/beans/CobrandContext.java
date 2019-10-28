package com.herokuapp.tabbackend.beans;

public class CobrandContext {
  private String cobrandId;
  private String applicationId;
  private Session session;

  public class Session{
    private String cobSession;

    public String getCobSession() {
      return cobSession;
    }

    public void setCobSession(String cobSession) {
      this.cobSession = cobSession;
    }
  }
  @Override
  public String toString(){
    StringBuilder stringBuild= new StringBuilder(this.cobrandId+this.applicationId);
    return new String(stringBuild);
  }

  public String getCobrandId() {
    return cobrandId;
  }

  public void setCobrandId(String cobrandId) {
    this.cobrandId = cobrandId;
  }

  public String getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(String applicationId) {
    this.applicationId = applicationId;
  }

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }
}
