package vn.edu.hau.cake.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hau.cake.dto.Cart;
import vn.edu.hau.cake.dto.CartItem;
import vn.edu.hau.cake.model.SaleOrder;
import vn.edu.hau.cake.model.SaleOrderProducts;
import vn.edu.hau.cake.repository.ProductRepository;
import vn.edu.hau.cake.repository.SaleOrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SaleOderServiceIplm implements SaleOderService{
    @Autowired
    private SaleOrderRepository saleOrderRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public SaleOrder save(SaleOrder saleOrder) {

        return saleOrderRepository.save(saleOrder);
    }

    @Override
    public SaleOrder save(SaleOrder saleOrder, HttpServletRequest request) {

        saleOrder.setCode(String.valueOf(System.currentTimeMillis()));

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        for (CartItem cartItem : cart.getCartItems()) {
            SaleOrderProducts saleOrderProducts = new SaleOrderProducts();

            saleOrderProducts.setQuality(cartItem.getQuanlity());
            saleOrder.setTotal(saleOrderProducts.getSaleOrder().getTotal());

            // sử dụng hàm tiện ích add hoặc remove đới với các quan hệ onetomany
            saleOrder.addSaleOrderProducts(saleOrderProducts);
        }




        return saleOrderRepository.save(saleOrder);
    }

    @Override
    public List<SaleOrder> saveAll(List<SaleOrder> entities) {
        return (List<SaleOrder>) saleOrderRepository.saveAll(entities);
    }

    @Override
    public Optional<SaleOrder> findById(Integer integer) {
        return saleOrderRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return saleOrderRepository.existsById(integer);
    }

    @Override
    public Iterable<SaleOrder> findAll() {
        return saleOrderRepository.findAll();
    }

    @Override
    public Iterable<SaleOrder> findAllById(Iterable<Integer> integers) {
        return saleOrderRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return saleOrderRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        saleOrderRepository.deleteById(integer);
    }

    @Override
    public void delete(SaleOrder entity) {
        saleOrderRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        saleOrderRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends SaleOrder> entities) {
        saleOrderRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        saleOrderRepository.deleteAll();
    }
}
