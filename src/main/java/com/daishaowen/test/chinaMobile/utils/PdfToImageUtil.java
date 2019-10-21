/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT China Mobile (SuZhou) Software Technology Co.,Ltd. 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * CMSS Co.,Ltd. The programs may be used and/or copied only with written
 * permission from CMSS Co.,Ltd. or in accordance with the terms and conditions
 * stipulated in the agreement/contract under which the program(s) have been
 * supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.daishaowen.test.chinaMobile.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;


@SuppressWarnings("nls")
public class PdfToImageUtil {

    /**
     * pdf文件首页
     */
    public static final int FIRST_PAGE_NUM = 0;

    /**
     * jpeg格式
     */

    public static final String IMAGE_TYPE_JPEG = "jpeg";

    /**
     * png格式
     */
    public static final String IMAGE_TYPE_PNG = "png";

    /**
     * bmp格式
     */
    public static final String IMAGE_TYPE_BMP = "bmp";

    /**
     * 默认图片分辨率
     */
    public static final int DEFAULT_RESOLUTION = 192;

    /**
     * pdf转换图片
     * @param source pdf文件
     * @param target 图片文件
     * @param imageType 图片类型
     * @param pageNum 页码，从0开始
     * @param resolution 分辨率
     * @throws IOException
     */
    @SuppressWarnings("resource")
    public static void pdfToImage(final String source, final String target, final String imageType, final int pageNum,
            final int resolution) throws IOException {

        // 加载PDF
        final PDDocument doc = PDDocument.load(source);
        // 获取需转换的页数
        final PDPage page = (PDPage) doc.getDocumentCatalog().getAllPages().get(pageNum);
        // 转换为图片
        final BufferedImage bufferedImage = page.convertToImage(BufferedImage.TYPE_INT_RGB, resolution);
        // 输出文件流
        final OutputStream out = new FileOutputStream(target);
        // 存储图片
        ImageIO.write(bufferedImage, imageType, out);
        // 关闭操作
        out.close();
        doc.close();

    }

    /**
     * pdf转换图片
     * @param input
     * @return InputStream
     * @throws IOException
     */
    public static InputStream pdfToImage(final InputStream input) throws IOException {

        return PdfToImageUtil.pdfToImage(input, IMAGE_TYPE_JPEG, FIRST_PAGE_NUM, DEFAULT_RESOLUTION);

    }

    /**
     * pdf转换图片
     * @param input
     * @param imageType 图片类型
     * @param pageNum 页码，从0开始
     * @param resolution 分辨率
     * @return InputStream
     * @throws IOException
     */
    @SuppressWarnings("resource")
    public static InputStream pdfToImage(final InputStream input, final String imageType, final int pageNum,
            final int resolution) throws IOException {

        // 加载PDF
        final PDDocument doc = PDDocument.load(input);
        // 获取需转换的页数
        final PDPage page = (PDPage) doc.getDocumentCatalog().getAllPages().get(pageNum);
        // 转换为图片
        final BufferedImage bufferedImage = page.convertToImage(BufferedImage.TYPE_INT_RGB, resolution);
        // 获取input流
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, imageType, baos);
        final InputStream is = new ByteArrayInputStream(baos.toByteArray());
        // 关闭操作
        doc.close();
        baos.close();
        input.close();

        return is;
    }

}
