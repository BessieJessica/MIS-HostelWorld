package dao;

import model.MyOrder;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/9.
 */
public interface MyOrderDao {

    // orderstate=future
    List<MyOrder> getMyFutureOrder(int userId);

    // orderstate=history
    List<MyOrder> getMyHistoryOrder(int userId);

    // checkstate=checked
    List<MyOrder> getMyCheckin(int userId);

    MyOrder getTheOrder(int orderId);

    void updateOrder(int orderId, int roomId);

    int insert(MyOrder order);

    // 退订
    void cancelOrder(int orderId);

    // 订单总额
    double getOrderPay(int hosId);

    // 订单笔数
    int getOrderNum(int hosId);

    // 根据hosId获取订单
    List getHostelOrder(int hosId);
}
