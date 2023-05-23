package cn.edu.cdtu.contactmanagement.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String id;
    private String value;
    private String contactId;
    @JsonIgnore
    private Boolean isDeleted;
}
