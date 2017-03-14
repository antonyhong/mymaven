import com.alibaba.fastjson.JSON;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.tencent.gcloud.voice.GCloudVoiceEngine;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by HONGYONGMING on 2017/2/14.
 */
public class TestMain {

    static {
        System.load("E:\\技术中心\\语音服务\\gvoive\\gcloud_voice_winx64_1_1_3_138155_20170104\\Win64\\lib\\GCloudVoice.dll");
    }

    public static void main(String[] args) {
//        GCloudVoiceEngine engine = GCloudVoiceEngine.getInstance();
//        engine.init(null,null);

        List<String> list = new ArrayList<>();


        System.out.println(JSON.toJSON(list));

    }
}
