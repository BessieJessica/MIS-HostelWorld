package service.impl;

import dao.BillDao;
import model.Bill;
import common.RespInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BillService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by L.H.S on 2017/3/11.
 */
@Service("billService")
@Transactional
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao billDao;

    public RespInfo getBills(int userId) {

        List<List<Bill>> bills = new ArrayList<List<Bill>>();
        List<List> timeLists = billDao.getTimeGroup(userId);
        for (List time: timeLists) {
            bills.add(billDao.getBills(userId, time.get(0).toString(), time.get(1).toString()));
        }

        RespInfo respInfo = new RespInfo(true, "", bills);
        return respInfo;
    }
}
