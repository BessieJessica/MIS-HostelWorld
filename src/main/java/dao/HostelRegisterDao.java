package dao;

import model.HostelInfo;
import model.HostelRegister;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/9.
 */
public interface HostelRegisterDao {

    void insert(HostelRegister register);

    // 验证code是否存在
    List<HostelRegister> checkCode(String checkCode);

    // 设置密码后，更新状态，并存储到hostelinfo
    void updateState(int regId);

    // 存储info
    void insertInfo(HostelInfo info);
}
