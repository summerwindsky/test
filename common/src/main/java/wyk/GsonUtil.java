package wyk;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json, Class<?> clz) {
        return (T) gson.fromJson(json, clz);
    }


}
