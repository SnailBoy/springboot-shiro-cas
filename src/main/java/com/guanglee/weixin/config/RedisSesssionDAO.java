package com.guanglee.weixin.config;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisSesssionDAO extends AbstractSessionDAO {

	private static Logger logger = LoggerFactory.getLogger(RedisSesssionDAO.class);
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private final String keyprefix = "guanglee_shiro_session";

	@Override
	public void update(Session session) throws UnknownSessionException {
		// TODO Auto-generated method stub
		this.saveSession(session);
	}

	@Override
	public void delete(Session session) {
		// TODO Auto-generated method stub
		if (isNullSession(session)) {
			return;
		}
		redisTemplate.delete(getSessionKey(session.getId()));
	}

	// 用来统计当前活动的session
	@Override
	public Collection<Session> getActiveSessions() {
		// TODO Auto-generated method stub
		Set<Session> sessions = new HashSet<>();
		Set<String> sessionIds = redisTemplate.keys(this.keyprefix + "*");
		if (sessionIds != null && sessionIds.size() > 0) {
			for (String sessionId : sessionIds) {
				sessions.add((Session) redisTemplate.opsForValue().get(getSessionKey(sessionId)));
			}
		}
		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		// TODO Auto-generated method stub
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		// TODO Auto-generated method stub
		if (sessionId == null) {
			logger.error("session id is null");
			return null;
		}
		return (Session) redisTemplate.opsForValue().get(getSessionKey(sessionId));
	}

	private String getSessionKey(Serializable sessionId) {
		return this.keyprefix + sessionId;
	}

	private void saveSession(Session session) {
		if (isNullSession(session)) {
			return;
		}
		session.setTimeout(redisTemplate.getExpire("max") * 1000);
		redisTemplate.opsForValue().set(getSessionKey(session.getId()), session, redisTemplate.getExpire("max"));
	}

	private boolean isNullSession(Session session) {
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
			return true;
		}
		return false;
	}

}
