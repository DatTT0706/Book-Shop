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
public class Publisher {
    
    private int id;
    private String name;
    private int status;
    private String note;
}
