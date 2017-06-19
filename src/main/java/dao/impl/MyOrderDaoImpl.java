package dao.impl;

import dao.MyOrderDao;
import model.MyOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Repository
public class MyOrderDaoImpl implements MyOrderDao {

    @Autowired
    private SessionFactory sessionFactory;


    public List<MyOrder> getMyFutureOrder(int userId) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from MyOrder mo where mo.userId=" + userId + " and mo.orderstate='future'";
        List<MyOrder> list = session.createQuery(hql).list();

        return list;
    }

    public List<MyOrder> getMyHistoryOrder(int userId) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from MyOrder mo where mo.userId=" + userId + " and mo.orderstate='history'";
        List<MyOrder> list = session.createQuery(hql).list();

        return list;
    }

    public List<MyOrder> getMyCheckin(int userId) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from MyOrder mo where mo.userId=" + userId + " and mo.checkstate='checked'";
        List<MyOrder> list = session.createQuery(hql).list();

        return list;
    }

    public MyOrder getTheOrder(int orderId) {

        MyOrder order = (MyOrder) sessionFactory.getCurrentSession().get(MyOrder.class, orderId);
        return order;
    }

    public void updateOrder(int orderId, int roomId) {

        Session session = sessionFactory.getCurrentSession();
        MyOrder order = (MyOrder) session.load(MyOrder.class, orderId);
        order.setCheckstate("checked");
        order.setOrderstate("history");
        order.setRoom(roomId + "");

        session.update(order);
    }

    public int insert(MyOrder order) {

        sessionFactory.getCurrentSession().save(order);
        return order.getId();
    }

    public void cancelOrder(int orderId) {

        Session session = sessionFactory.getCurrentSession();
        MyOrder order = (MyOrder) session.load(MyOrder.class, orderId);
        order.setOrderstate("history");
        order.setCheckstate("cancel");
        session.update(order);
    }

    public double getOrderPay(int hosId) {

        String hql = "select sum(pay) from MyOrder where hostelId=" + hosId;
        double total = 0;
        List list = sessionFactory.getCurrentSession().createQuery(hql).list();
        if (list.get(0) != null) {
            total = Double.parseDouble(list.get(0).toString());
        }

        return total/100;
    }

    public int getOrderNum(int hosId) {

        String hql = "select count(*) from MyOrder where hostelId=" + hosId;

        int total = 0;
        List list = sessionFactory.getCurrentSession().createQuery(hql).list();
        if (list.get(0) != null) {
            total = Integer.parseInt(list.get(0).toString());
        }

        return total;
    }

    public List getHostelOrder(int hosId) {

        String hql = "from MyOrder where hostelId=" + hosId;
        List list = sessionFactory.getCurrentSession().createQuery(hql).list();
        return list;
    }
}
