package racoonsoft.racoonspring.access;

import racoonsoft.racoonspring.data.structure.User;

import java.util.Date;

public class UserSession
{
    private long created;
    private long duration;

    private Long userId;
    private String sessionId;

    private User anonymous;

    public User getAnonymous() {
        return anonymous;
    }
    public boolean isAnonymous()
    {
        return userId<0;
    }

    public void setAnonymous(User anonymous) {
        this.anonymous = anonymous;
        userId = anonymous.getLongValue("id");
    }

    public boolean expired(Long now)
    {
        return (now-created)>(duration*1000);
    }

    public UserSession(int duration, Long userId, String sessionId)
    {
        this.created = new Date().getTime();
        this.duration = duration;
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
