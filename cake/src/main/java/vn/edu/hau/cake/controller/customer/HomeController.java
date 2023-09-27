package vn.edu.hau.cake.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.edu.hau.cake.model.Product;
import vn.edu.hau.cake.service.ProductService;

import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String Home(final Model model) throws IOException{
        List<Product> productHot = productService.findByProductHot();
        List<Product> productList1 = productService.findByCategoryId1();
        List<Product> productList2 = productService.findByCategoryId2();
        List<Product> productList3 = productService.findByCategoryId3();
        model.addAttribute("productHot", productHot);
        model.addAttribute("product1", productList1);
        model.addAttribute("product2", productList2);
        model.addAttribute("product3", productList3);
        return "home";
    }

}
