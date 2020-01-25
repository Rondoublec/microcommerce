package fr.ecommerce.microcommerce.controller;


import fr.ecommerce.microcommerce.dao.ProductDao;
import fr.ecommerce.microcommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    //Produits
    @GetMapping(value = "Produits")
    public List<Product> listeProduits(){
        return productDao.findAll();
    }

    //Produits/{id}
    @GetMapping(value= "Produits/{id}")
    public Product afficherUnProduit(@PathVariable int id){

        return productDao.findById(id);
    }

    @PostMapping(value = "/Produits")
    public ResponseEntity<Void> ajouterProduit(@RequestBody Product product){

        Product product1 = productDao.save(product);
        if (product == null){
            return ResponseEntity.noContent().build();
        }
        URI Location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product1.getId())
                .toUri();

        return ResponseEntity.created(Location).build();
    }

}
