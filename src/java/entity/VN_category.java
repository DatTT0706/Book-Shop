/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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

public class VN_category implements Serializable{
    
    private int id;
    private String name;
    private String note;
    private int status;
    
}
