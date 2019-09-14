package com.sbia.sbiademo.services.servicesImp;

import com.sbia.sbiademo.services.services.ShiroServices;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ShiroServicesImp implements ShiroServices {
    @Autowired
    SessionDAO sessionDAO;

    /**
     * @return 返回当前活跃的会话
     */
    @Override
    public Collection<Session> getActiveSessions() {
        return sessionDAO.getActiveSessions();
    }
}
