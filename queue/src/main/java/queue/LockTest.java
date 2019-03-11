//package queue;
//
//import com.thunisoft.lawerdata.common.util.QueryUtil;
//import com.thunisoft.lawerdata.lawyer.entity.Lawyer;
//import com.thunisoft.lawerdata.lawyer.entity.CacheBean;
//import com.thunisoft.lawerdata.lawyer.handle.LawyerDaoI;
//import com.thunisoft.lawerdata.lawyer.handle.LawyerESHandler;
//import com.thunisoft.lawerdata.system.config.ESConfig;
//import com.thunisoft.lawerdata.writ.config.Constants;
//import com.thunisoft.lawerdata.writ.entity.WritAnalyzer;
//import io.netty.util.internal.ConcurrentSet;
//import net.sf.json.JSONObject;
//import org.apache.commons.beanutils.BeanUtils;
//import org.apache.commons.collections.CollectionUtils;
//import org.elasticsearch.search.SearchHit;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.Iterator;
//import java.util.Set;
//import java.util.concurrent.*;
//import java.util.concurrent.atomic.AtomicLong;
//
///**
// * Title:
// * Description:
// * Company: 北京华宇元典信息服务有限公司
// *
// * @author zhangjing
// * @version 1.0
// * @date 2018-07-13 11:04
// */
//public class LockTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(LockTest.class);
//
//    AtomicLong secount = new AtomicLong();
//
//    private LinkedBlockingDeque<CacheBean> cache = new LinkedBlockingDeque<CacheBean>();
//    private LinkedBlockingDeque<CacheBean> delay = new LinkedBlockingDeque<CacheBean>();
//    private ConcurrentHashMap dealing = new ConcurrentHashMap();
//    new Concurrent
//
//    private Semaphore semaphore = new Semaphore(120);
//
//    private LinkedBlockingDeque queue = new LinkedBlockingDeque<CacheBean>();
//    private ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 20, 1, TimeUnit.HOURS, queue);
//
//    public void doTest() {
//
//        // 1.缓存，信号量
//        // 2.线程池消费缓存队列
//        // 3.判断dealing是否存在，若存在放delay队列，不存在（清除缓存，执行操作，释放信号量）
//        // 4.消费delay队列，同上
//
//        // 1.缓存，信号量
//        for (;;) {
//            new CacheBean();
//            try {
//                semaphore.acquire();
//                cache.add();
//                secount.getAndIncrement();
//            } catch (InterruptedException e) {
//                logger.error("遍历 律师 异常：{}", e);
//            }
//
//            // 2.线程池消费缓存队列
//            pool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        CacheBean bean = nextTask();
//                        if (bean != null) {
//                            process(bean);
//                        }
//                    } catch (Exception e) {
//                        logger.error("写入es lawyer type失败：{}");
//                    } finally {
//                        semaphore.release();
////                        logger.info("lawyer length:{}", semaphore.getQueueLength());
//                    }
//                }
//            });
//        }
//
//        // 3.判断dealing是否存在，若存在放delay队列，不存在（清除缓存，执行操作，释放信号量）
//        // 4.消费delay队列，同上
//    }
//
//    private void process(CacheBean bean) {
//
//        dealing.remove(lawyerName + "##" + lawFirmName);
//    }
//
//    private CacheBean nextTask() {
//        // 3.判断dealing是否存在，若存在放delay队列，不存在（清除缓存，执行操作，释放信号量）
//        // 1.遍历delay，判断是否在处理，未处理（remove delay，return）
//        // 2.从cache poll,判断是否在处理，处理（put delay）
//
//        Iterator<CacheBean> itr = delay.iterator();
//        while (itr.hasNext()) {
//            CacheBean bean = itr.next();
//            if (bean != null && !isProcessing(bean)) {
//                itr.remove();
//                return bean;
//            }
//        }
//        CacheBean bean = cache.poll();
//        if (bean != null && !isProcessing(bean)) {
//            return bean;
//        }
//        delay.add(bean);
//        return null;
//    }
//
//    private synchronized boolean isProcessing(CacheBean bean) {
//        String key = bean.getName() + "##" + bean.getYsls();
//        if (dealing.contains(key)) {
//            return true;
//        }
//        dealing.add(key);
//        return false;
//    }
//
//}
