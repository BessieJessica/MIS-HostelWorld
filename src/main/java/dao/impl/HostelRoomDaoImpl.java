package dao.impl;

import dao.HostelRoomDao;
import model.HostelRoom;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Repository
public class HostelRoomDaoImpl implements HostelRoomDao {

    @Autowired
    private SessionFactory sessionFactory;

    public HostelRoom getRoom(int hosId, int roomId) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from HostelRoom room where room.hostelId=" + hosId + " and room.roomId=" + roomId;
        List<HostelRoom> list = session.createQuery(hql).list();
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void updateState(int hosId, int roomId, String state) {

        HostelRoom room = getRoom(hosId, roomId);
        room.setIsempty(state);
        sessionFactory.getCurrentSession().update(room);
    }

    public void insertRoom(int hosId, int roomId) {

        HostelRoom room = new HostelRoom(roomId, hosId, "empty");
        sessionFactory.getCurrentSession().save(room);
    }
}
