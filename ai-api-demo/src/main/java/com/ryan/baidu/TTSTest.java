package com.ryan.baidu;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

import static com.ryan.baidu.BDConts.*;

public class TTSTest {



    public static void main(String[] args) {

        String outFile = "E:\\baidu_test.mp3";
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        TtsResponse res = client.synthesis("呵呵，我就知道，文钊是个有理想、有道德、有文化、有纪律的四有青年,是祖国的脊梁,民族之希望～", "zh", 1, null);
        System.out.println(res.getResult());
        byte[] data = res.getData();

        try {
            Files.write(data,new File(outFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
