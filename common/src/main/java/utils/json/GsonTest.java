package utils.json;

import com.google.gson.Gson;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-11-28 16:13
 */
public class GsonTest {
    private Gson gson = new Gson();

    private Entity entity = new Entity("123", "zs");
    private Entity2 entity2 = new Entity2("123");
    private EntityList entityList = new EntityList();

    @Test
    public void testField() {
        String json = gson.toJson(entity);
        Entity2 entity2 = gson.fromJson(json, Entity2.class);
        System.out.println(entity2);

        entityList.setEntitys(Arrays.asList(entity,entity));
//        String json1 = gson.toJson(entityList);
//        System.out.println(json1);
//        EntityList2 entityList2 = gson.fromJson(json1, EntityList2.class);
        EntityList2 entityList2 = GsonUtil.toJsonFromObj(entityList, EntityList2.class);
        EntityList3 entityList3 = GsonUtil.toJsonFromObj(entityList, EntityList3.class);
        System.out.println(gson.toJson(entityList2));
        System.out.println(gson.toJson(entityList3));// set 并没有去重
    }


    @Data
    class Entity {
        public Entity(String id, String name) {
            this.id = id;
            this.name = name;
        }

        private String id;
        private String name;
    }

    @Data
    class Entity2 {
        private String id;

        public Entity2(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Entity2{" +
                    "id='" + id + '\'' +
                    '}';
        }

//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            if (!super.equals(o)) return false;
//
//            Entity2 entity2 = (Entity2) o;
//
//            return id != null ? id.equals(entity2.id) : entity2.id == null;
//        }
//
//        @Override
//        public int hashCode() {
//            int result = super.hashCode();
//            result = 31 * result + (id != null ? id.hashCode() : 0);
//            return result;
//        }
    }

    @Data
    class EntityList {
        private List<Entity> entitys;
    }

    @Data
    class EntityList2 {
        private List<Entity2> entitys;
    }

    @Data
    class EntityList3 {
        private HashSet<Entity2> entitys;
    }
}

