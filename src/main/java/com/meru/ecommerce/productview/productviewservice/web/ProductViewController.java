package com.meru.ecommerce.productview.productviewservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meru.ecommerce.productview.productviewservice.entity.Inventory;
import com.meru.ecommerce.productview.productviewservice.entity.Price;
import com.meru.ecommerce.productview.productviewservice.entity.Product;
import com.meru.ecommerce.productview.productviewservice.entity.ProductView;
import com.meru.ecommerce.productview.productviewservice.entity.Promotion;
import com.meru.ecommerce.productview.productviewservice.service.ProductViewService;

@Controller
public class ProductViewController {
    static String WELCOME_MSG = "Welcome to Product View Service. This is not a valid service path.";
    static String DELETE_MSG = "Delete Product View for ProductViewID: %s %s";
    static String CREATE_SUB_COMPONENT_MSG = "Create %s for product %d in ProductView is %s";
    static String UPDATE_SUB_COMPONENT_MSG = "Update %s for product %d in ProductView is %s";
    static String SUCCESS = "Success";
    static String ERROR = "Failed";
    static String COMPONENT_PRODUCT = "Product";
    static String COMPONENT_INVENTORY = "Inventory";
    static String COMPONENT_PRICE = "Price";
    static String COMPONENT_PROMOTION = "Promotion";

    @Autowired
    private ProductViewService pvs;
    
    @RequestMapping(value={"/index"},method = RequestMethod.GET)
	public String index(Model model){
		List<ProductView> productview= pvs.getAllProductViews();
		model.addAttribute("productview", productview);
		return "index";
	}
	
    @RequestMapping(value="addProduct")
	public String addProduct(Model model) {
		
		model.addAttribute("product", new Product());
		return "addProduct";
	}
    
    @RequestMapping(value="addPrice")
	public String addPrice(Model model) {
		
		model.addAttribute("price", new Price());
		return "addPrice";
	}
    
    @RequestMapping(value="addInventory")
	public String addInventory(Model model) {
		
		model.addAttribute("inventory", new Inventory());
		return "addInventory";
	}
    
	@RequestMapping(value="addPromotion")
	public String addPromotion(Model model) {
		model.addAttribute("promotion", new Promotion());
		return "addPromotion";
	}

	@RequestMapping(value="saveProduct", method=RequestMethod.POST)
	public String saveProduct(Product product) {
		pvs.createOrUpdateProductInProductView(product.getProductId(),product);
		return "redirect:/index";
	}
	@RequestMapping(value="saveInventory", method=RequestMethod.POST)
	public String saveInventory(Inventory inventory) {
		pvs.createOrUpdateInventoryInProductView(inventory.getProductId(),inventory);
		return "redirect:/index";
	}
	@RequestMapping(value="savePrice", method=RequestMethod.POST)
	public String savePrice(Price price) {
		pvs.createOrUpdatePriceInProductView(price.getProductId(),price);
		return "redirect:/index";
	}
	@RequestMapping(value="savePromotion", method=RequestMethod.POST)
	public String savePromotion(Promotion promotion) {
		pvs.createOrUpdatePromotionInProductView(promotion.getProductId(),promotion);
		return "redirect:/index";
	}
	
	
	
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<String> showInfo() {
        return ResponseEntity.badRequest().headers(new HttpHeaders()).body(WELCOME_MSG);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ProductView> getProductViewById(@PathVariable int id) {
        ProductView pvm = pvs.getProductViewById(id);
        HttpStatus status = HttpStatus.OK;
        if (null == pvm) {
            status = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(pvm, new HttpHeaders(), status);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<List<ProductView>> getAllProductViews() {
        return ResponseEntity.ok().body(pvs.getAllProductViews());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<String> deleteProductView(@PathVariable int id) {
        boolean deleted = pvs.removeProductView(id);
        HttpStatus status = HttpStatus.OK;
        String msg = String.format(DELETE_MSG, id, SUCCESS);
        if (!deleted) {
            msg = String.format(DELETE_MSG, id, ERROR);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(msg, new HttpHeaders(), status);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/product")
    public ResponseEntity<String> addProductToProductView(@RequestBody Product product) {
        boolean created = pvs.createOrUpdateProductInProductView(product.getProductId(), product);
        HttpStatus status = HttpStatus.OK;
        String msg = String.format(CREATE_SUB_COMPONENT_MSG, COMPONENT_PRODUCT, product.getProductId(), SUCCESS);
        if (!created) {
            String.format(CREATE_SUB_COMPONENT_MSG, COMPONENT_PRODUCT, product.getProductId(), ERROR);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(msg, new HttpHeaders(), status);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/product/{id}")
    public ResponseEntity<String> updateProductInProductView(@PathVariable int id, @RequestBody Product product) {
        boolean updated = pvs.createOrUpdateProductInProductView(id, product);
        HttpStatus status = HttpStatus.OK;
        String msg = String.format(UPDATE_SUB_COMPONENT_MSG, COMPONENT_PRODUCT, id, SUCCESS);
        if (!updated) {
            msg = String.format(UPDATE_SUB_COMPONENT_MSG, COMPONENT_PRODUCT, id, ERROR);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(msg, new HttpHeaders(), status);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/inventory")
    public ResponseEntity<String> addInventoryToProductView(@RequestBody Inventory inventory) {
        boolean created = pvs.createOrUpdateInventoryInProductView(inventory.getProductId(), inventory);
        HttpStatus status = HttpStatus.OK;
        String msg = String.format(CREATE_SUB_COMPONENT_MSG, COMPONENT_INVENTORY, inventory.getProductId(), SUCCESS);
        if (!created) {
            String.format(CREATE_SUB_COMPONENT_MSG, COMPONENT_INVENTORY, inventory.getProductId(), ERROR);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(msg, new HttpHeaders(), status);
    }

    //Update Inventory in Product View
    @RequestMapping(method = RequestMethod.PUT, value = "/inventory/{id}")
    public ResponseEntity<String> updateInventoryInProductView(@PathVariable int id, @RequestBody Inventory inventory) {
        boolean updated = pvs.createOrUpdateInventoryInProductView(id, inventory);
        HttpStatus status = HttpStatus.OK;
        String msg = String.format(UPDATE_SUB_COMPONENT_MSG, COMPONENT_INVENTORY, id, SUCCESS);
        if (!updated) {
            msg = String.format(UPDATE_SUB_COMPONENT_MSG, COMPONENT_INVENTORY, id, ERROR);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(msg, new HttpHeaders(), status);
    }

    //Add Price to Product View
    @RequestMapping(method = RequestMethod.POST, value = "/price")
    public ResponseEntity<String> addPriceToProductView(@RequestBody Price price) {
        boolean created = pvs.createOrUpdatePriceInProductView(price.getProductId(), price);
        HttpStatus status = HttpStatus.OK;
        String msg = String.format(CREATE_SUB_COMPONENT_MSG, COMPONENT_PRICE, price.getProductId(), SUCCESS);
        if (!created) {
            String.format(CREATE_SUB_COMPONENT_MSG, COMPONENT_PRICE, price.getProductId(), ERROR);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(msg, new HttpHeaders(), status);
    }

    //Update Price in Product View
    @RequestMapping(method = RequestMethod.PUT, value = "/price/{id}")
    public ResponseEntity<String> updatePriceInProductView(@PathVariable int id, @RequestBody Price price) {
        boolean updated = pvs.createOrUpdatePriceInProductView(id, price);
        HttpStatus status = HttpStatus.OK;
        String msg = String.format(UPDATE_SUB_COMPONENT_MSG, COMPONENT_PRICE, id, SUCCESS);
        if (!updated) {
            msg = String.format(UPDATE_SUB_COMPONENT_MSG, COMPONENT_PRICE, id, ERROR);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(msg, new HttpHeaders(), status);
    }

    //Add Promotion to Product View
    @RequestMapping(method = RequestMethod.POST, value = "/promotion")
    public ResponseEntity<String> addPromotionToProductView(@RequestBody Promotion promotion) {
        boolean created = pvs.createOrUpdatePromotionInProductView(promotion.getProductId(), promotion);
        HttpStatus status = HttpStatus.OK;
        String msg = String.format(CREATE_SUB_COMPONENT_MSG, COMPONENT_PROMOTION, promotion.getProductId(), SUCCESS);
        if (!created) {
            String.format(CREATE_SUB_COMPONENT_MSG, COMPONENT_PROMOTION, promotion.getProductId(), ERROR);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(msg, new HttpHeaders(), status);
    }

    //Update Promotion in Product View
    @RequestMapping(method = RequestMethod.PUT, value = "/promotion/{id}")
    public ResponseEntity<String> updatePromotionInProductView(@PathVariable int id, @RequestBody Promotion promotion) {
        boolean updated = pvs.createOrUpdatePromotionInProductView(id, promotion);
        HttpStatus status = HttpStatus.OK;
        String msg = String.format(UPDATE_SUB_COMPONENT_MSG, COMPONENT_PROMOTION, id, SUCCESS);
        if (!updated) {
            msg = String.format(UPDATE_SUB_COMPONENT_MSG, COMPONENT_PROMOTION, id, ERROR);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(msg, new HttpHeaders(), status);
    }
}
