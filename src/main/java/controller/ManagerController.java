package controller;

import common.RespInfo;
import common.TotalFinance;
import model.HostelApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.HostelApplyService;
import service.HostelService;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by L.H.S on 2017/3/11.
 */

@Controller
@RequestMapping(value = "/myManager", method = RequestMethod.POST)
@ResponseBody
public class ManagerController {

    @Autowired
    private HostelApplyService applyService;
    @Autowired
    private UserService userService;
    @Autowired
    private HostelService hostelService;


    @RequestMapping("/getToApprove")
    public List<HostelApply> getToApprove(HttpSession session) {

        return applyService.getApply();
    }

    @RequestMapping("/getHistoryApprove")
    public List<HostelApply> getHistoryApprove(HttpSession session) {

        return applyService.getHistoryApply();
    }

    @RequestMapping("/Login")
    public RespInfo managerLogin(HttpSession session, @RequestParam int managerId, @RequestParam String pwd) {

        RespInfo respInfo = userService.managerLogin(managerId, pwd);
        if (respInfo.getStatus()) {
            session.setAttribute("managerId", managerId);
            session.setAttribute("managerName", respInfo.getInfo());
        }
        return respInfo;
    }

    @RequestMapping("/Approve")
    public void agree(HttpSession session, @RequestParam int applyId, @RequestParam String state) {

        int approverId = (Integer) session.getAttribute("managerId");
        applyService.updateState(applyId, state, approverId);
    }

    @RequestMapping("/getTotalFinance")
    public TotalFinance getBarChart(HttpSession session) {

        return hostelService.getTotalFinance();
    }

}
