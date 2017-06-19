package service;

import model.MyOrder;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/10.
 */
public interface OrderService {

    List<MyOrder> getFutureOrder(int userId);

    List<MyOrder> getHistoryOrder(int userId);

    List<MyOrder> getCheckin(int userId);

    void insert(int userId, int hosId, String checktime, String leavetime, double pay);

    void cancelOrder(int orderId);
}
