package task;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by L.H.S on 2017/3/16.
 */
@Component
public class ScanTask {

    @Autowired
    private SessionFactory sessionFactory;

    @Scheduled(cron = "0 0 2 * * ?")
    public void print() {
        String time = DateFormat.getDateTimeInstance().format(new Date());
        System.out.println("扫描数据库：" + time);

        Session session = sessionFactory.getCurrentSession();
        String hql = "";
        List<User> userList = session.createQuery(hql).list();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (User user : userList) {
            long day = 0;
            try {
                Date today = new Date();
                Date lastSaleDate = df.parse(user.getJointime());
                day = (today.getTime() - lastSaleDate.getTime()) / (24 * 60 * 60 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (day > 365) {
                user.setLevel("暂停状态");
                session.update(user);
            }
        }
    }
}
