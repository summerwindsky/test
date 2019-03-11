package com.zj.disruptor;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-05-18 17:09
 */
public class ThreadMain {
    public static void main(String[] args) {

        // 队列中的元素
        class Element {

            private int value;

            public int get(){
                return value;
            }

            public void set(int value){
                this.value= value;
            }

        }

        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Element element = new Element();
            element.set(i);
            System.out.println(element.get());
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}
