package dao.impl;

import dao.CheckoutDao;
import model.CheckOut;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Repository
public class CheckoutDaoImpl implements CheckoutDao{

    @Autowired
    private SessionFactory sessionFactory;

    public void insert(CheckOut checkOut) {
        sessionFactory.getCurrentSession().save(checkOut);
    }

    public double getOutPay(int hosId) {

        String hql = "select sum(totalpay) from CheckOut where hostelId=" + hosId;
        double pay = 0;
        List list = sessionFactory.getCurrentSession().createQuery(hql).list();
        if (list.get(0) != null) {
            pay = Double.parseDouble(list.get(0).toString());
        }
        return pay/100;
    }

    public List getHostelCheckout(int hosId) {

        String hql = "from CheckOut where hostelId=" + hosId;
        List list = sessionFactory.getCurrentSession().createQuery(hql).list();
        return list;
    }
}
