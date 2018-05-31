package com.ryan.baidu.pattern;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hongyongming on 2016/6/13.
 */
public class PatternTest {

    public static void main(String[] args) {

        testWxOAuth();
    }

    private void test1(){
        Pattern Pattern_GUID = Pattern.compile(".*\\[(.{36})\\].*");
        String reInfo = "新增申报成功[4CDE1CFD-EDED-46B1-946C-B8022E42FC9Q]";
        Matcher matcher = Pattern_GUID.matcher(reInfo);
        if(matcher.find()){
            System.out.println(matcher.group(1));
        }else {
            System.out.println("NOT FOUND~");
        }
    }

    private  static  void testWxOAuth(){
         Pattern oauthRedirectPatter = Pattern.compile("(\\?|&)?code=(?<code>\\w+?)&state=STATE_REDIRECT");

         List<String> urls = genUrls();
         for (String url : urls){
             System.out.println("【Test】:"+url);
             Matcher m = oauthRedirectPatter.matcher(url);
             if(m.find()){
                 System.out.println("code："+m.group("code"));
                 String newUrl = m.replaceAll("");
                 System.out.println("newUrl："+newUrl);
             }else {
                 System.out.println("error");
             }


         }

    }
    private static List<String> genUrls(){
        return Arrays.asList("/action/story/page/main?code=001cH0La0E618x1b1PIa0dKVKa0cH0Lq&state=STATE_REDIRECT",
                "/action/story/page/main?code=021A2VAH1OWAJ80PHmEH1t7hBH1A2VAO&state=STATE_REDIRECT",
                "/action/story/page/main?code=071zJSc12U6B8Y0NNsc123nUc12zJScG&state=STATE_REDIRECT",
                "/action/story/page/main?code=061QV8gg2IgOIB0tzIhg28Xdgg2QV8gp&state=STATE_REDIRECT",
                "/action/story/page/main?code=011RqFFb0kwsVt1liVHb0C4EFb0RqFFI&state=STATE_REDIRECT",
                "/action/story/page/main?code=081bkYgT0UjBuW13WxgT0C85hT0bkYgW&state=STATE_REDIRECT",
                "/action/story/page/main?code=011IMfBi112v8w0QH9Bi1S70Bi1IMfBf&state=STATE_REDIRECT",
                "/action/story/page/share?from=groupmessage&isappinstalled=0&code=0115hzbd2xbt2D05fsdd2CDjbd25hzbn&state=STATE_REDIRECT",
                "/action/story/page/main?code=081fiCO92uwQPR0AtxL927TAO92fiCOM&state=STATE_REDIRECT",
                "/action/story/page/main?code=071CR9AD0axFJc2RG7AD0CgtAD0CR9Aq&state=STATE_REDIRECT",
                "/action/story/page/main?code=061JHXIF0hwR7j24taGF0UyGIF0JHXIM&state=STATE_REDIRECT",
                "/action/story/page/main?code=061Gw4CR1ZsZX918TKCR1IGcCR1Gw4CT&state=STATE_REDIRECT",
                "/action/story/page/main?code=0813pZr52IRRcK0DOiu52fd6s523pZrz&state=STATE_REDIRECT",
                "/action/story/page/main?spm=m425777463340064768.subscribe&code=021nJeGC06mAXc2L23KC0t3RFC0nJeGR&state=STATE_REDIRECT",
                "/action/story/page/main?code=011yQVJ50ZwdIK1z6HJ50RqZJ50yQVJi&state=STATE_REDIRECT",
                "/action/story/page/main?openid=ow6v_vluUJOFG3iyXj5k_trr8dQo&spm=m423868230747062272.subscribe&code=0818WLyb1aws3u0zbtzb1ZKxyb18WLy0&state=STATE_REDIRECT",
                "/action/story/page/main?openid=ow6v_vluUJOFG3iyXj5k_trr8dQo&spm=m423868230747062272.subscribe&code=081Fw8qj2xfQRF0Tkpsj2xikqj2Fw8qQ&state=STATE_REDIRECT",
                "/action/story/page/main?code=001Pyx2m1GfiYi0wk45m1UCv2m1Pyx2S&state=STATE_REDIRECT",
                "/action/story/page/main?code=071BOIkd0ypg2u1jxSid0ZwJkd0BOIkW&state=STATE_REDIRECT",
                "/action/story/page/main?code=081Wdopl0tnGdk1Ex6ol0Wotpl0WdopA&state=STATE_REDIRECT",
                "/action/story/page/main?code=0719yC2d0bjBSu10LU0d0ZWt2d09yC2i&state=STATE_REDIRECT",
                "/action/story/page/main?code=001FRNER1x2H0a1tQkDR192JER1FRNEz&state=STATE_REDIRECT",
                "/action/story/page/main?code=0113sLUV0MKN0W1TnpWV0yxMUV03sLUK&state=STATE_REDIRECT",
                "/action/story/page/main?code=001x0tUB1xD57f0HtnUB1cutUB1x0tUb&state=STATE_REDIRECT",
                "/action/story/page/main?code=081r8B4i2586VI0dU97i2THD4i2r8B4V&state=STATE_REDIRECT",
                "/action/story/page/main?code=081mjNX90YIlDv1tFzX905y6Y90mjNX4&state=STATE_REDIRECT",
                "[2018-03-20 22:45:09][http-nio-8880-exec-18][INFO ][] WxUserInfoService.getWxUserInfoFromWx - wx user entity data:o0mtYwfGOO8Uo1j0y5wEWpzMjmuA-ow6v_vklcLCpIvDh7khbiygVIJGY",
                "/action/story/page/main?code=0615jUv12D5IiX0qYMt125lMv125jUvl&state=STATE_REDIRECT",
                "/action/story/page/main?code=071w6gYc2QJVGC0hENZc2iM0Yc2w6gYT&state=STATE_REDIRECT",
                "/action/story/page/main?code=071w6gYc2QJVGC0hENZc2iM0Yc2w6gYT&state=STATE_REDIRECT",
                "/action/story/page/main?code=071w6gYc2QJVGC0hENZc2iM0Yc2w6gYT&state=STATE_REDIRECT",
                "/action/story/page/main?code=0116BuVe2LPfnB0MGGVe28ilVe26BuVV&state=STATE_REDIRECT",
                "/action/story/page/main?code=001GPsIr118aEo0ujmHr1WNSIr1GPsI1&state=STATE_REDIRECT",
                "/action/story/page/main?code=071M6swf1jEv9z0Avswf1F9Cwf1M6sw2&state=STATE_REDIRECT",
                "/action/story/page/main?code=081DKRn61BiIqS1ayjn61uB7o61DKRna&state=STATE_REDIRECT",
                "/action/story/page/main?code=0013hK022lranX085S022gKk0223hK0X&state=STATE_REDIRECT",
                "/action/story/page/main?code=()&state=STATE_REDIRECT",
                "/action/story/page/main?code=011IMfBi112v8w0QH9Bi1S70Bi1IMfBf&state=STATE_REDIRECT",
                "/action/story/page/main?code=011IMfBi112v8w0QH9Bi1S70Bi1IMfBf&state=STATE_REDIRECT",
                "/action/story/page/main?code=011IMfBi112v8w0QH9Bi1S70Bi1IMfBf&state=STATE_REDIRECT",
                "/action/story/page/main?code=011IMfBi112v8w0QH9Bi1S70Bi1IMfBf&state=STATE_REDIRECT",
                "/action/story/page/main?code=011IMfBi112v8w0QH9Bi1S70Bi1IMfBf&state=STATE_REDIRECT",
                "/action/story/page/main?code=011IMfBi112v8w0QH9Bi1S70Bi1IMfBf&state=STATE_REDIRECT",
                "/action/story/page/main?code=011IMfBi112v8w0QH9Bi1S70Bi1IMfBf&state=STATE_REDIRECT",
                "/action/story/page/main?code=011IMfBi112v8w0QH9Bi1S70Bi1IMfBf&state=STATE_REDIRECT",
                "/action/story/page/main?code=011IMfBi112v8w0QH9Bi1S70Bi1IMfBf&state=STATE_REDIRECT",
                "/action/story/page/main?code=011IMfBi112v8w0QH9Bi1S70Bi1IMfBf&state=STATE_REDIRECT",
                "/action/story/page/main?code=011xXQR21cl03P1WoxT21RrBR21xXQR7&state=STATE_REDIRECT",
                "/action/story/page/main?code=021nkxai199NYv0Lgnbi1pinai1nkxa8&state=STATE_REDIRECT",
                "/action/story/page/main?code=011dm9lW0qAD9W1P5alW0OyclW0dm9lk&state=STATE_REDIRECT",
                "/action/story/page/main?code=001Ep3Df1tw68z0zDpCf1ETbDf1Ep3DP&state=STATE_REDIRECT",
                "/action/story/page/share?code=061xNpxy0rm9jg1khcwy0pJ2xy0xNpxj&from=groupmessage&isappinstalled=0&state=STATE_REDIRECT",
                "/action/story/page/main?code=081PQP5n1U5Vsi0YWS7n1cO16n1PQP5K&state=STATE_REDIRECT",
                "/action/story/page/main?code=011dAunG00oycl25g2pG0yhenG0dAunq&state=STATE_REDIRECT",
                "/action/story/page/main?code=071u7fpb1FBtCt0Rfoob14Zhpb1u7fpZ&state=STATE_REDIRECT",
                "/action/story/page/main?code=071tX0aO0itEFa2Did9O0D0N9O0tX0ae&state=STATE_REDIRECT",
                "/action/story/page/main?code=0614P3EQ1AYvI81VqNEQ1Z1fEQ14P3Ex&state=STATE_REDIRECT",
                "/action/story/page/main?code=011RIRdm1GgiRi0BHYdm1vPOdm1RIRdo&state=STATE_REDIRECT",
                "/action/story/page/main?code=021uwxjh2DlV1I0l5lih2SSyjh2uwxjH&state=STATE_REDIRECT",
                "/action/story/page/main?code=021a1RJ00FC0iE1BnXK00uMKJ00a1RJn&state=STATE_REDIRECT",
                "/action/story/page/main?code=011K3ema0ESG0x1hfela0m19ma0K3emP&state=STATE_REDIRECT",
                "/action/story/page/main?code=071T3XsZ1vULmZ0sowrZ19vSsZ1T3Xs9&state=STATE_REDIRECT",
                "/action/story/page/main?code=061JBOoY0qqbC22RQMoY0DH3pY0JBOoQ&state=STATE_REDIRECT",
                "/action/story/page/main?code=061yN7lH070pnh2MqqjH069UkH0yN7lH&state=STATE_REDIRECT",
                "/action/story/page/main?code=061PKxu41snc9L14hvw41pXPu41PKxu6&state=STATE_REDIRECT",
                "/action/story/page/main?code=011ahV7W1KYssW0B5s5W1MrU7W1ahV7R&state=STATE_REDIRECT",
                "/action/story/page/main?code=0010m3W90NxLBv1jK9Z90PMgW900m3Wy&state=STATE_REDIRECT",
                "/action/story/page/main?code=001uZEwY1M0G1U0zHvwY1BBtwY1uZEwP&state=STATE_REDIRECT",
                "/action/story/page/main?code=021iv9JX0V5p5124s7KX0WU3JX0iv9J7&state=STATE_REDIRECT",
                "/action/story/page/main?code=061CCpDT1XFPr71QtIAT1ZwdDT1CCpDY&state=STATE_REDIRECT",
                "/action/story/page/main?openid=ow6v_vmk7tsqQSEfdSCoJrWjEgwM&spm=m407655347294064640.subscribe.youzan_qr_88&code=0218bHRB1RR84f0UpiRB19wJRB18bHRa&state=STATE_REDIRECT",
                "/action/story/page/main?openid=ow6v_vmk7tsqQSEfdSCoJrWjEgwM&spm=m407655347294064640.subscribe.youzan_qr_88&code=001rk4M10qMjtF1BjvO10p3iM10rk4Mg&state=STATE_REDIRECT",
                "/action/story/page/main?code=001ehP0z1d0MSh0IoN4z1b6B0z1ehP04&state=STATE_REDIRECT",
                "/action/story/page/main?code=021cUjps1zJdVn0Hnsts1mJkps1cUjpB&state=STATE_REDIRECT",
                "/action/practice/main?code=001uk8Bt0V3knb1fOLAt0Jt7Bt0uk8BX&state=STATE_REDIRECT",
                "/action/practice/main?code=011qHrhg0VYPoy1hifgg0Hahhg0qHrhj&state=STATE_REDIRECT",
                "/action/story/page/main?code=011OJEX619K2SR12jyU61IgXX61OJEXL&state=STATE_REDIRECT",
                "/action/story/page/main?code=061kSGhP1jTvf3196KiP1EtLhP1kSGhr&state=STATE_REDIRECT",
                "/action/story/page/main?code=001M5BKY0FW8f22yaPKY0znAKY0M5BKq&state=STATE_REDIRECT",
                "/action/story/page/main?code=06199MGk2IFRZG0uc2Fk2iPYGk299MG1&state=STATE_REDIRECT",
                "/action/story/page/main?openid=ow6v_vgheMxFBtG37FokBimYdL3c&spm=m424080508067020800.subscribe&code=011RW1vW0qHQsV1hFPtW0BrnvW0RW1v6&state=STATE_REDIRECT",
                "/action/story/page/main?code=011kxGOl0NnYtl1luOOl0dXpOl0kxGOi&state=STATE_REDIRECT",
                "/action/practice/main?code=0016VmM20XSrMC1zZCO20nLAM206VmML&state=STATE_REDIRECT");
    }

}
