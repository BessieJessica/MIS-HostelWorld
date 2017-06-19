package controller;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import common.RespInfo;
import common.TotalFinance;
import model.HostelInfo;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.HostelApplyService;
import service.HostelService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by L.H.S on 2017/3/11.
 */
@Controller
@RequestMapping("/hostel")
@ResponseBody
public class HostelController {

    @Autowired
    private HostelApplyService applyService;
    @Autowired
    private HostelService hostelService;

    @RequestMapping("/apply")
    public void apply(HttpSession session, @RequestParam String applyer, @RequestParam String phone,
                      @RequestParam String email, @RequestParam String identity, @RequestParam String hostelname,
                      @RequestParam String location, @RequestParam String description, @RequestParam String imgurl) {

        applyService.apply(applyer, phone, email, identity, hostelname, location, description, imgurl);
    }

    @RequestMapping("/checkCode")
    public RespInfo checkCode(HttpSession session, @RequestParam String checkcode) {

        return applyService.checkCode(checkcode);
    }

    @RequestMapping("/setPwd")
    public void setPwd(HttpSession session, @RequestParam int regId, @RequestParam int applyId, @RequestParam String pwd) {

        applyService.setPwd(regId, applyId, pwd);
    }

    @RequestMapping("/Login")
    public RespInfo login(HttpSession session, @RequestParam int hosId, @RequestParam String pwd) {
        RespInfo respInfo = hostelService.hostelLogin(hosId, pwd);

        if (respInfo.getStatus()) {
            session.setAttribute("hosId", hosId);
        }
        return respInfo;
    }

    @RequestMapping("/getInfo")
    public HostelInfo getInfo(HttpSession session) {

        int hosId = (Integer) session.getAttribute("hosId");
        return hostelService.getInfo(hosId);
    }

    @RequestMapping("/modApply")
    public void modApply(HttpSession session, @RequestParam String applyer, @RequestParam String phone,
                         @RequestParam String email, @RequestParam String hostelname, @RequestParam String notice,
                         @RequestParam String location, @RequestParam String description, @RequestParam String imgurl) {

        int hosId = (Integer) session.getAttribute("hosId");
        applyService.modApply(hosId, applyer, phone, email, hostelname, location, description, notice, imgurl);
    }

    @RequestMapping("/CheckIn")
    public RespInfo checkIn(HttpSession session, @RequestParam int roomId, @RequestParam String checktime,
                            @RequestParam String leavetime, @RequestParam String checkinstaff, @RequestParam double pay,
                            @RequestParam String ismember, @RequestParam String paytype,
                            @RequestParam int memberId, @RequestParam int orderId) {

        int hosId = (Integer) session.getAttribute("hosId");
        RespInfo respInfo = hostelService.checkIn(hosId, roomId, checktime, leavetime,
                checkinstaff, pay, ismember, paytype, memberId, orderId);

        return respInfo;
    }

    @RequestMapping("/getCheckIn")
    public RespInfo getCheck(HttpSession session, @RequestParam int roomId) {
        int hosId = (Integer) session.getAttribute("hosId");
        RespInfo respInfo = hostelService.getCheckIn(hosId, roomId);

        return respInfo;
    }

    @RequestMapping("/CheckOut")
    public void checkOut(HttpSession session, @RequestParam int roomId, @RequestParam String checkouttime,
                         @RequestParam double totalpay, @RequestParam String payinfo) {

        int hosId = (Integer) session.getAttribute("hosId");
        hostelService.checkOut(hosId, roomId, checkouttime, payinfo, totalpay);
    }

    @RequestMapping("/getTheOrder")
    public RespInfo getTheOrder(HttpSession session, @RequestParam int orderId) {

        int hosId = (Integer) session.getAttribute("hosId");
        return hostelService.getTheOrder(orderId, hosId);
    }

    @RequestMapping("/getHostelFinance")
    public TotalFinance getHostelFinance(HttpSession session) {

        int hosId = (Integer) session.getAttribute("hosId");
        return hostelService.getHostelFinance(hosId);
    }

    @RequestMapping("/getJoinDays")
    public int getJoinDays(HttpSession session) {

        int hosId = (Integer) session.getAttribute("hosId");
        return hostelService.getJoinDays(hosId);
    }

    @RequestMapping("/getHostelRecords")
    public List getHostelRecords(HttpSession session) {

        int hosId = (Integer) session.getAttribute("hosId");
        return hostelService.getHostelRecords(hosId);
    }
}
