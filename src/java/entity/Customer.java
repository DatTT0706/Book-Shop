/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author TRANTATDAT
 */

@Getter
@Setter
@Builder
@ToString
public class Customer {
    
    private int id;
    private String customerName;
    private String address;
    private String email;
    private String phone;
    private String note;
}
