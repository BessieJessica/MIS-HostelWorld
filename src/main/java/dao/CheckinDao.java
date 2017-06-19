package dao;

import model.CheckIn;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/9.
 */
public interface CheckinDao {

    void insert(CheckIn checkIn);

    CheckIn getCheck(int hosId, int roomId);

    void updateState(int hosId, int roomId);

    // checkin 人次
    int getCheckNums(int hosId);

    // valid状态且 userId = -1 的checkin金额
    double getCheckPay(int hosId);

    // 根据客栈获取入住登记
    List getHostelChecks(int hosId);
}
