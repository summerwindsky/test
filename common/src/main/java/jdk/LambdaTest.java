package jdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-11-07 14:15
 */
public class LambdaTest {
    public static void main(String[] args) {

        Comparator<String> c = (String s1, String s2) -> s1.compareToIgnoreCase(s2);

        List<Person> people = new ArrayList<>();
        Collections.sort(people, Comparator.comparing(Person::getName));
        people.sort(Comparator.comparing(Person::getName));

//        BufferedReader.lines();
    }

    public class Person{
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
