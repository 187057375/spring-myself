package com.ciicgat.springmyself.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ciicgat.springmyself.annotation.Async;
import com.ciicgat.springmyself.annotation.Autowired;
import com.ciicgat.springmyself.annotation.Service;
import com.ciicgat.springmyself.annotation.advice.Advice;
import com.ciicgat.springmyself.annotation.advice.Aspect;
import com.ciicgat.springmyself.pojo.Person;
import com.ciicgat.springmyself.pojo.Student;
import com.ciicgat.springmyself.service.PersonService;
import com.ciicgat.springmyself.service.StudentService;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 16:48 2018/9/25
 */
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private PersonService personService;

    @Advice(value = "StudentAspect")
//    @Async
    @Override
    public Student getStudent(){
        Person person = personService.getPerson();
        Student student = new Student();
        student.setName(person.getName());
        student.setAge(person.getAge());
        student.setGender(person.getGender());
        student.setGrade("九年级");
        System.out.println("student = " + JSONObject.toJSONString(student));
        return student;
    }
}
