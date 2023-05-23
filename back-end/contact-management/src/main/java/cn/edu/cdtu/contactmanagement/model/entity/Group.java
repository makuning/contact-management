package cn.edu.cdtu.contactmanagement.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@TableName(value = "group", autoResultMap = true)
public class Group {
    private String id;
    private String name;
    private String userId;
    @JsonIgnore
    private Boolean isDeleted;
}
