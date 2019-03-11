package study.implict

/**
  * Title: 
  * Description: 
  * Company: 北京华宇元典信息服务有限公司
  *
  * @author zhangjing
  * @version 1.0
  * @date 2019-01-25 15:12
  *
  */
object ImplictTest {
  // 数据类型转换
  implicit def foo(s: String): Int = Integer.parseInt(s) // 需要时把String->Int
  def add(a: Int, b: Int) = a + b

//  def factorial(n: Int) = 1 to n reduceLeft(_*_)
//  implicit def m2(n:Int) = new { def ! = factorial(n) }


  def func(i:Int) = 1 to i reduceLeft(_*_)

//  def !(i:Int)= func(i)

  implicit def impl1(i:Int) = new {def ! = func(i)}

  def main(args: Array[String]): Unit = {
    println(add("100", 8)) // 108, 先把"100"隐式转换为100
    print(10!)
//    !(1)
  }


}
