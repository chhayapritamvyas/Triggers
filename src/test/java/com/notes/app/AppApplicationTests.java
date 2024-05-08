package com.notes.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppApplicationTests {

    @Autowired
    private AppApplication appApplication;

    @Test
    void contextLoads() {
        AppApplication.main(new String[]{});
        Assertions.assertFalse(appApplication.getClass().isLocalClass());
    }

}
