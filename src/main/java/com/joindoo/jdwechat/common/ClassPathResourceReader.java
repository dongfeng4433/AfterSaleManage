package com.joindoo.jdwechat.common;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Created by zhuqiang on 2018/5/18.
 */
public class ClassPathResourceReader {
    private final String path;

    private String content;

    public ClassPathResourceReader(String path) {
        this.path = path;
    }

    public String getContent() {
        if (content == null) {
            try {
                ClassPathResource resource = new ClassPathResource(path);
                BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
                content = reader.lines().collect(Collectors.joining("\n"));
                reader.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return content;
    }
}
