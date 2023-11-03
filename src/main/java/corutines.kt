import kotlinx.coroutines.*

suspend fun main() = coroutineScope{

    val numDeferred1 = async{ sum(1, 2)}
    val numDeferred2 = async{ sum(3, 4)}
    val numDeferred3 = async{ sum(5, 6)}
    val num1 = numDeferred1.await()
    val num2 = numDeferred2.await()
    val num3 = numDeferred3.await()

    println("number1: $num1  number2: $num2  number3: $num3")
}
suspend fun sum(a: Int, b: Int) : Int{
    delay(500L) // имитация продолжительной работы
    return a + b
}