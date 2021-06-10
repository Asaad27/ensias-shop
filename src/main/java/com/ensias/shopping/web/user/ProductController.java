package com.ensias.shopping.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ensias.shopping.entity.Classification;
import com.ensias.shopping.entity.OrderItem;
import com.ensias.shopping.entity.Product;
import com.ensias.shopping.entity.res.ResultBean;
import com.ensias.shopping.service.ClassificationService;
import com.ensias.shopping.service.ProductService;
import com.ensias.shopping.service.ShopCartService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ClassificationService classificationService;
    @Autowired
    private ShopCartService shopCartService;


    @RequestMapping("/get.do")
    public ResultBean<Product> getProduct(int id) {
        Product product = productService.findById(id);
        return new ResultBean<>(product);
    }


    @RequestMapping("/get.html")
    public String toProductPage(int id, Map<String, Object> map) {
        Product product = productService.findById(id);
        map.put("product", product);
        return "mall/product/info";
    }

    @ResponseBody
    @RequestMapping("/hot.do")
    public ResultBean<List<Product>> getHotProduct() {
        List<Product> products = productService.findHotProduct();
        return new ResultBean<>(products);
    }


    @ResponseBody
    @RequestMapping("/new.do")
    public ResultBean<List<Product>> getNewProduct(int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Product> products = productService.findNewProduct(pageable);
        return new ResultBean<>(products);
    }

    @RequestMapping("/category.html")
    public String toCatePage(int cid, Map<String, Object> map) {
        Classification classification = classificationService.findById(cid);
        map.put("category", classification);
        return "mall/product/category";
    }

    @RequestMapping("/toCart.html")
    public String toCart(){
        return "mall/product/cart";
    }


    @ResponseBody
    @RequestMapping("/category.do")
    public ResultBean<List<Product>> getCategoryProduct(int cid, int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Product> products = productService.findByCid(cid, pageable);
        return new ResultBean<>(products);
    }


    @ResponseBody
    @RequestMapping("/categorySec.do")
    public ResultBean<List<Product>> getCategorySecProduct(int csId, int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Product> products = productService.findByCsid(csId, pageable);
        return new ResultBean<>(products);
    }


    @ResponseBody
    @RequestMapping("/getCategorySec.do")
    public ResultBean<List<Classification>> getCategorySec(int cid){
        List<Classification> list = classificationService.findByParentId(cid);
        return new ResultBean<>(list);
    }


    @ResponseBody
    @RequestMapping("/addCart.do")
    public ResultBean<Boolean> addToCart(int productId, HttpServletRequest request) throws Exception {
        shopCartService.addCart(productId, request);
        return new ResultBean<>(true);
    }


    @ResponseBody
    @RequestMapping("/delCart.do")
    public ResultBean<Boolean> delToCart(int productId, HttpServletRequest request) throws Exception {
        shopCartService.remove(productId, request);
        return new ResultBean<>(true);
    }


    @ResponseBody
    @RequestMapping("/listCart.do")
    public ResultBean<List<OrderItem>> listCart(HttpServletRequest request) throws Exception {
        List<OrderItem> orderItems = shopCartService.listCart(request);
        return new ResultBean<>(orderItems);
    }


}
