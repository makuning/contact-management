package cn.edu.cdtu.contactmanagement.model.info;

import cn.edu.cdtu.contactmanagement.type.ResponseType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonResultTest {

    @Test
    void success() {
        Assertions.assertEquals("-1", ResponseType.ERROR.toString());
    }
}