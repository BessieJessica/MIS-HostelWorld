package controller;

import model.MyOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.OrderService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by L.H.S on 2017/3/11.
 */

@Controller
@RequestMapping(value = "/order", method = RequestMethod.POST)
@ResponseBody
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/getFutureOrder")
    public List<MyOrder> getFutureOrder(HttpSession session) {

        int userId = (Integer) session.getAttribute("userId");
        return orderService.getFutureOrder(userId);
    }

    @RequestMapping("/getHistoryOrder")
    public List<MyOrder> getHistoryOrder(HttpSession session) {

        int userId = (Integer) session.getAttribute("userId");
        return orderService.getHistoryOrder(userId);
    }

    @RequestMapping("/getCheckIn")
    public List<MyOrder> getCheckIn(HttpSession session) {

        int userId = (Integer) session.getAttribute("userId");
        return orderService.getCheckin(userId);
    }

    @RequestMapping("/cancelOrder")
    public void cancelOrder(HttpSession session, @RequestParam int orderId) {
        orderService.cancelOrder(orderId);
    }
}
