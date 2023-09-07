package com.springcore;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "ECOMMERCE WEBSITE" );
        ApplicationContext cn=new ClassPathXmlApplicationContext("config.xml");
//        Address address= new Address("nepa", "mp");
//        Student st= new Student();
        Scanner sc= new Scanner(System.in);
//        System.out.println("enter enrollment number");
//        st.setEnrollment(sc.next());
//        System.out.println("enter name");
//        sc.nextLine();
//        st.setName(sc.nextLine());
//        System.out.println("enter age");
//        st.setAge(sc.nextInt());
//        
//        		
//        StudentDao s=(StudentDao)cn.getBean("studentDao");
//        s.insert(st);
//        System.out.println(s);
        
        LoginPage.getStated();
        
    }
}
