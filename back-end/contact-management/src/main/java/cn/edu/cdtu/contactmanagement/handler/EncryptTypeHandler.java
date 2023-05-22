package cn.edu.cdtu.contactmanagement.handler;

import cn.edu.cdtu.contactmanagement.util.AESUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description 加密处理
 * @Author makun
 * @Date 2023/5/22 15:40
 * @Version 1.0
 */
@Slf4j
public class EncryptTypeHandler extends BaseTypeHandler<String> {
    /**
     * 非空字段加密
     * @param preparedStatement
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String parameter, JdbcType jdbcType) throws SQLException {
        try {
            if (StringUtils.isBlank((String) parameter)) {
                return;
            }
            // todo 加密操作
            String encrypt = AESUtil.encrypt(parameter.toString(), AESUtil.DEFAULT_KEY);
            preparedStatement.setString(i, encrypt);
        } catch (Exception e) {
            log.error("typeHandler加密异常：" + e);
        }
    }

    /**
     * 非空字段解密
     * @param resultSet
     * @param columnName
     * @return
     * @throws SQLException
     */
    @Override
    public String getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String col = resultSet.getString(columnName);
        try {
            if (StringUtils.isBlank(col)) {
                return col;
            }
            // todo 对结果col进行解密操作
            return AESUtil.decrypt(col, AESUtil.DEFAULT_KEY);
        } catch (Exception e) {
            log.error("typeHandler解密异常：" + e);
        }
        return col;
    }

    /**
     * 非空字段加密
     * @param resultSet
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public String getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String col = resultSet.getString(columnIndex);
        try {
            if (StringUtils.isBlank(col)) {
                return col;
            }
            // todo 对结果col进行解密操作
            return AESUtil.decrypt(col, AESUtil.DEFAULT_KEY);
        } catch (Exception e) {
            log.error("typeHandler解密异常：" + e);
        }
        return col;
    }

    /**
     * 可空字段解密
     * @param callableStatement
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public String getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String col = callableStatement.getString(columnIndex);
        try {
            if (StringUtils.isBlank(col)) {
                return col;
            }
            // todo 对结果col进行解密操作
            return AESUtil.decrypt(col, AESUtil.DEFAULT_KEY);
        } catch (Exception e) {
            log.error("typeHandler解密异常：" + e);
        }
        return col;
    }
}
