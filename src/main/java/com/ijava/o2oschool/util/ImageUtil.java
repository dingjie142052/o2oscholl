package com.ijava.o2oschool.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;

import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;

public class ImageUtil {
//    LoggerFactory.getLogger()
    public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
        String realFileName = FileUtil.getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(200, 200).outputQuality(0.25f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    public static  File transferCommonsMultipartFile2File(CommonsMultipartFile thumbnail){
        File file = new File(thumbnail.getOriginalFilename());
        try {
            thumbnail.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();

        }catch ( IllegalStateException e){
            e.printStackTrace();
        }
        return file;
    }

    public static String generateThumbnail(File thumbnail, String targetAddr) {
        String realFileName = FileUtil.getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail).size(200, 200).outputQuality(0.25f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    public static String generateNormalImg(CommonsMultipartFile thumbnail, String targetAddr) {
        String realFileName = FileUtil.getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(337, 640).outputQuality(0.5f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    public static List<String> generateNormalImgs(List<CommonsMultipartFile> imgs, String targetAddr) {
        int count = 0;
        List<String> relativeAddrList = new ArrayList<String>();
        if (imgs != null && imgs.size() > 0) {
            makeDirPath(targetAddr);
            for (CommonsMultipartFile img : imgs) {
                String realFileName = FileUtil.getRandomFileName();
                String extension = getFileExtension(img);
                String relativeAddr = targetAddr + realFileName + count + extension;
                File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
                count++;
                try {
                    Thumbnails.of(img.getInputStream()).size(600, 300).outputQuality(0.5f).toFile(dest);
                } catch (IOException e) {
                    throw new RuntimeException("创建图片失败：" + e.toString());
                }
                relativeAddrList.add(relativeAddr);
            }
        }
        return relativeAddrList;
    }

    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = FileUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    private static String getFileExtension(CommonsMultipartFile cFile) {
        String originalFileName = cFile.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    private static String getFileExtension(File cFile) {
        String originalFileName = cFile.getName();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }



    private static void setWatermark() throws IOException {
        String realFileName = Thread.currentThread().getContextClassLoader().getResource("").getPath();
       Thumbnails.of(new File("G:/scenery.jpg")).size(200,200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(realFileName+"/doller.png")),0.25f)
       .outputQuality(0.8f).toFile("G:/scenerynew.jpg");
    }
//		String originalFileName = cFile.getOriginalFilename();
//		return originalFileName.substring(originalFileName.lastIndexOf("."));
//		Thumbnails.of(new File(""));


}
