package dao.impl;

import common.RespInfo;
import dao.HostelRegisterDao;
import model.HostelInfo;
import model.HostelRegister;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Repository
public class HostelRegisterDaoImpl implements HostelRegisterDao{

    @Autowired
    private SessionFactory sessionFactory;


    public void insert(HostelRegister register) {

        sessionFactory.getCurrentSession().save(register);
    }

    public List<HostelRegister> checkCode(String checkCode) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from HostelRegister hr where hr.checkcode='" + checkCode + "'";
        List<HostelRegister> list = session.createQuery(hql).list();

        return list;
    }

    // 更新register的注册状态
    public void updateState(int regId) {

        Session session = sessionFactory.getCurrentSession();
        HostelRegister register = (HostelRegister) session.load(HostelRegister.class, regId);
        register.setState("reg");
        session.update(register);
    }

    public void insertInfo(HostelInfo info) {

        sessionFactory.getCurrentSession().save(info);
    }
}
