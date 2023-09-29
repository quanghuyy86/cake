package vn.edu.hau.cake.controller.customer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.edu.hau.cake.dto.Cart;
import vn.edu.hau.cake.dto.CartItem;
import vn.edu.hau.cake.model.Product;
import vn.edu.hau.cake.model.SaleOrder;
import vn.edu.hau.cake.model.SaleOrderProducts;
import vn.edu.hau.cake.repository.ProductRepository;
import vn.edu.hau.cake.service.ProductService;
import vn.edu.hau.cake.service.SaleOderService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Controller
public class CartController {
    @Autowired
    private ProductService productService;

    @Autowired
    private SaleOderService saleOderService;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = { "/addToCart" }, method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> ajax_AddToCart(final Model model,
                                                              final HttpServletRequest request,
                                                              final HttpServletResponse response,
                                                              final @RequestBody CartItem cartItem)throws IOException {
        HttpSession session = request.getSession();

        Cart cart = null;

        if (session.getAttribute("cart") != null) {
            cart = (Cart) session.getAttribute("cart");
        } else {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        List<CartItem> cartItems = cart.getCartItems();

        boolean isExists = false;
        for (CartItem item : cartItems) {
            if (item.getProductId() == cartItem.getProductId()) {
                isExists = true;
                item.setQuanlity(item.getQuanlity() + cartItem.getQuanlity());
            }
        }

        if (!isExists) {

            Optional<Product> productInDb = productService.findById(cartItem.getProductId());

            cartItem.setProductName(productInDb.get().getTitle());
            cartItem.setPriceUnit(productInDb.get().getPrice());
            cartItem.setAvatar(productInDb.get().getAvatar());

            cart.getCartItems().add(cartItem);

        }

        session.setAttribute("totalPrice", cart.getTotalPrice());

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        jsonResult.put("code", 200);
        jsonResult.put("status", "TC");
        jsonResult.put("totalItems", getTotalItems(request));
        jsonResult.put("totalPrice", getTotalPrice(request));

        session.setAttribute("totalItems", getTotalItems(request));
        session.setAttribute("totalPrice", getTotalPrice(request));

        return ResponseEntity.ok(jsonResult);
    }

    private int getTotalItems(final HttpServletRequest request) {
        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute("cart") == null) {
            return 0;
        }

        Cart cart = (Cart) httpSession.getAttribute("cart");
        List<CartItem> cartItems = cart.getCartItems();

        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getQuanlity();
        }

        return total;
    }

    public BigDecimal getTotalPrice(final HttpServletRequest request) {
        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute("cart") == null) {
            return BigDecimal.ZERO;
        }

        Cart cart = (Cart) httpSession.getAttribute("cart");
        List<CartItem> cartItems = cart.getCartItems();

        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : cartItems) {
            BigDecimal itemTotal = item.getPriceUnit().multiply(BigDecimal.valueOf(item.getQuanlity()));
            total = total.add(itemTotal);
        }

        return total;
    }

    @RequestMapping(value = {"/cart"}, method = RequestMethod.GET)
    public String cartCheckout(final Model model,
                               final HttpServletResponse response,
                               final HttpServletRequest request,
                               final HttpSession session)throws IOException {

        return"cart";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkOut(final Model model,
                           final @ModelAttribute("saleOrder") SaleOrder SaleOrder){
        return "checkout";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String saveCheckOut(final Model model,
                               final HttpServletRequest request,
                               final HttpServletResponse response,
                               final @ModelAttribute("saleOrder") SaleOrder saleOrder){

        saleOrder.setCode(String.valueOf(System.currentTimeMillis()));
        saleOrder.setTotal(getTotalPrice(request));

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        for(CartItem cartItem : cart.getCartItems()){
            SaleOrderProducts saleOrderProducts = new SaleOrderProducts();
            Optional<Product> productOptional = productService.findById(cartItem.getProductId());
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                // Gán sản phẩm cho SaleOrderProducts
                saleOrderProducts.setProduct(product);
                saleOrderProducts.setQuality(cartItem.getQuanlity());

                saleOrder.addSaleOrderProducts(saleOrderProducts);
            } else {
                // Xử lý trường hợp không tìm thấy sản phẩm (Optional rỗng) nếu cần.
            }

        }

        saleOderService.save(saleOrder);

        session.setAttribute("cart", null);
        session.setAttribute("totalItems", 0);

        return "home";
    }

    @RequestMapping(value = { "/cart/deleteitem" }, method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> productDelete(final Model model,
                                                             final HttpServletRequest request,
                                                             final @RequestBody CartItem cartItem) {

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        List<CartItem> cartItems = cart.getCartItems();

        Iterator<CartItem> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if(item.getProductId() == cartItem.getProductId()) {
                iterator.remove();
                break;
            }
        }

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        jsonResult.put("code", 200);
        jsonResult.put("message", "Đã xóa thành công");
        jsonResult.put("totalPrice", getTotalPrice(request));

        session.setAttribute("totalItems", getTotalItems(request));
        session.setAttribute("totalPrice", getTotalPrice(request));

        return ResponseEntity.ok(jsonResult);
    }



}
