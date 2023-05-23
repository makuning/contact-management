package cn.edu.cdtu.contactmanagement.model.entity;

import cn.edu.cdtu.contactmanagement.handler.EncryptTypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@TableName(value = "user", autoResultMap = true)
public class User {
    private String id;
    @TableField(typeHandler = EncryptTypeHandler.class)
    private String username;
    @TableField(typeHandler = EncryptTypeHandler.class)
    @JsonIgnore
    private String password;
    private String contactId;
    @JsonIgnore
    private Boolean isDeleted;
}
