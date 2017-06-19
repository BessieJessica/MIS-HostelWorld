package service;

import common.RespInfo;
import model.HostelApply;

import java.util.List;

/**
 * Created by L.H.S on 2017/3/11.
 */
public interface HostelApplyService {

    void apply(String applyer, String phone,
               String email, String identity, String hostelname,
               String location, String description, String imgurl);

    List<HostelApply> getApply();

    List<HostelApply> getHistoryApply();

    // 经理审批
    void updateState(int applyId, String state, int approverId);

    // 验证checkcode
    RespInfo checkCode(String checkCode);

    // 设置密码
    void setPwd(int regId, int applyId, String pwd);

    // 客栈修改信息  identity 即 客栈Id
    void modApply(int hosId, String applyer, String phone,
                  String email, String hostelname, String location,
                  String description, String notice, String imgurl);
}
