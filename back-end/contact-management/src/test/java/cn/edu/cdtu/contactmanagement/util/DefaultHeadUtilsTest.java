package cn.edu.cdtu.contactmanagement.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultHeadUtilsTest {

    @Test
    void randomHead() {
        for (int i = 1; i < 100; i++) {
            String head = DefaultHeadUtils.randomHead();
            System.out.println(head);
        }
    }
}