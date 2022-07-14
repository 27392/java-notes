package cn.haohaoli.book.headfirst.iterator.version3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author lwh
 */
@Getter
@ToString
@AllArgsConstructor
public class MenuItem {

    private String  name;
    private String  description;
    private boolean vegetarian;
    private double  price;

}
