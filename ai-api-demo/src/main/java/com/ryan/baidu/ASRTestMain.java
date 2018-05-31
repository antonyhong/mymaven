package com.ryan.baidu;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;

import static com.ryan.baidu.BDConts.API_KEY;
import static com.ryan.baidu.BDConts.APP_ID;
import static com.ryan.baidu.BDConts.SECRET_KEY;

public class ASRTestMain {

    public static void main(String[] args) {

        String file = "E:\\小云熊\\需求\\20180524 喜马拉雅\\test_60.wav";
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60*1000*5);


        JSONObject res = client.asr(file, "wav", 16000, null);
        System.out.println(res.toString(2));

    }
}
