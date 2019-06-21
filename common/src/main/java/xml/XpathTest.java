package xml;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-07-14 11:12
 */
public class XpathTest {

    private String name = "/output.xml";

    Element rootElement;
    Document document = null;

    @Before
    public void before() {

        // 创建saxReader对象
        SAXReader reader = new SAXReader();
        // 通过read方法读取一个文件 转换成Document对象
        try {
            document = reader.read(new File(ResourcePath.getClassPath() + name));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //获取根节点元素对象
        rootElement = document.getRootElement();


    }

    @Test
    public void testGetElement() {
//        List<Attribute> list = rootElement.selectNodes("/writ/QW/DSR/DLR[SSCYR[@value='王瑞']]/DLDXJH/DLDX/@value");
        List<Attribute> list = rootElement.selectNodes("/writ/QW/DSR/DLR[SSCYR[@value='蔡军']]/BHDX/@value");


//        List<Attribute> list = rootElement.selectNodes("/writ/QW/DSR/DLR[DWZWFZ[DWMC[@value='南京正义法律服务所']]]/DLDXJH/DLDX/@value");

//        List<Attribute> att = rootElement.selectNodes("/writ/QW/PJJG/SSFCD/SSFCDJL/SSFCDFZ/CDR/CDRDW/@value");
        for (Attribute a : list) {
            Element node = (Element) rootElement.selectSingleNode("/writ/QW/DSR/*[SSCYR[@value='" + a.getValue() + "']]");
            System.out.println(a.getValue());
            System.out.println(node.attributeValue("nameCN"));
        }

//        List<Attribute> attributes = rootElement.selectNodes("/writ/QW/DSR/DLR[SSCYR[@value='周文辉']]/DLDXJH/DLDX/@value");
//        for (Attribute attribute : attributes) {
//            System.out.println(attribute.getValue());
//        }

//        Node node = rootElement.selectSingleNode("/writ/QW/DSR/*[SSCYR[@value='江苏天泓汽车服务有限公司']]/@nameCN");
//        System.out.println(node.getText());

    }


    @Test
    public void testEle() {
//        List<Attribute> list = rootElement.selectNodes("/writ/QW/QSXX/AJYLYSLJGD/QSAH/@value");
//        List<Attribute> list = rootElement.selectNodes("/writ/QW/QSXX/AJYLYSLJGD/QSFY/BZFYMC@value");
//        List<Attribute> list = rootElement.selectNodes("/writ/QW/QSXX/AJYLYSLJGD/QSFY/XZQH_P/@value | /writ/QW/QSXX/AJYLYSLJGD/QSFY/XZQH_P | /writ/QW/QSXX/AJYLYSLJGD/QSFY");
//        List<Attribute> list = rootElement.selectNodes("/writ/CUS_CPWS_CPFXGC/CUS_FZ_YS_MSZZ/CUS_ZYJD|/writ/CUS_CPWS_CMSSD/CUS_FZ_YS_MSZZ/CUS_ZYJD|/writ/CUS_ESCPWS_QSSLD/CUS_FZ_YS_MSZZ/CUS_ZYJD|/writ/CUS_ESCPWS_BSSLD/CUS_FZ_YS_MSZZ/CUS_ZYJD|/writ/CUS_ESCPWS_CPFXGC/CUS_FZ_YS_MSZZ/CUS_ZYJD");
//        List<Element> list1 = rootElement.selectNodes("//ys");

        String values = XmlUtil.getValues(document, "(/writ/QW/QSXX/AJYLYSLJGD/QSFY/XZQH_P | /writ/QW/QSXX/AJYLYSLJGD/QSFY)");
        System.out.println(values);


//        System.out.println(list.size());
//        System.out.println(list1.size());
//        for (Attribute attribute : list) {
//            System.out.println(attribute.getValue());
//        }
    }

    @Test
    public void testYsCode() {
        Map<String, Pair> codes = new HashMap<String, Pair>();

        long count = 0;
        File file = new File(ResourcePath.getClassPath() + "/xs");
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                Document document = XmlUtil.readXml(file1);
                Element rootElement = document.getRootElement();
                List<Element> list1 = rootElement.selectNodes("//ys");
                for (Element element : list1) {
                    count++;
                    String code = element.attributeValue("code");
                    final String name = element.attributeValue("name");
                    if (StringUtils.isEmpty(code)) {
                        System.out.println("空");
                    }
                    if (codes.containsKey(code)) {
                        Pair pair = codes.get(code);
                        Long aLong = pair.getCount() + 1;
                        Set<String> names = pair.getNames();
                        names.add(name);
                        pair.setCount(aLong);
                        pair.setNames(names);
                        codes.put(code, pair);

                    } else {

                        codes.put(code, new Pair(1L, new HashSet<String>(){{
                            add(name);
                        }
                        }));

                    }
                }
            }
        }

        System.out.println(count);
        System.out.println(codes.size());

        for (String s : codes.keySet()) {
            Pair pair = codes.get(s);
            if (pair.getCount() > 1) {
                System.out.println(s + "==" + pair.getNames().toString());
            }
        }
    }

    class Pair {
        private Long count;
        private Set<String> names;

        public Pair(Long count, Set<String> names) {
            this.count = count;
            this.names = names;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }

        public Set<String> getNames() {
            return names;
        }

        public void setNames(Set<String> names) {
            this.names = names;
        }
    }

}
