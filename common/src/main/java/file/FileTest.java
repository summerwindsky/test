package file;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
}
