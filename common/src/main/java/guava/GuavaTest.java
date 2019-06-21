package guava;

import com.google.common.base.*;
import com.google.common.collect.MoreCollectors;
import com.google.common.collect.Ordering;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2019-01-17 10:17
 */
public class GuavaTest {

    @Test
    public void test() {
        int i = 0;
        List<Foo> list = Arrays.asList(new Foo("", 0));
        Foo foo = new Foo("1",1);
        Foo foo1 = new Foo("1",1);

        //
        Optional<Integer> possible = Optional.of(5);
        possible.isPresent(); // returns true
        possible.get(); // returns 5

        // 1.Preconditions 参数判断
//        Preconditions.checkArgument(i>0,"%s is bigger than 0",i);
        Preconditions.checkArgument(i==0,"%s is bigger than 0",i);

        // Sort  见LambdaTest.java
        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
            public String apply(Foo foo) {
                return foo.sortedBy;
            }
        });
//        Ordering<Foo> ordering1 = Ordering.natural().nullsFirst().onResultOf();

        ordering.max(list);
            // lambda
//            list.sort(Comparator.comparing());

        // Objects
        //  比的是地址啊
        Objects.equals(null, "hh"); // 替换StringUtils.isNotEmpty() &&
        boolean b = Objects.deepEquals(foo, foo1);
        System.out.println(b);

        // toString
        MoreObjects.toStringHelper(this).add("jjj", 1);

        // exception
        try {
            throw new Exception();
        } catch (Throwable t) {
            Throwables.propagateIfPossible(t, Exception.class);
            Throwables.propagate(t);
        }

    }

    class Foo {
        public Foo(String sortedBy, int notSortedBy) {
            this.sortedBy = sortedBy;
            this.notSortedBy = notSortedBy;
        }

        @Nullable
        String sortedBy;
        int notSortedBy;
    }
}
