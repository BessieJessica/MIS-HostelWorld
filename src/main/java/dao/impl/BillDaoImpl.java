package dao.impl;

import dao.BillDao;
import model.Bill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Repository
public class BillDaoImpl implements BillDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List getTimeGroup(int userId) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "select new list(year,month) from Bill bill where bill.userId=" + userId + " group by bill.year,bill.month";
        List list = session.createQuery(hql).list();

        return list;
    }

    public List<Bill> getBills(int userId, String year, String month) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from Bill bill where bill.userId=" + userId + " and bill.year='" + year + "' and bill.month='" + month + "'";
        List<Bill> list = session.createQuery(hql).list();

        return list;
    }

    public void insert(Bill bill) {
        sessionFactory.getCurrentSession().save(bill);
    }
}
