package vn.edu.hau.cake.controller.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hau.cake.model.Categories;
import vn.edu.hau.cake.model.Product;
import vn.edu.hau.cake.service.CategoryService;
import vn.edu.hau.cake.service.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/admin/productlist", method = RequestMethod.GET)
    public String listProduct(final Model model,
                              final @Param("keyword") String keyword,
                              final @RequestParam(name = "page", defaultValue = "0") int page) throws IOException{
        int pageSize = 10;

        Page<Product> productList = productService.gettAllProduct(page, 5);

        if(keyword != null){
            productList = this.productService.searchPageProduct(keyword, page, pageSize);
            model.addAttribute("keyword", keyword);
        }
        model.addAttribute("productList", productList);
        model.addAttribute("productList", productList.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productList.getTotalPages());
        return "product_list";
    }
    @RequestMapping(value = "/admin/addproduct", method = RequestMethod.GET)
    public String addProduct(final Model model) throws IOException{
        model.addAttribute("product", new Product());
        List<Categories> listCategories = categoryService.findAll();
        model.addAttribute("categories", listCategories);
        return "addproduct";
    }
    @RequestMapping(value = "/admin/addproduct", method = RequestMethod.POST)
    public String addProducts(final Model model,
                              final @ModelAttribute("product") Product product,
                              final @RequestParam("productAvatar") MultipartFile avatar,
                              final @RequestParam("productPictures") MultipartFile[] picture) throws IOException {
        productService.save(product, avatar, picture);
        model.addAttribute("product", new Product());
        return "redirect:/admin/productlist";
    }
    @RequestMapping(value = "/admin/updateproduct/{id}", method = RequestMethod.GET)
    public String updateProduct(final Model model,
                                final @PathVariable("id") Integer id){
        List<Categories> categoriesList = categoryService.findAll();
        model.addAttribute("categories", categoriesList);
        Optional<Product> productDatabase = productService.findById(id);
        model.addAttribute("product", productDatabase);

        return "editproduct";
    }

    @RequestMapping(value = "/admin/updateproduct/{id}", method = RequestMethod.POST)
    public String updateProducts(final Model model,
                                 final @PathVariable("id") Integer id,
                                 final @ModelAttribute("product") Product product,
                                 final @RequestParam("productAvatar") MultipartFile avatar,
                                 final @RequestParam("productPictures") MultipartFile[] picture) throws IOException{
        productService.updateProduct(id, product, avatar, picture);
        model.addAttribute("product", new Product());
        return "redirect:/admin/productlist";
    }

    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    String deleteProduct(final @PathVariable("id") Integer id){
        productService.deleteById(id);
        return "redirect:/admin/productlist";
    }


}
