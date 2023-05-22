package cn.edu.cdtu.contactmanagement.mapper;

import cn.edu.cdtu.contactmanagement.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * @Description user表持久层访问
 * @Author makun
 * @Date 2023/5/22 11:42
 * @Version 1.0
 */
public interface UserMapper extends BaseMapper<User> {

}
