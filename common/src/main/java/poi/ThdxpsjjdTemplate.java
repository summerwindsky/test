package poi;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Title: DealWritRelationHandler
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author tianxiupeng
 * @version 1.0
 * @date 2019/1/3
 */
public class ThdxpsjjdTemplate {

    private static final Logger logger = LoggerFactory.getLogger(ThdxpsjjdTemplate.class);

    private static boolean hasDealTable = false;

    public static void main(String[] args) {
        String filePath = "D://test.docx";
        Document templateDoc = XmlUtil.readXml("D://jcw/01WTXSCZ_CPB_AJJDGLS.xml");
        FileInputStream in = null;
        XWPFDocument xwpf = null;
        try {
            in = new FileInputStream(filePath);
            xwpf = new XWPFDocument(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ThdxpsjjdTemplate thdxpsjjdTemplate = new ThdxpsjjdTemplate();
        thdxpsjjdTemplate.init(templateDoc, xwpf);
        XmlUtil.WriteXml("d://testout.xml", templateDoc);
    }

    public void init(Document templateDoc, Object object) {
        XWPFDocument xwpf = (XWPFDocument) object;
        xwpf.getBodyElements().forEach((IBodyElement iBodyElement) -> {
            //处理段落
            if (iBodyElement.getElementType().equals(BodyElementType.PARAGRAPH)) {
                XWPFParagraph xwpfParagraph = (XWPFParagraph) iBodyElement;
                String text = xwpfParagraph.getText();
                System.out.println();

                if (hasDealTable) {
//                    Attribute attr = (Attribute) poi.XmlUtil.getSingleNode(templateDoc, "/writ/QW/WW[@value]");
//                    Attribute attrO = (Attribute) poi.XmlUtil.getSingleNode(templateDoc, "/writ/QW/WW[@oValue]");
                    Element node = (Element)templateDoc.selectSingleNode("/writ/QW/WW");
                    Attribute attr = node.attribute("value");
                    Attribute attrO = node.attribute("oValue");
                    String oldValue = attr.getValue();
                    String newValue = oldValue + text;
                    attr.setValue(newValue);
                    attrO.setValue(newValue);
                } else {
//                    Attribute attr = (Attribute) poi.XmlUtil.getSingleNode(templateDoc, "/writ/QW/WS[@value]");
//                    Attribute attrO = (Attribute) poi.XmlUtil.getSingleNode(templateDoc, "/writ/QW/WS[@oValue]");
                    Element node = (Element)templateDoc.selectSingleNode("/writ/QW/WS");
                    Attribute attr = node.attribute("value");
                    Attribute attrO = node.attribute("oValue");
                    String oldValue = attr.getValue();
                    String newValue = oldValue + text;
                    attr.setValue(newValue);
                    attrO.setValue(newValue);
                }
            }
            ;
            //处理表格
            if (iBodyElement.getElementType().equals(BodyElementType.TABLE)) {
                hasDealTable = true;
                XWPFTable xwpfTable = (XWPFTable) iBodyElement;

                //1.预处理，去除多余合并行
                List<XWPFTableRow> rows = xwpfTable.getRows();
                Predicate<XWPFTableCell> pAllCellNotNull = x -> StringUtils.isNotEmpty(x.getText());
                List<XWPFTableRow> filteredRows = rows.stream()
                        .filter(xwpfTableRow -> xwpfTableRow.getTableCells().stream().anyMatch(pAllCellNotNull))
                        .collect(Collectors.toList());

                //2.获取指定位置的值
                List<Element> elements = (List<Element>) XmlUtil.getList(templateDoc, "/writ/QW/BGNR");
                for (Element element : elements) {
                    getValue(element, filteredRows);
                }
            }
        });
    }

    public void getValue(Element ele, List<XWPFTableRow> rows) {

        String name = ele.getName();
        if ("QD".equals(name)) {
            getQD(ele, rows);
        } else {
            getCommonValue(ele, rows);
        }
    }

    public void getCommonValue(Element ele, List<XWPFTableRow> rows) {
        String row = ele.attributeValue("row");
        String col = ele.attributeValue("col");
        if (StringUtils.isNotEmpty(row) && StringUtils.isNotEmpty(col)) {
            int rowInt = Integer.valueOf(row) -1;
            int colInt = Integer.valueOf(col) -1;
            if (rows.size() >= rowInt && rows.get(rowInt).getTableCells().size() >= colInt) {
                String text = rows.get(rowInt).getTableCells().get(colInt).getText();
                ele.addAttribute("value", text);
                ele.addAttribute("oValue", text);
            } else {
                logger.error("实际表格行列值小于所配行列值！");
            }
        }
        List<Element> childEles = (List<Element>) ele.elements();
        if (CollectionUtils.isNotEmpty(childEles)) {
            for (Element childEle : childEles) {
                getValue(childEle, rows);
            }
        }
    }

    public void getQD(Element ele, List<XWPFTableRow> rows) {
        Integer start = Integer.valueOf(ele.attributeValue("start")) -1;
        Integer size = Integer.valueOf(ele.attributeValue("size"));
        Element jd = ele.element("JD");
        ele.remove(jd);
        List<Element> jdEles = (List<Element>) jd.elements();

        loop1:
        for (int i = start; i < start + size; i++) {
            Element newJd = ele.addElement("JD");
            for (Element jdEle : jdEles) {
                Integer col = Integer.valueOf(jdEle.attributeValue("col")) - 1;
                String text = rows.get(i).getTableCells().get(col).getText();

                String notNull = jdEle.attributeValue("notNull");
                if (StringUtils.isNotEmpty(notNull)
                        && Boolean.valueOf(notNull)
                        && StringUtils.isEmpty(text)) {
                    ele.remove(newJd);
                    continue loop1;
                }

                Element newJDChild = newJd.addElement(jdEle.getName());
                newJDChild.addAttribute("nameCN", jdEle.attributeValue("nameCN"));
                newJDChild.addAttribute("value", text);
                newJDChild.addAttribute("oValue", text);
            }
        }
    }

}
