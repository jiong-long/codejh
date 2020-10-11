package com.cases.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 
 * @author wangjinlong
 * @datetime Aug 28, 2020 4:42:58 PM
 *
 */
@Data
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private int age;
    private String sex;
    
    public int doubleAge() {
    	return age * 2;
    }
}

