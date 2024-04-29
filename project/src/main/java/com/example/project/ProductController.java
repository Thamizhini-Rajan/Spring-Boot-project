package com.example.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController
{
    @Autowired
    ProductService ps;
    @PostMapping("/api/product")
    public ResponseEntity<Product> adddata(@RequestBody Product p)
    {
        Product obj=ps.create(p);
        return new ResponseEntity<>(obj,HttpStatus.CREATED);
    }
    @GetMapping("/api/products") 
    public List<Product> fetchDepartmentList() 
    { 
        return ps.fetchDepartmentList(); 
    } 
    @GetMapping("/api/sort/{field}")
    public ResponseEntity<List<Product>> get(@PathVariable String field)
    {
        try{
            return new ResponseEntity<>(ps.getbysort(field),HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/api/product/{productId}")
    public ResponseEntity<Product> putMethod(@PathVariable("productId") int productId,@RequestBody Product pd)
    {
        if(ps.updateDetails(productId,pd) == true)
        {
            return new ResponseEntity<>(pd,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/api/product/{productId}")
    public ResponseEntity<Boolean> delete(@PathVariable("productId") int productId)
    {
        if(ps.deleteEmployee(productId) == true)
        {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }
}
