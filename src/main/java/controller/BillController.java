package controller;

import common.RespInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.BillService;

import javax.servlet.http.HttpSession;

/**
 * Created by L.H.S on 2017/3/11.
 */

@Controller
@RequestMapping(value = "/bill", method = RequestMethod.POST)
@ResponseBody
public class BillController {

    @Autowired
    private BillService billService;

    @RequestMapping("/getBills")
    public RespInfo getBills(HttpSession session) {

        int userId = (Integer) session.getAttribute("userId");
        return billService.getBills(userId);
    }
}
