/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.io.Serializable;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;


/**
 *
 * @author TRANTATDAT
 */

@Setter
@Getter
@Builder
@ToString

public class Product implements Serializable{
    
    
    // tim hieu ve design pattern
    private int id;
    private int typeId;
    private int authorId;
    private int publisherId;
    private int vnCategoryId;
    private int frCategoryId;
    private String name;
    private double price;
    private int quantity;
    private String imgName;
    private String description;
    private String note;
    private int status;
    
    
}



