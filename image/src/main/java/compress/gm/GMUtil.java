package compress.gm;

import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.GMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.process.ArrayListOutputConsumer;
import org.im4java.process.Pipe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author yezhiwei
 * IM4JAVA通过命令行的方式调用GraphicsMagick(GM)
 * 这里是对IM4JAVA的封装
 * 使用该类需安装GraphicsMagick
 *
 * 需要在系统安装GM工具,且需要保证在命令行可以调用（配置环境变量）。
 */
public class GMUtil {

    /**
     * 获取图片长宽、格式信息
     * 这里读出来的图片质量是GM的计算方法算出来的，不同的工具算可能不一样（PhotoShop），而且基本都是75，所以这里可能是个坑
     * @param inputStream   图片的输入流
     * @return
     * @throws Exception
     */
    public static ImageInfo getImageInfo(InputStream inputStream) throws Exception {
        GMOperation op = new GMOperation();
        op.addRawArgs("-format", "%w\n%h\n%m\n%Q\n");
        op.addImage("-");
        IdentifyCmd identifyCmd = new IdentifyCmd(true);
        Pipe pipeIn = new Pipe(inputStream, null);
        identifyCmd.setInputProvider(pipeIn);
        ArrayListOutputConsumer output = new ArrayListOutputConsumer();
        identifyCmd.setOutputConsumer(output);
        identifyCmd.run(op);
        ArrayList<String> cmdOutput = output.getOutput();
        assert cmdOutput.size() >= 4;  //如果是gif的话每一帧都会有值，cmdOutput.size()=帧数*4，取第一帧的值
        return new ImageInfo(Integer.parseInt(cmdOutput.get(0)),
                Integer.parseInt(cmdOutput.get(1)),
                cmdOutput.get(2).toLowerCase(),
                Double.parseDouble(cmdOutput.get(3)));

        //要解码耗时太长
        /*op.verbose();
        op.addImage("-");
        IdentifyCmd identifyCmd = new IdentifyCmd(true);
        Pipe pipeIn = new Pipe(inputStream, null);
        identifyCmd.setInputProvider(pipeIn);
        ArrayListOutputConsumer output = new ArrayListOutputConsumer();
        identifyCmd.setOutputConsumer(output);
        identifyCmd.run(op);
        ArrayList<String> cmdOutput = output.getOutput();
        String format = null;
        int width=0, height=0, jpegQuality=0;
        for(int i=0; i<cmdOutput.size(); i++){
            String line = cmdOutput.get(i).replace(" ", "");
            if(line.startsWith("Format")){
                format = line.substring(line.indexOf(":") + 1, line.indexOf("(")).toLowerCase();
            }else if(line.startsWith("Geometry")){
                String sizeStr = line.substring(line.indexOf(":")+1);
                String[] sizeArr = sizeStr.split("x");
                width = Integer.parseInt(sizeArr[0]);
                height = Integer.parseInt(sizeArr[1]);
            }else if(line.startsWith("JPEG-Quality")){
                jpegQuality = Integer.parseInt(line.substring(line.indexOf(":") + 1));
            }
        }
        return new ImageInfo(width, height, format, jpegQuality);*/
    }

    /**
     * 获取图片长宽、格式信息
     * @param imagePath     图片的路径
     * @return
     * @throws Exception
     */
    public static ImageInfo getImageInfo(String imagePath) throws Exception {
        GMOperation op = new GMOperation();
        op.addRawArgs("-format", "%w\n%h\n%m\n%Q");
        op.addImage(imagePath);
        IdentifyCmd identifyCmd = new IdentifyCmd(true);
        ArrayListOutputConsumer output = new ArrayListOutputConsumer();
        identifyCmd.setOutputConsumer(output);
        identifyCmd.run(op);
        ArrayList<String> cmdOutput = output.getOutput();
        assert cmdOutput.size() == 4;
        return new ImageInfo(Integer.parseInt(cmdOutput.get(0)),
                Integer.parseInt(cmdOutput.get(1)),
                cmdOutput.get(2).toLowerCase(),
                Double.parseDouble(cmdOutput.get(3)));
    }

    /**
     * 保存图片
     * @param inputStream   图片的输入流
     * @param savePath      保存的完整路径
     * @param quality       保存的图片质量，quality取值范围:[0, 100]，值越小图片质量越差，图片硬盘大小越小。
     *                      为null则代表使用原图的质量保存
     * @throws Exception
     */
    public static void saveImage(InputStream inputStream, String savePath, Double quality) throws Exception {
        GMOperation op = new GMOperation();
        if(quality!=null) {
            op.quality(quality);
        }
// else{
        // 这个命令会保留旧的图片的quality sampling-factor 导致图片增大
//            op.addRawArgs("-define", "jpeg:preserve-settings");
//        }

        // 这个参数是把没必要的一些图片信息去掉，指文件头里的信息,这个命令会导致
        //op.addRawArgs("-strip");
        op.addImage("-");
        op.addImage(savePath);

        ConvertCmd convert = new ConvertCmd(true);
        Pipe pipeIn = new Pipe(inputStream, null);
        convert.setInputProvider(pipeIn);

        convert.run(op);
    }

    /**
     * 等比例缩放
     * @param srcImagePath  原图的完整路径
     * @param width         缩放后的宽
     * @param height        缩放后的高
     * @param quality       保存的图片质量，quality取值范围:[0, 100]，值越小图片质量越差，图片硬盘大小越小。
     *                      为null则代表使用原图的质量保存
     * @return
     * @throws Exception
     */
    public static byte[] resize(String srcImagePath, int width, int height, Double quality) throws Exception {
        GMOperation op = new GMOperation();
        // 待处理图片的绝对路径
        op.addImage(srcImagePath);

        // 图片压缩比，有效值范围是0.0-100.0，数值越大，缩略图越清晰
        if(quality!=null) {
            op.quality(quality);
        }else{
            op.addRawArgs("-define", "jpeg:preserve-settings");
        }
        op.addRawArgs("-strip");
        op.resize(width, height);

        // 处理后图片的绝对路径，这里不用保存所以这么写
        op.addImage("-");
        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        Pipe pipeOut = new Pipe(null, fos);

        // true指使用GM，false指使用IM
        ConvertCmd convert = new ConvertCmd(true);
        convert.setOutputConsumer(pipeOut);
        convert.run(op);
        byte[] resizeImgData = fos.toByteArray();
        fos.close();
        return resizeImgData;
    }

    /**
     * 等比例缩放
     * @param srcImagePath      原图的完整路径
     * @param saveImagePath     缩放后的保存路径
     * @param width             缩放后的宽
     * @param height            缩放后的高
     * @param quality           保存的图片质量，quality取值范围:[0, 100]，值越小图片质量越差，图片硬盘大小越小。
     *                          为null则代表使用原图的质量保存
     * @throws Exception
     */
    public static void resize(String srcImagePath, String saveImagePath, int width, int height, Double quality) throws Exception {
        GMOperation op = new GMOperation();
        // 待处理图片的绝对路径
        op.addImage(srcImagePath);

        // 图片压缩比，有效值范围是0.0-100.0，数值越大，缩略图越清晰
        if(quality!=null) {
            op.quality(quality);
        }else{
            op.addRawArgs("-define", "jpeg:preserve-settings");
        }
        op.addRawArgs("-strip");
        op.resize(width, height);

        // 处理后图片的绝对路径，这里不用保存所以这么写
        op.addImage(saveImagePath);

        // true指使用GM，false指使用IM
        ConvertCmd convert = new ConvertCmd(true);
        convert.run(op);
    }

    /**
     * 长图水平切分
     * @param inputStream           长图的输入流
     * @param width                 裁剪的宽，应为长图的宽
     * @param height                裁剪的高
     * @param subImagePathFormat    裁剪后子图的保存全路径的格式，如/home/images/item/aa/bb/123456_%d.suffix
     *                              符合printf风格格式，裁剪成功后子图从0开始保存，如
     *                              /home/images/item/aa/bb/123456_0.suffix
     * @param quality               保存的图片质量，quality取值范围:[0, 100]，值越小图片质量越差，图片硬盘大小越小。
     *                              为null则代表使用原图的质量保存
     * @return                      裁剪是否成功
     */
    public static void cropImage(InputStream inputStream, int width, int height, String subImagePathFormat, Double quality) throws Exception {
        GMOperation op = new GMOperation();
        op.addImage("-");
        /**
         * width：裁剪的宽度
         * height：裁剪的高度
         * x：裁剪左上角的横坐标
         * y：裁剪左上角的纵坐标
         * x和y缺省时会按width*height的大小把整张图片切块
         * 边边的块可能会比裁剪大小要小
         */
        if(quality!=null) {
            op.quality(quality);
        }else{
            op.addRawArgs("-define", "jpeg:preserve-settings");
        }
        op.addRawArgs("-strip");
        op.crop(width, height);
        op.addRawArgs("+adjoin", subImagePathFormat);

        ConvertCmd convert = new ConvertCmd(true);
        Pipe pipeIn = new Pipe(inputStream, null);
        convert.setInputProvider(pipeIn);

        convert.run(op);
    }

    /**
     * 添加显式水印，右下角为锚点
     * @param srcImgPath    原图的完整路径
     * @param wmImgPath     水印图片的完整路径
     * @param saveImgPath   加入水印后图片的保存路径
     * @param margin_width  右下角为锚点，水平方向内边距
     * @param margin_height 右下角为锚点，竖直方向内边距
     * @param quality       保存的图片质量，quality取值范围:[0, 100]，值越小图片质量越差，图片硬盘大小越小。
     *                      为null则代表使用原图的质量保存
     * @throws Exception
     */
    public static void insertVisibleWaterMark(String srcImgPath, String wmImgPath, String saveImgPath,
                                              int margin_width, int margin_height, Double quality) throws Exception {
        File saveImgParentFile = new File(saveImgPath).getParentFile();
        if(!saveImgParentFile.exists()){
            saveImgParentFile.mkdirs();
        }

        GMOperation op = new GMOperation();
        op.addRawArgs("-gravity", "southeast");
        op.addRawArgs("-geometry", "+" + margin_width + "+" + margin_height);

        if(quality!=null) {
            op.quality(quality);
        }else{
            op.addRawArgs("-define", "jpeg:preserve-settings");
        }
        op.addRawArgs("-strip");

        op.addImage(wmImgPath);
        op.addImage(srcImgPath);
        op.addImage(saveImgPath);

        CompositeCmd convert = new CompositeCmd(true);
        convert.run(op);
    }

    public static void main(String[] args){
        try {
            ImageInfo imageInfo = getImageInfo("D:\\speImg\\yingyou.jpg");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
