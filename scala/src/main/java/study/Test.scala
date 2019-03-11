package study

/**
  * Title: 
  * Description: 
  * Company: 北京华宇元典信息服务有限公司
  *
  * @author zhangjing
  * @version 1.0
  * @date 2019-01-24 14:16
  *
  */
object Test {
  def apply(i: Int): Int = i*2
  def main(args: Array[String]): Unit = {
    val x = Test(5)
    // 使用模式匹配时 提取器unapply 自动被调用
    x match {
      case Test(num) => print(x +"is num " + num)
      case _ => print("none")
    }
  }

  def unapply(arg: Int): Option[Int] = if(arg%2 == 0) Some(arg/2) else None

}
