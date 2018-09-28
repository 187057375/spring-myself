package com.ciicgat.springmyself.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ciicgat.springmyself.annotation.Service;
import com.ciicgat.springmyself.annotation.advice.Advice;
import com.ciicgat.springmyself.pojo.Person;
import com.ciicgat.springmyself.service.PersonService;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 14:20 2018/9/26
 */
@Service
public class PersonServiceImpl implements PersonService {

//    @Advice("PersonAspect")
    @Override
    public Person getPerson() {
        Person person = new Person("张三", "男", 18);
        System.out.println("person = " + JSONObject.toJSONString(person));
        return person;
    }
}
