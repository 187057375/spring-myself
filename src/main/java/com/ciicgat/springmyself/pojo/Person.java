package com.ciicgat.springmyself.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 16:44 2018/9/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private String name;

    private String gender;

    private int age;
}
