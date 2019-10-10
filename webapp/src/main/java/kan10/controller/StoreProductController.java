package kan10.controller;

import kan10.dao.StoreProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.inject.Inject;

@Controller
public class StoreProductController {

    @Inject
    StoreProductDao storeProductDao ;

    @GetMapping("/viewproduct/{id}")
    public String message (@PathVariable long id, Model model) {
        model.addAttribute("message",storeProductDao.findOneById( id )) ;
        return "viewproduct";
    }
}
