package com.ciicgat.springmyself.test;

import com.ciicgat.springmyself.ioc.IocBeanFactory;
import com.ciicgat.springmyself.ioc.IocContainer;
import com.ciicgat.springmyself.pojo.Student;
import com.ciicgat.springmyself.service.StudentService;

/**
 * Company:中智关爱通(上海)
 *
 * @author：tao.zhang
 * @Date：Created in 16:54 2018/9/25
 */
public class StudentServiceTest {

    public static void main(String[] args) throws Exception {
        IocContainer.init();
        StudentService studentService = IocBeanFactory.getBean(StudentService.class);
        Student student = studentService.getStudent();
        System.out.println("student = " + student);
    }
}
