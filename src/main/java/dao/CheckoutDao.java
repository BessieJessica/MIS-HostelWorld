package dao;

import model.CheckOut;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/9.
 */
public interface CheckoutDao {

    void insert(CheckOut checkOut);

    double getOutPay(int hosId);

    // 根据hosId获取登出记录
    List getHostelCheckout(int hosId);
}
