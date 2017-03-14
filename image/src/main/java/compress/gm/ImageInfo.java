package compress.gm;

public class ImageInfo {

	private int width;
    private int height;
    private String suffix;
    private double quality;
    private String md5Hex;
 
    public ImageInfo(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ImageInfo(int width, int height, String suffix, double quality) {
        this.width = width;
        this.height = height;
        this.suffix = suffix;
        this.quality = quality;
    }

    public int getWidth() {
        return width;
    }
 
    public int getHeight() {
        return height;
    }

    public String getSuffix() {
        return suffix;
    }

    public double getQuality() {
        return quality;
    }

    // 为了减小图片大小，如果图片质量大于90，则取90
    public double calSuitableQuality(){
        if(quality == 0){
            return 90;
        }
        return quality>90 ? 90 : quality;
    }

    public String getMd5Hex() {
        return md5Hex;
    }

    public void setMd5Hex(String md5Hex) {
        this.md5Hex = md5Hex;
    }

}
