package neo4j;

import neo4j.entity.WritEntity;
import org.apache.commons.lang3.time.StopWatch;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import neo4j.util.GsonUtil;

import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dingpeng
 * @version 1.0
 * @date 2018/8/15 14:43
 */
public class Neo4jTestThreadIndex extends Thread {

    private final Logger logger = LoggerFactory.getLogger(Neo4jTestThreadIndex.class);

    private String uri;
    private String name;
    private String password;

    private LocalTime localTime;

    private WritEntity writEntity;

    public Neo4jTestThreadIndex(String uri, String name, String password,
                                LocalTime localTime, WritEntity writEntity) {
        this.name = name;
        this.password = password;
        this.uri = uri;
        this.localTime = localTime;
        this.writEntity = writEntity;
    }

    @Override
    public void run() {
        Driver driver = null;
        logger.info(" start...");
        while (true) {
            if (timeEquals(LocalTime.now(), localTime)) {
                logger.info(" 开始查询...");
                try {
                    driver = getDriver();
                    Session session = driver.session();
                    Map<String, Object> map = new HashMap<>();

                    StopWatch stopWatch = new StopWatch();
                    stopWatch.start();
//                    createTest(session);
                    mergeTest(session);
                    stopWatch.stop();
                    System.out.println("用时："+stopWatch.getTime());
                    if (session != null) {
                        session.close();
                    }
                    System.out.println("用时："+stopWatch.getTime());
                    return;
                } catch (Exception e) {
                    logger.error("neo4j 异常：", e);
                } finally {
                    if (driver != null) {
                        driver.close();
                    }
                }
            } else {
                try {
                    sleep(100);
                } catch (Exception e) {
                    logger.error("sleep 异常:", e);
                }
            }
        }
    }


    public Driver getDriver() {
        Driver driver = null;
        try {
            driver = GraphDatabase.driver(uri, AuthTokens.basic(name, password));
        } catch (Exception e) {
            logger.error("创建driver异常：", e);
        }
        return driver;
    }

    public boolean timeEquals(LocalTime l1, LocalTime l2) {
        return true;
//        if (l1.getHour() == l2.getHour() && l1.getMinute() == l2.getMinute() && l1.getSecond() == l2
//                .getSecond()) {
//            return true;
//        }
//        return false;
    }

    public void createTest(Session session) {
        session.run("UNWIND "+ GsonUtil.toJson(writEntity.getLawFirmList()).replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2")+" as row" +
                " CREATE (n:ls)" +
                " SET n.name = row.name, n.id = row.id");

        session.run("UNWIND "+ GsonUtil.toJson(writEntity.getLawyerList()).replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2")+" as row" +
                " CREATE (n:lawyer)" +
                " SET n.name = row.name, n.id = row.id");

        session.run("UNWIND "+GsonUtil.toJson(writEntity.getRefLawyer2LsList()).replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2")+" as row" +
                " MATCH (a:lawyer {id: row.lawyerId})," +
                "  (b:ls {id: row.lsId})" +
                " MERGE (a)-[r:lawyer_ls_ref {id:row.id,name:row.name}]->(b)");

        session.run("UNWIND "+GsonUtil.toJson(writEntity.getRefLs2WsList()).replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2")+" as row" +
                " MATCH (a:ls {id: row.lsId})," +
                "      (b:ptal_ws {id: row.wsId})" +
                " MERGE (a)-[r:ls_ws_ref {id:row.id,name:row.name}]->(b)");

        session.run("UNWIND "+GsonUtil.toJson(writEntity.getRefLawyer2WsList()).replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2")+" as row" +
                " MATCH (a:lawyer {id: row.lawyerId})," +
                "      (b:ptal_ws {id: row.wsId})" +
                " MERGE (a)-[r:lawyer_ws_ref {id:row.id,name:row.name}]->(b)");
    }

    public void mergeTest(Session session) {
        String sql = "UNWIND " + GsonUtil.toJson(writEntity.getLawFirmList()).replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2") + " as row" +
                " CREATE (n:ls)" +
                " SET n.name = row.name, n.id = row.id";
//        session.runAsync(sql);

        session.runAsync( sql)
                .thenCompose( cursor -> cursor.listAsync( record -> record.get( 0 ).asString() ) )
                .exceptionally( error ->
                {
                    // query execution failed, print error and fallback to empty list of titles
                    error.printStackTrace();
                    return Collections.emptyList();
                } )
                .thenCompose( titles -> session.closeAsync().thenApply( ignore -> titles ) );
    }
    
}
