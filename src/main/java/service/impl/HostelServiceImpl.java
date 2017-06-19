package service.impl;

import common.RespInfo;
import common.TotalFinance;
import dao.*;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.HostelService;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by L.H.S on 2017/3/12.
 */

@Service("hostelService")
@Transactional
public class HostelServiceImpl implements HostelService {

    @Autowired
    private HostelInfoDao hostelInfoDao;
    @Autowired
    private HostelRoomDao roomDao;
    @Autowired
    private CheckinDao checkinDao;
    @Autowired
    private CheckoutDao checkoutDao;
    @Autowired
    private MyOrderDao orderDao;
    @Autowired
    private HostelApplyDao applyDao;

    public RespInfo hostelLogin(int hostelId, String pwd) {

        RespInfo respInfo = hostelInfoDao.getPwd(hostelId);

        if (respInfo.getStatus()) {
            if (respInfo.getInfo().equals(pwd)) {
                return new RespInfo(true, "", "");
            } else {
                return new RespInfo(false, "密码错误", "");
            }
        } else {
            return new RespInfo(false, "无效的客栈编号", "");
        }
    }

    public HostelInfo getInfo(int hosId) {
        return hostelInfoDao.getInfo(hosId);
    }

    // 存储到checkin，更新room的empty
    public RespInfo checkIn(int hosId, int roomId, String checktime, String leavetime, String checkinstaff, double pay,
                            String ismember, String paytype, int memberId, int orderId) {

        if (orderId != -1) {
            // 更新order状态
            orderDao.updateOrder(orderId, roomId);
        }

        HostelRoom room = roomDao.getRoom(hosId, roomId);
        if (room != null) {

            if (room.getIsempty().equals("empty")) {
                CheckIn checkIn = new CheckIn(hosId, roomId, checktime, leavetime, checkinstaff, pay, ismember, paytype, memberId, orderId);
                checkIn.setState("valid");
                checkinDao.insert(checkIn);
                roomDao.updateState(hosId, roomId, "notempty");
                return new RespInfo(true, "登记成功", "");
            } else {
                return new RespInfo(false, "房间非空", "");
            }

        } else {
            return new RespInfo(false, "房间号不存在", "");
        }
    }

    public RespInfo getCheckIn(int hosId, int roomId) {

        CheckIn checkIn = checkinDao.getCheck(hosId, roomId);
        if (checkIn != null) {
            return new RespInfo(true, "", checkIn);
        } else {
            return new RespInfo(false, "搜索不到入住信息", "");
        }
    }

    // 更新checkin状态，和room状态
    public void checkOut(int hosId, int roomId, String checkouttime, String payinfo, double totalpay) {

        // 存储到checkout
        CheckIn checkIn = (CheckIn) getCheckIn(hosId, roomId).getObject();
        CheckOut checkout = new CheckOut(hosId, roomId, checkIn.getChecktime(), checkouttime,
                checkIn.getCheckinstaff(), totalpay, payinfo, checkIn.getUserId());
        checkoutDao.insert(checkout);

        checkinDao.updateState(hosId, roomId);
        roomDao.updateState(hosId, roomId, "empty");
    }

    public RespInfo getTheOrder(int orderId, int hosId) {

        MyOrder order = orderDao.getTheOrder(orderId);
        if (order != null) {
            if (order.getHostelId() == hosId) {
                return new RespInfo(true, "", order);
            } else {
                return new RespInfo(false, "非本店订单", "");
            }
        } else {
            return new RespInfo(false, "订单号不存在", "");
        }
    }

    public List<HostelInfo> searchHostel(String key, String condition) {

        if (condition.equals("location")) {
            return hostelInfoDao.getHostelByLocation(key);
        } else {
            return hostelInfoDao.getHostelByName(key);
        }
    }

    public TotalFinance getTotalFinance() {

        List hostelNames = new ArrayList();
        List datas = new ArrayList();

        List orderSales = new ArrayList();  // 订单销售额
        List sales = new ArrayList(); // 线下销售额
        List orders = new ArrayList(); // 订单笔数
        List checkins = new ArrayList();  // 入住人次

        List<List> hostels = hostelInfoDao.getAllName();
        for (List info : hostels) {
            hostelNames.add(info.get(1));

            int hosId = Integer.parseInt(info.get(0).toString());
            orderSales.add(orderDao.getOrderPay(hosId));
            orders.add(orderDao.getOrderNum(hosId));
            checkins.add(checkinDao.getCheckNums(hosId));
            double eachsale = checkinDao.getCheckPay(hosId) + checkoutDao.getOutPay(hosId);
            sales.add(eachsale);
        }

        datas.add(orderSales);
        datas.add(sales);
        datas.add(orders);
        datas.add(checkins);

        TotalFinance totalFinance = new TotalFinance(hostelNames, datas);

        return totalFinance;
    }

    public TotalFinance getHostelFinance(int hosId) {

        double eachsale = checkinDao.getCheckPay(hosId) + checkoutDao.getOutPay(hosId);
        TotalFinance totalFinance = new TotalFinance();
        Double[] dou = {orderDao.getOrderPay(hosId), eachsale, 1.0 * orderDao.getOrderNum(hosId), 1.0 * checkinDao.getCheckNums(hosId)};
        totalFinance.setData(dou);

        return totalFinance;
    }

    public int getJoinDays(int hosId) {

        HostelApply apply = applyDao.getTheApply(hosId);
        String beginDatestr = apply.getApplytime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        long day = 0;
        try {
            Date today = df.parse(df.format(new Date()));
            Date joinDay = df.parse(beginDatestr);

            day = (today.getTime() - joinDay.getTime()) / (24 * 60 * 60 * 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return (int) day + 1;
    }

    public List getHostelRecords(int hosId) {

        List list = new ArrayList();
        list.add(checkinDao.getHostelChecks(hosId));
        list.add(checkoutDao.getHostelCheckout(hosId));
        list.add(orderDao.getHostelOrder(hosId));

        return list;
    }
}
