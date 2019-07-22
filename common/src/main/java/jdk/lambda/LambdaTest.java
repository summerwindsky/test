package jdk.lambda;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2019-01-11 10:46
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<Person> ps = Arrays.asList(new Person("zs"), new Person("ls"));
        Stream<String> names = ps.stream().<String>map(p -> p.getName());
        names.forEach(System.out::println);

        // 目标类型
        Supplier<Runnable> c = () -> () -> {
            System.out.println("");
        };
        // Object o = () -> { System.out.println("hi"); }; 这段代码是非法的
        Object o = (Runnable) () -> {
            System.out.println("hi");
        };

        // lambda 表达式对 值 封闭，对 变量 开放。
        // 归约（reduction）来操作 值
        int sum = ps.stream().mapToInt(e -> e.getAge()).sum();
        int sum1 = ps.stream().mapToInt(e -> e.getAge()).reduce(0, (x, y) -> x * y);

        // 例子
        Collections.sort(ps, comparing(p -> p.getName()));
        Collections.sort(ps, comparing(Person::getName));

        ps.sort(comparing(Person::getName));
        ps.sort(comparing(Person::getName).reversed());
        ps.sort(Comparator.nullsFirst(comparing(Person::getName).thenComparing(Person::getAge)));

        //
        Pattern pattern = Pattern.compile("\\s+");
        Map<String, Long> wordFreq =
                ps.stream()
                        .flatMap(t -> pattern.splitAsStream(t.getName())) // Stream<String>
                        .collect(groupingBy(s -> s.toUpperCase(), counting()));
    }

    /**
     * collector
     */
    @Test
    public void testLib() {
        Person[] persons = {new Person("zs", 1), new Person("ls", 2), new Person("zs", 1)};
//        Person[] persons = {};
        Pattern pattern = Pattern.compile("\\s+");
        Arrays.stream(persons)
                .map(p -> p.getName())
                .flatMap(n -> pattern.splitAsStream(n))
                .collect(Collectors.toList());
    }


    private static class Person {
        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void testColect2Set() {
        AtomicLong atomicLong = new AtomicLong();
        Set<String> set = new HashSet<>();
        set.add("fffff");
        set.add("ggggg");
        set.add("hhhhh");
        Set<String> contains_ls_uncheck_ls_dealed = set
                .stream()
                .map(ls -> {
                    ls = ls.substring(3);
                    return ls;
                })
                .collect(Collectors.toSet());

        contains_ls_uncheck_ls_dealed.forEach(x -> System.out.println(x));
    }
}
