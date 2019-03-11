package string;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-07-16 9:54
 */
public class StringTest {

    @Test
    public void testSplit() {
        String s = "（2014）渝二中法民终字第01961号##1418572800000####010070d4cf26a7b672986452ce56479622a####MSAJ_ESAJ##裁定书";
        String s2 = "（2014）渝二中法民终字第01961号######010070d4cf26a7b672986452ce56479622a######";
        String s3 = "######010070d4cf26a7b672986452ce56479622a######";
        String s1 = "（2013）万法民初字第07510号############jdfjk";
        String s4 = "2017888";
        String[] split = s4.split("##", 7);
        System.out.println(split.length);
        for (String str : split) {
            System.out.println(str.toString());
        }
    }


    @Test
    public void testlslsSplit() {
        String s1 = "##律师";
        String s2 = "律师##";
        String[] split = s1.split("##", 2);
        String[] split1 = s2.split("##", 2);

        System.out.println(split.length + "==" + split[0] + "==" + split[1]);
        System.out.println(split1.length + "==" + split1[0] + "==" + split1[1]);
    }

    @Test
    public void remove() {
        String m = "1910275.22元";
        String y = StringUtils.replace(m, "元", "");
        System.out.println(y);

        String substring = StringUtils.substring(m, m.length() - 1, m.length());
        System.out.println(substring);

        String format = String.format("%.2f", 1910275.2);
        System.out.println(format);
    }

    @Test
    public void testDouble() {
        Double d = 0.0;
        if (0 == d) {
            System.out.println("==");
        }
    }

    @Test
    public void testTrim() {
        String t = " ";
        System.out.println(StringUtils.isNotEmpty(t));
        System.out.println(StringUtils.isNotEmpty(t.trim()));
        System.out.println("==" + t.trim() + "==");
    }

    @Test
    public void testIndex() {
        String ft = "《中华人民共和国民事诉讼法》第一百五十四条第一款第（二）项";
        int start = ft.indexOf("《");
        int end = ft.indexOf("》");
        ft = ft.substring(start, end + 1);
        System.out.println(ft);
    }

    @Test
    public void testIn() {
        String[] s = {"1", "2"};
    }

    @Test
    public void testSub() {

        System.out.println(normalizeLSDW("广东世××律师事务所"));
        System.out.println(normalizeLSDW("X"));
        System.out.println(normalizeLSDW("×"));
        System.out.println(normalizeLSDW("某"));
        System.out.println(normalizeLSDW("x"));
        System.out.println(normalizeLSDW("＊"));
        System.out.println(normalizeLSDW("的房价肯定： 冒号"));
        System.out.println(normalizeLSDW("dfd （实习）实习"));
        System.out.println(normalizeLSDW("引号“"));
        System.out.println(normalizeLSDW("引号”"));
        System.out.println(normalizeLSDW("引号\""));
        System.out.println(normalizeLSDW(null));
    }

    //预编译的正则表达式
    private static Pattern pp1 = Pattern.compile("([:：]|（实习）)(?<ls>.*)");
    private static Pattern pp2 = Pattern.compile("[X×某x＊]");
    private static Pattern pp3 = Pattern.compile("[\"”“]");

    /**
     * 规范化律所名称
     * @return
     */
   /* public String normalizeLSDW(String lsdw) {
        // 有问题的律所名称
        if (lsdw.contains("X") || lsdw.contains("×") || lsdw.contains("某") || lsdw.contains("x") || lsdw.contains("＊")) {
            return null;
        }

        if (lsdw.contains(":")) {
            lsdw = lsdw.substring(lsdw.indexOf(":") + 1, lsdw.length());
        }

        if (lsdw.contains("：")) {
            lsdw = lsdw.substring(lsdw.indexOf("：") + 1, lsdw.length());
        }

        if (lsdw.contains("（实习）")) {
            lsdw = lsdw.substring(lsdw.indexOf("（实习）") + "（实习）".length(), lsdw.length());
        }

        if (lsdw.contains("(实习)")) {
            lsdw = lsdw.substring(lsdw.indexOf("(实习)") + "(实习)".length(), lsdw.length());
        }

        if (lsdw.contains("\"")) {
            lsdw = lsdw.replace("\"", "");
        }
        if (lsdw.contains("“")) {
            lsdw = lsdw.replace("“","");
        }
        if (lsdw.contains("”")) {
            lsdw = lsdw.replace("”", "");
        }
        return lsdw;
    }
   */

    /**
     * 规范化律所名称
     *
     * @return
     */
    public String normalizeLSDW(String lsdw) {
        // 有问题的律所名称
        if (pp2.matcher(lsdw).find()) {
            return null;
        }
        Matcher mc1 = pp1.matcher(lsdw);

        if (mc1.find()) {
            lsdw = mc1.group("ls");
        }

        lsdw = pp3.matcher(lsdw).replaceAll("");
        return lsdw;
    }


    @Test
    public void testLong() {
        System.out.println(Long.valueOf(""));
    }

    @Test
    public void testHashCode() {
        System.out.println("hhh".hashCode());
        System.out.println("gdejicbegh".hashCode());
    }

    @Test
    public void testSet() {
        HashSet<String> strings = new HashSet<>();
        strings.add("djfld");
        strings.add("efijf");

        System.out.println(strings.toString());
    }

    @Test
    public void testn() {
        String s = "jkdjfdj\n";
        System.out.println(s.replaceAll("\n", ""));

    }

    @Test
    public void testLineseptor() {
        String line = "广州市交通委员会关于印发进一步规范城市轨道交通临时停止运营服务\n" +
                "管理的通知";
        String line1 = "广州市交通委员会关于印发进一步规范城市轨道交通临时停止运营服务" +
                "管理的通知";
        String s = DigestUtils.md5Hex(line);
        String s1 = DigestUtils.md5Hex(line1);
        System.out.println(s);
        System.out.println(s1);
    }

}
