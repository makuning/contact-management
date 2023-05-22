package cn.edu.cdtu.contactmanagement.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 用户表实体类
 * @Author makun
 * @Date 2023/5/22 11:29
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class User {
    @TableId(value = "id")
    private String id;
    private String username;
    private String password;
    private Long contactId;
    private Boolean isDeleted;
}
