package service.impl;

import common.RespInfo;
import common.SendEmail;
import dao.BillDao;
import dao.MyOrderDao;
import model.Bill;
import model.HostelInfo;
import model.MyOrder;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.HostelService;
import service.OrderService;
import service.UserService;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by L.H.S on 2017/3/11.
 */

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MyOrderDao orderDao;
    @Autowired
    UserService userService;
    @Autowired
    HostelService hostelService;
    @Autowired
    BillDao billDao;

    public List<MyOrder> getFutureOrder(int userId) {

        return orderDao.getMyFutureOrder(userId);
    }

    public List<MyOrder> getHistoryOrder(int userId) {
        return orderDao.getMyHistoryOrder(userId);
    }

    public List<MyOrder> getCheckin(int userId) {
        return orderDao.getMyCheckin(userId);
    }

    public void insert(int userId, int hosId, String checktime, String leavetime, double pay) {

        User user = (User) userService.getUserInfo(userId).getObject();
        HostelInfo hostel = hostelService.getInfo(hosId);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        String ordertime = df.format(new Date());// new Date()为获取当前系统时间

        MyOrder order = new MyOrder(userId, hosId, hostel.getName(), hostel.getLocation(), "", ordertime,
                checktime, leavetime, pay, user.getPhone(), user.getUsername(), "future", "notchecked");

        int orderId = orderDao.insert(order);
        // 存储到账单
        String timestr[] = ordertime.split(" ");
        String yearm[] = timestr[0].split("-");
        Bill bill = new Bill(userId, timestr[0], pay, hostel.getName(), yearm[0], yearm[1]);
        billDao.insert(bill);

        String email = user.getEmail();
        String info = "恭喜您！<br>您已在 <span style='font-size: 18px; font-weight: 500;'>" + hostel.getName() + "</span> 客栈" +
                "预定成功！<br>您的订单号是：<span style='font-size: 18px; font-weight: 400;'>" + orderId + "</span>" +
                "<br>可在@<a href='http://localhost:8082/html/HomePage.jsp'>HostelWorld</a>的个人页面查看";
        RespInfo respInfo = new RespInfo(true, info, email);

        SendEmail sendEmail = new SendEmail(respInfo);
        sendEmail.start();
    }

    public void cancelOrder(int orderId) {
        orderDao.cancelOrder(orderId);

        MyOrder order = orderDao.getTheOrder(orderId);
        String ordertime[] = order.getOrdertime().split(" ");
        String yearm[] = ordertime[0].split("-");
        Bill bill = new Bill(order.getUserId(), ordertime[0], -order.getPay(), order.getHostelname(), yearm[0], yearm[1]);
        billDao.insert(bill);
    }
}
