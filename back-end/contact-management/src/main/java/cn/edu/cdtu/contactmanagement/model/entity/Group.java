package cn.edu.cdtu.contactmanagement.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 分组表
 * @Author makun
 * @Date 2023/5/23 12:44
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "`group`", autoResultMap = true)
public class Group {
    private String id;          // 群组ID
    private String name;        // 群组名称
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // 表示此字段与JSON互转时，只能被写入
    private String userId;      // 这个群组属于哪个用户的
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // 表示此字段与JSON互转时，只能被写入
    private Boolean isDeleted;  // 逻辑删除
}
