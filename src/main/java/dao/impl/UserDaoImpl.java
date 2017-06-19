package dao.impl;

import dao.UserDao;
import common.RespInfo;
import model.Manager;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public int insert(User user) {

        sessionFactory.getCurrentSession().save(user);
        int userId = user.getId();

        return userId;
    }

    public void setPwd(int userId, String pwd) {

        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, userId);
        user.setPassword(pwd);
        session.update(user);
    }

    public void setBalance(int userId, double balance) {

        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, userId);
        user.setBalance(balance);
        user.setPoint((int)balance);
        session.update(user);
    }

    public RespInfo getPwd(int userId) {

        User user = (User) sessionFactory.getCurrentSession().get(User.class, userId);
        if (user != null) {
            return new RespInfo(true, user.getPassword(), "");
        } else {
            return new RespInfo(false, "无效的会员卡号", "");
        }
    }

    public User getUserInfo(int userId) {

        User user = (User) sessionFactory.getCurrentSession().get(User.class, userId);
        return user;
    }

    public void updateUserInfo(User u) {

        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, u.getId());
        user.setUsername(u.getUsername());
        user.setSex(u.getSex());
        user.setBirth(u.getBirth());
        user.setPhone(u.getPhone());
        user.setEmail(u.getEmail());

        session.update(user);
    }

    public void updateBankcard(int userId, String bankcard) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, userId);
        user.setBankcard(bankcard);

        session.update(user);
    }

    public RespInfo getManagerPwd(int managerId) {

        Manager manager = (Manager) sessionFactory.getCurrentSession().get(Manager.class, managerId);
        if (manager != null) {
            return new RespInfo(true, manager.getPassword(), manager.getName());
        } else {
            return new RespInfo(false, "无效的工作编号", "");
        }
    }

    public void charge(int userId, double charge) {

        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, userId);
        double balance = user.getBalance();
        user.setBalance(balance + charge);
        user.setPoint(user.getPoint() + (int) charge);

        if (user.getPoint() < 5000) {
            user.setLevel("黄金会员");
        } else if (user.getPoint() < 20000) {
            user.setLevel("铂金会员");
        } else if (user.getPoint() < 100000) {
            user.setLevel("钻石会员");
        } else {
            user.setLevel("黑卡贵宾");
        }
        session.update(user);
    }
}

