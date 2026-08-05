package com.rohan.e_com.service;


import com.rohan.e_com.Repository.ProductResposatory;
import com.rohan.e_com.model.ProductTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductResposatory repo;

    public List<ProductTable> getProduct() {
     return   repo.findAll();
    }

    public ProductTable getProductById(int id) {
      return   repo.findById(id).orElse(null);
    }

    public ProductTable addProduct(ProductTable product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

//    public ProductTable updateProduct(int id, ProductTable product, MultipartFile imageFile) throws IOException {
////        product.setImageData(imageFile.getBytes());
//        product.setImageName(imageFile.getOriginalFilename());
//        product.setImageType(imageFile.getContentType());
//        return  repo.save(product);
        public ProductTable updateProduct(int id, ProductTable product, MultipartFile imageFile) throws IOException {
            ProductTable existingProduct = repo.findById(id).orElse(null);
            if (existingProduct == null) {
                return null;
            }

            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStockQuantity(product.getStockQuantity());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setAvailable(product.isAvailable());
            existingProduct.setReleaseDate(product.getReleaseDate());
//            // ... other fields like brand, category, available, etc.

            // Only touch image fields if a new image was actually sent
            if (imageFile != null && !imageFile.isEmpty()) {
                existingProduct.setImageData(imageFile.getBytes());
                existingProduct.setImageName(imageFile.getOriginalFilename());
                existingProduct.setImageType(imageFile.getContentType());
            }

            return repo.save(existingProduct);
        }



    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<ProductTable> searchProduct(String keyword) {
            return repo.searchProducts(keyword);
    }
}
