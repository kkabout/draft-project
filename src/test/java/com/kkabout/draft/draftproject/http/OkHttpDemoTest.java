package com.kkabout.draft.draftproject.http;

import com.kkabout.draft.draftproject.DraftProjectApplicationTests;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OkHttpDemoTest extends DraftProjectApplicationTests {

    @Resource
    private OkHttpDemo okHttpDemo;
    @Test
    void doGet() throws IOException {
        okHttpDemo.doGet();
    }

    @Test
    void doGetAsync() {
    }
}