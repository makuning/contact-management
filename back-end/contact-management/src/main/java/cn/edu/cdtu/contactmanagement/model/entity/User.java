package cn.edu.cdtu.contactmanagement.model.entity;

import cn.edu.cdtu.contactmanagement.handler.EncryptTypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@TableName(value = "`user`", autoResultMap = true)
public class User {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // 表示此字段与JSON互转时，只能被写入
    private String id;     // 用户ID
    @TableField(typeHandler = EncryptTypeHandler.class)     // 自定义加密解密处理
    private String username;    // 用户名
    @TableField(typeHandler = EncryptTypeHandler.class)     // 自定义加密解密处理
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // 表示此字段与JSON互转时，只能被写入
    private String password;    // 密码
    private String contactId;   // 该用户关联的自己的联系信息
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // 表示此字段与JSON互转时，只能被写入
    private Boolean isDeleted;  // 逻辑删除
}
