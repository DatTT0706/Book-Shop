/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.*;
import entity.*;
import java.util.List;

/**
 *
 * @author TRANTATDAT
 */
public class Main {

    public static void main(String[] args) {
//        List<Image> p = new ImageDao().getAllByProductId(6);
//        p.forEach(System.out::println);

        List<Product> lsP = new ProductDao().getSameAuthor(24);
        lsP.forEach(System.out::println);

//        System.out.println(new VN_categoryDao().getOne(1));
        Product p = Product.builder()
                .typeId(1)
                .authorId(5)
                .publisherId(1)
                .vnCategoryId(2)
                .frCategoryId(0)
                .name("Con chim xanh biếc bay về")
                .price(103000)
                .quantity(15)
                .imgName("biamem.jpg")
                .description("Hi")
                .note(null)
                .status(1)
                .build();
        System.out.println(new ProductDao().update(4,p));
        System.out.println(new ProductDao().getOne(4));
//        FR_category p = FR_category.builder()
//                .name("Hi3")
//                .note(null)
//                .status(1)
//                .build();
//        System.out.println(new FR_categoryDao().update(25,p));
//        Account a = new AccountDao().login("acckhach@gmail.com", "12345");
//        System.out.println(a);
        
        
    }
}
