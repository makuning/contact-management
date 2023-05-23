package cn.edu.cdtu.contactmanagement.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String id;      // 联系人ID
    private String name;    // 联系人姓名
    private String head;    // 联系人头衔
    private String address; // 联系人地址
    private String remark;  // 联系人备注
    private String userId;  // 这个联系人属于哪个用户的
    private String groupId; // 这个联系人属于哪个组的
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // 表示此字段与JSON互转时，只能被写入
    private Boolean isDeleted;  // 逻辑删除
}
