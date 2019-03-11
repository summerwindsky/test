package io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import regex.DelTagsUtil;

import java.io.File;

public class CommonIoUtil {

    public static void main(String[] args) {
        exec("tb_article_content.csv","tb_article_content.csv.out");
        exec("tb_article_info_xsfb.csv", "tb_article_info_xsfb.csv.out");
    }

    /**
     * 对输入文件进行加工，输出新文件
     * @param path
     * @param destPath
     */
    public static void exec(String path, String destPath) {
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + path;
        String destFilePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + destPath;
        File file = new File(filePath);
        File destFile = new File(destFilePath);

        try {
            LineIterator it = FileUtils.lineIterator(file, "UTF-8");
            try {
                while (it.hasNext()) {
                    String line = it.nextLine();
                    FileUtils.writeStringToFile(destFile, DelTagsUtil.delHtmlTags(line) + System.getProperty("line.separator"),true);

                    // do something with line
                }
            } finally {
                LineIterator.closeQuietly(it);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
