package com.xyk.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;

import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 水印工具类
 */
public class WaterMarker {

    // 水印透明度
    private static float alpha = 0.2f;
    // 水印横向位置
    private static int positionWidth = 110;
    // 水印纵向位置
    private static int positionHeight = 123;
    // 水印文字字体
    private static Font font = new Font("微软雅黑", Font.BOLD, 18);
    // 水印文字颜色
//    private static Color color = Color.red;
//    private static Color color =new Color(225,84,114);
    private static Color color = new Color(0, 0, 0);

    /**
     * @param alpha          水印透明度
     * @param positionWidth  水印横向位置
     * @param positionHeight 水印纵向位置
     * @param font           水印文字字体
     * @param color          水印文字颜色
     */
    public static void setImageMarkOptions(float alpha, int positionWidth, int positionHeight, Font font, Color color) {
        if (alpha != 0.0f) WaterMarker.alpha = alpha;
        if (positionWidth != 0) WaterMarker.positionWidth = positionWidth;
        if (positionHeight != 0) WaterMarker.positionHeight = positionHeight;
        if (font != null) WaterMarker.font = font;
        if (color != null) WaterMarker.color = color;
    }

    /**
     * 给图片添加水印图片
     *
     * @param iconPath   水印图片路径
     * @param srcImgPath 源图片路径
     * @param targerPath 目标图片路径
     */
    public static void markImageByIcon(String iconPath, String srcImgPath,
                                       String targerPath) {
        markImageByIcon(iconPath, srcImgPath, targerPath, null);
    }

    /**
     * 给图片添加水印图片、可设置水印图片旋转角度
     *
     * @param iconPath   水印图片路径
     * @param srcImgPath 源图片路径
     * @param targerPath 目标图片路径
     * @param degree     水印图片旋转角度
     */
    public static void markImageByIcon(String iconPath, String srcImgPath,
                                       String targerPath, Integer degree) {
        OutputStream os = null;
        try {

            Image srcImg = ImageIO.read(new File(srcImgPath));

            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 1、得到画笔对象
            Graphics2D g = buffImg.createGraphics();

            // 2、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
            // 3、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
            }

            // 4、水印图片的路径 水印图片一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(iconPath);

            // 5、得到Image对象。
            Image img = imgIcon.getImage();

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

            // 6、水印图片的位置
            g.drawImage(img, positionWidth, positionHeight, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            // 7、释放资源
            g.dispose();

            // 8、生成图片
            os = new FileOutputStream(targerPath);
            ImageIO.write(buffImg, "JPG", os);

            System.out.println("图片完成添加水印图片");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给图片添加水印文字
     *
     * @param logoText   水印文字
     * @param srcImgPath 源图片路径
     * @param targerPath 目标图片路径
     */
    public static void markImageByText(String logoText, String logoData, String srcImgPath,
                                       String targerPath) throws IOException {
        markImageByText(logoText, logoData, srcImgPath, targerPath, null);
    }

    /**
     * 给图片添加水印文字、可设置水印文字的旋转角度
     *
     * @param logoText
     * @param dateTime   水印下面加日期
     * @param srcImgPath
     * @param targerPath
     * @param degree
     */
    public static void markImageByText(String logoText, String dateTime, String srcImgPath,
                                       String targerPath, Integer degree) throws IOException {
        markImageByText(new FileOutputStream(targerPath), new File(srcImgPath), logoText, dateTime, degree);
    }


    public static void markImageByText(OutputStream out, File srcImgFile, String logoText, String dateTime, Integer degree) throws IOException {
            // 1、源图片
            Image srcImg = ImageIO.read(srcImgFile);
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();

// ---------- 增加下面的代码使得背景透明 -----------------
            //buffImg = g.getDeviceConfiguration().createCompatibleImage(srcImg.getWidth(null),srcImg.getHeight(null),Transparency.TRANSLUCENT);
            //g.dispose();
            //g = buffImg.createGraphics();

// ---------- 背景透明代码结束 -----------------


            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
            }
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(font);

            AttributedString ats = new AttributedString(logoText);
            AttributedString ats2 = new AttributedString(dateTime);

                         /* 消除java.awt.Font字体的锯齿 */
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                          /* 消除java.awt.Font字体的锯齿 */
            ats.addAttribute(TextAttribute.FONT, font, 0, logoText.length());
            ats2.addAttribute(TextAttribute.FONT, font, 0, dateTime.length());

            AttributedCharacterIterator iter = ats.getIterator();
            AttributedCharacterIterator iter2 = ats2.getIterator();


            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            //  g.drawString(logoText, positionWidth, positionHeight);
            g.drawString(iter, positionWidth, positionHeight);
            g.drawString(iter2, positionWidth, positionHeight + 34);
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            ImageIO.write(buffImg, "png", out);
    }

}