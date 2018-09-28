package com.ciicgat.springmyself.ioc;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 下午9:12 2018/7/22
 */
@Data
public class Bean {

    private String name;

    private String id;

    private String className;

    private String scope="singleton";

    private List<Property> properties=new ArrayList<Property>();

}
