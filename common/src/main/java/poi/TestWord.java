package poi;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;


public class TestWord {

    @Test
    public void testWord(){
        try{
            FileInputStream in = new FileInputStream("D:\\表格类型.doc");//载入文档
            POIFSFileSystem pfs = new POIFSFileSystem(in);
            HWPFDocument hwpf = new HWPFDocument(pfs);
            Range range = hwpf.getRange();//得到文档的读取范围
            TableIterator it = new TableIterator(range);
            //迭代文档中的表格
            while (it.hasNext()) {
                Table tb = (Table) it.next();
                //迭代行，默认从0开始
                for (int i = 0; i < tb.numRows(); i++) {
                    TableRow tr = tb.getRow(i);
                    //迭代列，默认从0开始
                    for (int j = 0; j < tr.numCells(); j++) {
                        TableCell td = tr.getCell(j);//取得单元格
                        //取得单元格的内容
                        for(int k=0;k<td.numParagraphs();k++){
                            Paragraph para =td.getParagraph(k);
                            String s = para.text();
                            System.out.println(s);
                        } //end for
                    }   //end for
                }   //end for
            } //end while
        }catch(Exception e){
            e.printStackTrace();
        }
    }//end method

	public static void main(String[] args){  
        try{  
        	String filePath = "D://test.docx";
           FileInputStream in = new FileInputStream(filePath);
           if(filePath.toLowerCase().endsWith("docx")){
               XWPFDocument xwpf = new XWPFDocument(in);
               Iterator<XWPFTable> it = xwpf.getTablesIterator();
               Iterator<XWPFParagraph> itPara = xwpf.getParagraphsIterator();
               while(itPara.hasNext()) {
            	   XWPFParagraph para = itPara.next();
            	   System.out.println("段落信息----------" + para.getText());
               }

               while(it.hasNext()){

                   XWPFTable table = it.next();


//                   XWPFTableCell cell1 = table.getRows().get(1).getTableCells().get(4);
//                   System.out.println(cell1.getText());



                   List<XWPFTableRow> rows=table.getRows();   
                   //读取每一行数据  
                   for (int i = 0; i < rows.size(); i++) {
                       XWPFTableRow  row = rows.get(i);  
                       //读取每一列数据  
                       List<XWPFTableCell> cells = row.getTableCells(); 
                       String rowString = i+"==";
                       for (int j = 0; j < cells.size(); j++) {  
                           XWPFTableCell cell=cells.get(j);
                           CTTc CTTc = cell.getCTTc();
                           CTTcPr prop = CTTc.getTcPr();
                           CTVMerge cevMerge = prop.getVMerge();
                           String s = "";
                           //1.非合併單元格
                           if(cevMerge==null) {

                           }else{
                        	   //2.合併單元格的第一行
                        	   if(cevMerge.getVal() != null) {
                        		   s = cevMerge.getVal().toString();
                        	   }else {
                        		   //3.合併單元格的其他行
                        	   }
                           }
                           
                           //CTDecimalNumber CTDecimalNumber = prop.getGridSpan();
                           String content = StringUtils.isBlank(cell.getText())?"null":cell.getText();
                           if(StringUtils.isNotBlank(rowString)) {
                        	   rowString = rowString + "," + content;
                           }else {
                        	   rowString = content;
                           }
                           //输出当前的单元格的数据  
                      } 
                       System.out.println(rowString);
                   }  
               }  
           }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }



}
