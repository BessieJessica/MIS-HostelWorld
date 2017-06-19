package dao.impl;

import dao.HostelApplyDao;
import model.HostelApply;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Repository
public class HostelApplyDaoImpl implements HostelApplyDao{

    @Autowired
    private SessionFactory sessionFactory;

    public void insert(HostelApply apply) {

        sessionFactory.getCurrentSession().save(apply);
    }

    public HostelApply updateState(int applyId, String state, int approverId) {

        Session session = sessionFactory.getCurrentSession();
        HostelApply apply = (HostelApply) session.load(HostelApply.class, applyId);
        apply.setApprovalstate(state);
        apply.setApproverId(approverId);

        session.update(apply);
        return apply;
    }

    public List<HostelApply> getApply() {

        String hql = "from HostelApply ha where ha.approvalstate='unchecked'";
        List<HostelApply> list = sessionFactory.getCurrentSession().createQuery(hql).list();

        return list;
    }

    public List<HostelApply> getHistory() {

        String hql = "from HostelApply ha where ha.approvalstate<>'unchecked'";
        List<HostelApply> list = sessionFactory.getCurrentSession().createQuery(hql).list();

        return list;
    }

    public HostelApply getTheApply(int applyId) {

        HostelApply ha = (HostelApply) sessionFactory.getCurrentSession().get(HostelApply.class, applyId);
        return ha;
    }
}
