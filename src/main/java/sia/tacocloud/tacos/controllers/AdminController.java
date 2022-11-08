package sia.tacocloud.tacos.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.tacos.controllers.services.OrderAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private OrderAdminService orderAdminService;

    public AdminController(OrderAdminService orderAdminService) {
        this.orderAdminService = orderAdminService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public String showAdminPage(){
        return "admin";
    }
    @PostMapping("/deleteOrders")
    public String deleteAllOrders(){
        orderAdminService.deleteAllOrders();
        return "redirect:/admin";
    }
}
