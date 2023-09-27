package vn.edu.hau.cake.controller.customer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.edu.hau.cake.dto.Cart;
import vn.edu.hau.cake.dto.CartItem;
import vn.edu.hau.cake.model.Product;
import vn.edu.hau.cake.service.ProductService;

import java.io.IOException;
import java.util.*;

@Controller
public class CartController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"/cart"}, method = RequestMethod.GET)
    public String cartCheckout(final Model model,
                               final HttpServletResponse response,
                               final HttpServletRequest request,
                               final HttpSession session)throws IOException {

        return"cart";
    }
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

    public int getTotalPrice(final HttpServletRequest request) {
        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute("cart") == null) {
            return 0;
        }

        Cart cart = (Cart) httpSession.getAttribute("cart");
        List<CartItem> cartItems = cart.getCartItems();

        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getQuanlity() * item.getPriceUnit().intValue();
        }

        return total;
    }



}
