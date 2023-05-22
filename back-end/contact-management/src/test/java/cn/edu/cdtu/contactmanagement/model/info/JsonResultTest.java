package cn.edu.cdtu.contactmanagement.model.info;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonResultTest {

    @Test
    void success() {
        Assertions.assertEquals("-1", JsonResult.ResponseCode.ERROR.toString());
    }
}