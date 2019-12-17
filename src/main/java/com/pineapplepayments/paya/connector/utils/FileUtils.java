package com.pineapplepayments.paya.connector.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

@Service
@Transactional(readOnly = true)
public class FileUtils {

    public String readByURL(String url) {

        InputStream in;
        String data = null;
        try {
            in = new URL(url).openStream();
            byte[] bdata = FileCopyUtils.copyToByteArray(in);
            data = new String(bdata, StandardCharsets.UTF_8);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return data;
    }
}
