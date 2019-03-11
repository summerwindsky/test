package hbase;

import hbase.xml.ResourcePath;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-05-09 13:38
 */
public class XmlUtil {

    public static Document readXML(String filePath) throws Exception {

        // 创建saxReader对象
        SAXReader reader = new SAXReader();
        // 通过read方法读取一个文件 转换成Document对象
        Document document = reader.read(buildFile(filePath));
//        //获取根节点元素对象
//        Element node = document.getRootElement();

        return document;
    }

    /**
     * 把document对象写入新的文件
     *
     * @param document
     * @throws Exception
     */
    public static void writer(Document document, String path) throws Exception {
        // 紧凑的格式
        // OutputFormat format = OutputFormat.createCompactFormat();
        // 排版缩进的格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        // 设置编码
        format.setEncoding("UTF-8");
        // 创建XMLWriter对象,指定了写出文件及编码格式
        // XMLWriter writer = new XMLWriter(new FileWriter(new
        // File("src//a.xml")),format);
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(
                new FileOutputStream(buildFile(path)), "UTF-8"), format);
        // 写入
        writer.write(document);
        // 立即写入
        writer.flush();
        // 关闭操作
        writer.close();
    }

    public static File buildFile(String path) {
        return new File(ResourcePath.getClassPath() + File.separator + path);
    }
}
