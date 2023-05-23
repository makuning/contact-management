package cn.edu.cdtu.contactmanagement.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 电话表
 * @Author makun
 * @Date 2023/5/23 12:49
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "phone", autoResultMap = true)
public class Phone {
    private String id;          // 手机ID
    private String value;       // 手机号码
    private String source;      // 手机归属地
    private String contactId;   // 手机属于哪个联系人
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // 表示此字段与JSON互转时，只能被写入
    private Boolean isDeleted;  // 逻辑删除
}
