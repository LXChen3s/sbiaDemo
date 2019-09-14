package com.sbia.sbiademo.services.services;

import org.apache.shiro.session.Session;

import java.util.Collection;

public interface ShiroServices {
    Collection<Session> getActiveSessions();
}
