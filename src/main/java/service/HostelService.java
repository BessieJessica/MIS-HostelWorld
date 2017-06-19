package service;

import common.RespInfo;
import common.TotalFinance;
import model.HostelInfo;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/12.
 */
public interface HostelService {

    RespInfo hostelLogin(int hostelId, String pwd);

    HostelInfo getInfo(int hosId);

    // 存储到checkin，更新room的empty
    RespInfo checkIn(int hosId, int roomId, String checktime, String leavetime, String checkinstaff, double pay,
                     String ismember, String paytype, int memberId, int orderId);

    RespInfo getCheckIn(int hosId, int roomId);

    // 更新checkin状态，和room状态
    void checkOut(int hosId, int roomId, String checkouttime, String payinfo, double totalpay);

    // 根据订单查询
    RespInfo getTheOrder(int orderId, int hosId);

    // 查询客栈
    List<HostelInfo> searchHostel(String key, String condition);

    // 总经理获取所有统计信息
    TotalFinance getTotalFinance();

    // 客栈获取统计信息
    TotalFinance getHostelFinance(int hosId);

    // 获取客栈加盟天数
    int getJoinDays(int hosId);

    // 获取客栈记录；入住记录、离店记录、订单记录
    List getHostelRecords(int hosId);
}
