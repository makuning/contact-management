package cn.edu.cdtu.contactmanagement.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 联系人表
 * @Author makun
 * @Date 2023/5/23 12:46
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "contact", autoResultMap = true)
public class Contact {
    private String id;
    private String name;
    private String head;
    private String address;
    private String remark;
    private String userId;
    private String groupId;
    @JsonIgnore
    private Boolean isDeleted;
}
