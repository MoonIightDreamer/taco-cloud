package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private OrderAdminService service;

    public AdminController(OrderAdminService service) {
        this.service = service;
    }

    @PostMapping("/deleteOrders")
    public String deleteAllOrders() {
        service.deleteAllOrders();
        return "redirect:/admin";
    }
}