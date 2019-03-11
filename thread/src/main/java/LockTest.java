import java.util.concurrent.atomic.AtomicReference;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-09-06 18:29
 */
public class LockTest {
     class SpinLock {
        private AtomicReference<Thread> owner =new AtomicReference<>();
        public void lock(){
            Thread current = Thread.currentThread();
            while(!owner.compareAndSet(null, current)){
            }
        }
        public void unlock (){
            Thread current = Thread.currentThread();
            owner.compareAndSet(current, null);
        }
    }

}
