package com.ooad6.ecommerce.controller;

import com.ooad6.ecommerce.model.Items;
import com.ooad6.ecommerce.repository.ItemsShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    ItemsShow items;

    @GetMapping("/allitems")
    public List<Items> allItems() {
        System.out.println("This is Allitems");
        return items.findAll();
    }

    // Basically performing CRUD operations along with RequestParam will be recognized by swagger
    //    @GetMapping("/testAPI")
    //    public String testAPI() {
    //        System.out.println("This is testAPI");
    //        return "";
    //    }
}
