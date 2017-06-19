package dao;

import model.Bill;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/9.
 */
public interface BillDao {

    List getTimeGroup(int userId);

    List<Bill> getBills(int userId, String year, String month);

    void insert(Bill bill);
}
