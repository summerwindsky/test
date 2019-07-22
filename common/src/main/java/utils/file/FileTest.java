package utils.file;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-09-18 10:40
 */
public class FileTest {

    @Test
    public void testFile() {
        try {
            FileUtils.writeStringToFile(new File("d://tets.txt"), "dfjldjfj" + System.getProperty("line.separator"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExist() {

        try {
//            URL url = Thread.currentThread().getContextClassLoader().getResource("");
//            File file = new File(url.getFile());
//            String classPath = file.getCanonicalPath();
//            ClassPathResource resource = new ClassPathResource(classPath);
//            InputStream inputStream = resource.getInputStream();

            List<String> strings = FileUtils.readLines(new File("D:\\workspace\\test\\common\\src\\main\\resources\\FILE.TXT"));

            Set<String> set = new HashSet<>();
            set.addAll(strings);

            File fileN = new File("D:\\data\\work\\工作任务\\fb15\\数据\\0530\\1");
            File[] files = fileN.listFiles();
            Arrays.stream(files).forEach(file1 -> {
                String name = file1.getName();

                String rowkey = name;
                if (name.contains("doc") || name.contains("htm")) {
                    rowkey = name.replaceAll("-", "/");
                }

                if (!set.contains(rowkey)) {
                    System.out.println(name);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testComp() {
//        002eb34f52de9a9a16f7eada15583361, bh:62a8cda3296977f819585640f084c0ca
//        0035e8f297c598c2b34948cb5bab3b0a, bh:9ccae92b67e1affe5b9197ec5faf329a
//        0024bd5f29621aaabdc84bd75a236288, bh:fec3c285f7ed4b518f51d389359fe707
        try {
            List<String> list1 = FileUtils.readLines(new File("D:\\workspace\\test\\common\\src\\main\\resources\\compareFile\\1.txt"));
            List<String> list2 = FileUtils.readLines(new File("D:\\workspace\\test\\common\\src\\main\\resources\\compareFile\\2.txt"));

            System.out.println(list1.size());
            System.out.println(list2.size());

            boolean b = list2.removeAll(list1);
            list2.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
