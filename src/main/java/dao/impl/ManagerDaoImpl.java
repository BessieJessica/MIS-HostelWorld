package dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Repository
public class ManagerDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;


}
