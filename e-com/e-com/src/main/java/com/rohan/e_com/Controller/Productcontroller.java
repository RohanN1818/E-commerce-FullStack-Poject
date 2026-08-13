package com.rohan.e_com.Controller;

import com.rohan.e_com.model.ProductTable;
import com.rohan.e_com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api")
public class Productcontroller {
    @Autowired
  private ProductService service;
     @GetMapping("/products")
      public ResponseEntity<List<ProductTable>>  getPoducts(){
         return new ResponseEntity<>( service.getProduct(), HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity< ProductTable> getProduct(@PathVariable int id){
         ProductTable product = service.getProductById(id);
         if(product != null){
             return new ResponseEntity<>(product, HttpStatus.OK);
         } else{
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart ProductTable product, @RequestPart MultipartFile imageFile){
         try{
             ProductTable product1 = service.addProduct(product,imageFile);
             return new ResponseEntity<>( product1, HttpStatus.CREATED);
         }
         catch(Exception e){
             return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }
    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProduct(@PathVariable int productId){
         ProductTable product = service.getProductById(productId);
        if (product == null || product.getImageData() == null || product.getImageType() == null) {
            return ResponseEntity.notFound().build();
        }
         byte[] imageFile = product.getImageData();
           return ResponseEntity.ok()
                   .contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
    }
    @PutMapping("/product/{id}")
   public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart ProductTable product, @RequestPart(required = false) MultipartFile imageFile) throws IOException {
          ProductTable product1   =     service.updateProduct(id,product,imageFile);
          if(product1 != null){
              return new ResponseEntity<>("update successfull",HttpStatus.OK);
          } else {
              return new ResponseEntity<>("Something went wrong ",HttpStatus.BAD_REQUEST);
          }

   }
   @DeleteMapping("/product/{id}")
   public ResponseEntity<String> deleteProduct(@PathVariable int id){
       ProductTable product2 =  service.getProductById(id);

         if(product2 != null){
             service.deleteProduct(id);
             return new ResponseEntity<>("product Deleted successFully",HttpStatus.OK);
         } else{
             return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
         }
   }
   @GetMapping("/products/search")
   public ResponseEntity<List<ProductTable>> searchProduct(@RequestParam String keyword){
         System.out.println("Searching with " + keyword);
          List<ProductTable>  product = service.searchProduct(keyword);
          return new ResponseEntity<>(product,HttpStatus.OK);
   }

}
