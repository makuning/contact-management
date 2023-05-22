package cn.edu.cdtu.contactmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"cn.edu.cdtu.contactmanagement.mapper"})
public class ContactManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactManagementApplication.class, args);
    }

}
