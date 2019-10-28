package com.herokuapp.tabbackend.beans;

public class UserContext {
  private User user;
  private Session session;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }

  public class Session{
    private String userSession;

    public String getUserSession() {
      return userSession;
    }

    public void setUserSession(String userSession) {
      this.userSession = userSession;
    }
  }
}
