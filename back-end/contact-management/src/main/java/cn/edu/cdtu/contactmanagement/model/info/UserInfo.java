package cn.edu.cdtu.contactmanagement.model.info;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 用户详细信息
 * @Author makun
 * @Date 2023/5/22 13:46
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String id;
    private String username;
    private String password;
    private Long contactId;
    private Boolean isDeleted;
}
