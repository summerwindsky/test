package neo4j;

import org.neo4j.driver.v1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dingpeng
 * @version 1.0
 * @date 2018/8/15 14:43
 */
public class Neo4jTestThread extends Thread {

    private final Logger logger = LoggerFactory.getLogger(Neo4jTestThread.class);

    private String uri;
    private String name;
    private String password;

    private String companyId;

    private LocalTime localTime;

    public Neo4jTestThread(String uri, String name, String password, String companyId,
            LocalTime localTime) {
        this.name = name;
        this.password = password;
        this.uri = uri;
        this.companyId = companyId;
        this.localTime = localTime;
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
                    map.put("id", companyId);

                    StatementResult result = session
                            .run("match (p:Staff) -[]-> (c:Company) -[]-> (n) where c.id = " + companyId + " return n,c,p");
                    while (result.hasNext()) {
                        Record record = result.next();
                        if (record.get("p") != null) {
                            logger.info("staff --> name={},id={}", record.get("p").get("name"),
                                        record.get("p").get("staffId"));
                        }
                        if (record.get("n") != null) {
                            if (!"NULL".equals(record.get("n").get("staffId").toString())) {
                                logger.info("staff --> name={},id={}", record.get("n").get("name"),
                                            record.get("n").get("staffId"));
                            } else {
                                logger.info("company --> name={},id={}",
                                            record.get("n").get("name"), record.get("n").get("id"));
                            }
                        }
                    }
                    if (session != null) {
                        session.close();
                    }
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
        if (l1.getHour() == l2.getHour() && l1.getMinute() == l2.getMinute() && l1.getSecond() == l2
                .getSecond()) {
            return true;
        }
        return false;
    }
}
