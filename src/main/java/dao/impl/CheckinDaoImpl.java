package dao.impl;

import common.RespInfo;
import dao.CheckinDao;
import model.CheckIn;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Repository
public class CheckinDaoImpl implements CheckinDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void insert(CheckIn checkIn) {

        sessionFactory.getCurrentSession().save(checkIn);
    }

    public CheckIn getCheck(int hosId, int roomId) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from CheckIn c where c.roomId=" + roomId + " and c.hostelId=" + hosId + " and c.state='valid'";
        List<CheckIn> list = session.createQuery(hql).list();

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    // 离店时更新
    public void updateState(int hosId, int roomId) {

        CheckIn checkIn = getCheck(hosId, roomId);
        if (checkIn != null) {
            checkIn.setState("invalid");
        }

        sessionFactory.getCurrentSession().update(checkIn);
    }

    public int getCheckNums(int hosId) {

        String hql = "select count(*) from CheckIn where hostelId=" + hosId;
        int nums = 0;
        List list = sessionFactory.getCurrentSession().createQuery(hql).list();
        if (list.get(0) != null) {
            nums = Integer.parseInt(list.get(0).toString());
        }
        return nums;
    }

    public double getCheckPay(int hosId) {

        String hql = "select sum(pay) from CheckIn where  hostelId=" + hosId + " and state='" + "valid'";
        double pay = 0;
        List list = sessionFactory.getCurrentSession().createQuery(hql).list();
        if (list.get(0) != null) {
            pay = Double.parseDouble(list.get(0).toString());
        }

        return pay/100;
    }

    public List getHostelChecks(int hosId) {

        String hql = "from CheckIn where hostelId=" + hosId;
        List list = sessionFactory.getCurrentSession().createQuery(hql).list();
        return list;
    }
}
