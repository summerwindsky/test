package neo4j;

import neo4j.entity.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dingpeng
 * @version 1.0
 * @date 2018/8/15 14:58
 */
public class Main {

    public static void main(String[] args) {
//        String uri = "bolt://192.168.99.75:7687";
//        String name = "neo4j";
//        String password = "hyyd";
        String uri = "bolt://localhost:7687";
        String name = "neo4j";
        String password = "root";

//        String uri = "bolt://172.23.7.58:7687";
//        String name = "root";
//        String password = "admin";

        List<Integer> list = new ArrayList<>();
        Random ra = new Random();
        for (int i = 0; i < 1; i++) {
//            list.add(1000);
            list.add(10000);
//            list.add(1000);
        }
        //        list.add("431");
        //        list.add("510");
        //        list.add("430")
        //        list.add("602");
        //        list.add("705");
        //        list.add("750");
        //        list.add("790");
        //        list.add("830");
        //        list.add("855");
        //        list.add("946");
        //        list.add("133");
        //        list.add("258");
        //        list.add("365");
        //        list.add("1110");
        //        list.add("100009");
        //        list.add("510010");
        //        list.add("1510010");
        //        list.add("5510010");
        //        list.add("15510002");
        //        list.add("35510010");
        //        list.add("85510010");
        //        list.add("125510003");


        LocalTime localTime = LocalTime.of(17, 27, 10);

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(list.size(), list.size(), 1,
                TimeUnit.MINUTES,
                new LinkedBlockingDeque<>());
        for (int i = 0; i < list.size(); i++) {
//            Thread t = new neo4j.Neo4jTestThread(uri, name, password, list.get(i), localTime);
            WritEntity data = createData(Integer.valueOf(list.get(i)));
            Thread t = new Neo4jTestThreadIndex(uri, name, password,localTime, data);
            poolExecutor.execute(t);
        }

        poolExecutor.shutdown();
        try {
            poolExecutor.awaitTermination(2, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static   WritEntity createData(int count) {
        WritEntity writEntity = new WritEntity();
        for (int i = 0; i < count; i++) {
            String id = i + "";
            Lawyer lawyer = new Lawyer(i + "", i+"张三", null, null, null, null, null, null);
            LawFirm lawFirm = new LawFirm(i + "", i+"张三律师事务所", null, null, null, null, null, null, null);
            RefLawyer2Ws refLawyer2Ws = new RefLawyer2Ws(id, "c8c007ee-0da7-455a-96ae-e0b9e750a413");
            RefLs2Ws refLs2Ws = new RefLs2Ws(id, "c8c007ee-0da7-455a-96ae-e0b9e750a413");
            RefLawyer2Ls refLawyer2Ls = new RefLawyer2Ls(id, id);

            writEntity.addLawyer(lawyer);
            writEntity.addLawFirm(lawFirm);
            writEntity.addRefLawyer2Ls(refLawyer2Ls);
            writEntity.addRefLawyer2Ws(refLawyer2Ws);
            writEntity.addRefLs2Ws(refLs2Ws);

        }
//        System.out.println(GsonUtil.toJson(writEntity));
        return writEntity;
    }
}
