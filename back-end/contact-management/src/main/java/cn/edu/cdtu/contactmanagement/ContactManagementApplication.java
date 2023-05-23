package cn.edu.cdtu.contactmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description 主启动类
 * @Author makun
 * @Date 2023/5/22 10:29
 * @Version 1.0
 */
@SpringBootApplication  // springboot启动类
@MapperScan(basePackages = {"cn.edu.cdtu.contactmanagement.mapper"})    // 配置mapper扫描
public class ContactManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContactManagementApplication.class, args);
    }

}
