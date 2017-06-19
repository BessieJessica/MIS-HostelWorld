package service.impl;

import common.RespInfo;
import common.SendEmail;
import dao.HostelApplyDao;
import dao.HostelInfoDao;
import dao.HostelRegisterDao;
import dao.HostelRoomDao;
import model.HostelApply;
import model.HostelInfo;
import model.HostelRegister;
import model.HostelRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.HostelApplyService;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by L.H.S on 2017/3/11.
 */
@Service("applyService")
@Transactional
public class HostelApplyServiceImpl implements HostelApplyService {

    @Autowired
    private HostelApplyDao applyDao;
    @Autowired
    private HostelRegisterDao registerDao;
    @Autowired
    private HostelInfoDao infoDao;
    @Autowired
    private HostelRoomDao roomDao;

    public void apply(String applyer, String phone,
                      String email, String identity, String hostelname,
                      String location, String description, String imgurl) {

        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        HostelApply apply = new HostelApply(applyer, phone, email, identity, hostelname, location,
                description, imgurl, "unchecked", "open", df.format(today));

        applyDao.insert(apply);
    }

    public List<HostelApply> getApply() {
        return applyDao.getApply();
    }

    public List<HostelApply> getHistoryApply() {
        return applyDao.getHistory();
    }

    public void updateState(int applyId, String state, int approverId) {

        HostelApply ha = applyDao.updateState(applyId, state, approverId);

        String info = "对不起，<br>您的申请未经过审批。<br>详情请咨询电话：1525099xxxx<br>" +
                "@<a href='http://localhost:8082/html/HomePage.jsp'>HostelWorld</a>";
        RespInfo respInfo = new RespInfo(true, info, ha.getEmail());

        if (state.equals("inapprove")) {
            // 发送邮件
            sendEmail(respInfo);
            return;
        }

        if (ha.getApplytype().equals("modify")) {

            // 更新数据库，发送邮件
            // 其中identity为hosId
            infoDao.updateInfo(ha);
            sendEmail(respInfo);
            return;
        }

        // 若同意，生成验证码， 并发送邮件
        char code1 = (char) ('A' + (int) (Math.random() * 26));
        char code2 = (char) ('A' + (int) (Math.random() * 26));
        char code3 = (char) ('A' + (int) (Math.random() * 26));
        int codeId = applyId % 1000;
        String checkCode = String.valueOf(code1) + codeId + "" + String.valueOf(code2);
        if (codeId < 10) {
            checkCode += (codeId + "" + String.valueOf(code3) + codeId + "");
        } else if (codeId < 100) {
            checkCode += (String.valueOf(code2) + String.valueOf(code3));
        } else {
            checkCode += String.valueOf(code3);
        }

        HostelRegister register = new HostelRegister(checkCode, applyId, "notReg");
        registerDao.insert(register);

        String sucInfo = "恭喜您！<br>您的开店申请已通过审批！<br>您的注册码是：<span style='color:red; font-size: 20px;'>"
                + checkCode + "</span><br>请到<a href='http://localhost:8082/html/HomePage.jsp'>HostelWorld</a>进行注册！";
        respInfo.setInfo(sucInfo);
        sendEmail(respInfo);
    }

    public RespInfo checkCode(String checkCode) {
        List<HostelRegister> registers = registerDao.checkCode(checkCode);


        if (registers.size() == 0) {
            return new RespInfo(false, "无效的验证码", "");
        } else {
            HostelRegister reg = registers.get(0);
            if (reg.getState() == "reg") {
                return new RespInfo(false, "无效的验证码", "");
            } else {
                return new RespInfo(true, "", reg);
            }
        }
    }

    // 更新register状态，拉取apply中的信息，存储到info
    public void setPwd(int regId, int applyId, String pwd) {

        registerDao.updateState(regId);
        HostelApply ha = applyDao.getTheApply(applyId);
        HostelInfo hi = new HostelInfo(applyId, pwd, ha.getHostelname(), ha.getLocation(),
                ha.getDescription(), ha.getApplyer(), ha.getPhone(), ha.getEmail(), "", 10);

        registerDao.insertInfo(hi);

        for (int i=101; i<111; i++) {
            roomDao.insertRoom(applyId, i);
        }
    }

    public void modApply(int hosId, String applyer, String phone, String email, String hostelname,
                         String location, String description, String notice, String imgurl) {

        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        HostelApply ha = new HostelApply(applyer, phone, email, hosId + "", hostelname, location,
                description, imgurl, "unchecked", "modify", df.format(today));
        ha.setNotice(notice);

        applyDao.insert(ha);
    }

    private void sendEmail(RespInfo respInfo) {
        SendEmail sendEmail = new SendEmail(respInfo);
        sendEmail.start();
    }
}
