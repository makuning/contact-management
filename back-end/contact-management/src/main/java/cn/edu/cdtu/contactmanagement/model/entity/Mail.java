package cn.edu.cdtu.contactmanagement.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 邮箱表
 * @Author makun
 * @Date 2023/5/23 12:48
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "mail", autoResultMap = true)
public class Mail {
    private String id;          // 邮箱ID
    private String value;       // 邮箱地址
    private String contactId;   // 属于哪个联系人的
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // 表示此字段与JSON互转时，只能被写入
    private Boolean isDeleted;  // 逻辑删除
}
