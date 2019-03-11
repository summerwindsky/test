//import com.alibaba.fastjson.JSONObject
//
//object Json {
//    def main(args: Array[String]): Unit = {
//        val str2 = "{\"et\":\"kanqiu_client_join\",\"vtm\":1435898329434,\"body\":{\"client\":\"866963024862254\",\"client_type\":\"android\",\"room\":\"NBA_HOME\",\"gid\":\"\",\"type\":\"\",\"roomid\":\"\"},\"time\":1435898329}"
//    JSONObject.
//        val data=
//        println(data)
//        //获取json成员
//        val et=data.get("et")
//        println(et)
//        //获取字符串类型json成员
//        val et1=data.getString("et")
//        println(et1)
//        //获取整形类型,这样可以确定val vtm的数据类型,防止下面编译过程中报错.
//        val vtm=data.getInt("vtm")
//        //获取多级元素
//        val client=data.getJSONObject("body").getString("client")
//        print(client)
//      }
//}
