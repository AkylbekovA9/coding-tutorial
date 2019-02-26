import arrow.core.Eval.Companion.False
import arrow.core.Eval.Companion.True
import com.github.javafaker.Bool
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.numerics.*
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.FeatureSpec

class BasicsFeatureSpec : FeatureSpec({
    feature("functions") {
        scenario("sum1 and sum2 works the same") {
            val a = 1
            val b = 2
            sum1(a, b) shouldBe 3
            sum1(a, b) shouldBeLessThan 4
            sum1(0, 3) shouldBe 3
            sum1(-1, 1) shouldBe 0
            sum1(0, 3) shouldBe 3
            sum1(-1, 1) shouldBe 0
            sum1(-100, -100 ) shouldBe -200
            sum2(0, 0) shouldBe 0
            sum2(10000, 60000) shouldBeGreaterThan  10
            sum1(1, 1) / 2 shouldBe 1
            sum1(500, 1) shouldBeGreaterThanOrEqual sum2(499, 1)
            sum1(500,10) shouldBeLessThanOrEqual sum2(1000, 1)
            sum1(10, 10) shouldBeGreaterThanOrEqual  sum2(10, 10)
            sum1(9, 9) shouldBeGreaterThan sum2(8, 8)
            // Add greater less checks
        }
    }

    feature("variables") {

        val readOnly = 11
        var reassignable = 3
//            scenario("val can not be reassigned") {
//                readOnly = 12
//            }

        scenario("can be reassigned") {
            reassignable = 6
            reassignable shouldBe 6
        }
    }

    feature("strings") {
        val toge = "toge"
        val ther = "ther"
        val together = "together"

        scenario("concatenation works") {
            toge + ther shouldBe together
        }

        scenario("string interpolation works") {
            "$toge$ther" shouldBe together
        }

        scenario("is not empty") {
            together.isNotBlank() shouldBe true
        }
    }

    feature("conditional expressions") {
        val max = 100
        val min = 0

        scenario("returns max") {
            maxOf(min, max) shouldBe max
        }

        scenario("") {
            minOf(1, 2)
            minOf(-9, 8) shouldBe -9
            minOf(1, 2) shouldBe 1
            minOf(0, 4) shouldBe 0
            minOf(-5, -800) shouldBe -800
            minOf(36, -36) shouldBe -36
            minOf(1, 1) shouldBe 1
            minOf(-1, -1) shouldBe -1
            minOf_vararg(1, 5, 8) shouldBe 1
            minOf_vararg(-9, 40, 4000, 0) shouldBe -9
        }
    }

    // Write minOff function

    feature("when expression") {
        describe(1) shouldBe "One"
        describe("hello") shouldBe "Unknown"
        describe(1) shouldBe "One"
        describe("hello") shouldBe "Unknown"
        describe("9223372036854775808") shouldBe "Long"
        describe(44) shouldBe "Not a string"
        describe("Hello") shouldBe "Greeting"
        describe(5) shouldBe "Unknown"
        describe(0) shouldBe "Unknown"
        describe("helLo") shouldBe "Unknown"
        describe(1.0) shouldBe "Unknown"
        describe("") shouldBe "Not a string"
        // Add other checks
    }

    feature("collections") {
        val fruits = arrayListOf("Apple", "Orange", "Grapes", "Cherry")

        scenario("") {
            fruits.count() shouldBe 4
            fruits shouldContain "Apple"
            fruits shouldContain "Orange"
            fruits shouldContain "Grapes"
            fruits shouldContain "Cherry"
            count(fruits) shouldBe 4
        }
    }

    feature("collections") {
        val list1 = listOf("Apple", "Orange", "Apple", "Orange")
        val list2 = listOf("Apple")
        val list3 = listOf("Apple", "Orange", "Juice", "Banana")
        val list4 = listOf("Apple", "Orange", "Orange", "Orange")
        val list5 = listOf("")

        val myMap1 = toMapOfList(list1) shouldBe ("Apple" to 2, "Orange" to 2)
        val myMap2 = toMapOfList(list2) shouldBe ("Apple" to 1)
        val myMap3 = toMapOfList(list3) shouldBe ("Apple" to 1, "Orange" to 1, "Juice" to 1, "Banana" to 1)
        val myMap4 = toMapOfList(list4) shouldBe ("Apple" to 1, "Orange" to 3)
        val myMap5 = toMapOfList(list5) shouldBe ("")
    }

    feature("classes") {
        val list1 = arrayListOf("registration”, “redesign”, “addButton")
        val list2 = arrayListOf("registration”, “redesign”, “addButton", "addtextButton", "addTextView")
        val secondQA = QA("name", "Department")

        secondQA.releaseTesting(3, list1) shouldBe True
        secondQA.releaseTesting(1, list1) shouldBe False
        secondQA.releaseTesting(5, list2) shouldBe True
        secondQA.releaseTesting(2, list2) shouldBe False
        secondQA.releaseTesting(1, list2) shouldBe True
    }
})

fun sum1(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

fun maxOf(a: Int, b: Int) = if (a > b) a else b

/*fun minOf(a: Double, b: Double): Any {
    if (a < b) return a
    return Unit
}
*/
fun describe(obj: Any): String =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknown"
        }

fun count(list: ArrayList<String>): Int {
    var counter = 0
    for (i in list) {
        counter += 1
    }
    return counter
}


//------------------------------------------------------------------------------------Функция minOf//
fun minOf(n: Int, m: Int) : Any = if (n >= m) m else n

//------------------------------------------------------------------------------------Функция minOf (vararg)//
fun minOf_vararg(vararg numbers: Int) {
    var min = 0
    for (n in numbers) {
        if (min > n) min = n
    }
}


//-----------------------------------------------------------------------------------List() to Map() with count of "it"
fun countofFruits(fruits: List<String>, fruit: String): Int {
    var sum = 0
    for (i in 0..fruits.size) {
        if (fruit == fruits[i])
            sum ++
    }
    return sum
}
fun toMapOfList(firstList: List<String>) : Map<String, Int>{
    //write loop
    return firstList.associate { it to countofFruits(firstList, it) }
}



//-------------------------------------------------------------------------------------class Engineer and class QA
open class Engineer(var name: String, var department: String) {}

class QA(name: String, department: String) : Engineer (name, department) {
    fun releaseTesting(releasedate: Int, releasedfunction: ArrayList<String>) : Boolean {
        if (releasedfunction.size * 2 > releasedate) {
            return false
        } else {
            return true
        }
    }
}

//-----------------------------------------------------------------------------------------------isStringSymbols unique
fun isUnique(str: String): Boolean {
    for (i in 0..str.length) {
        if (str[i] == str[i + 1])
            return false
    }
    return true
}
//---------------------------------------------------------------------------------------------isStringSymbols unique_2
/*
fun isUnique2(str2: String): Boolean {
    var count = 0
    for (s in 0..str2.length) {
        for (element in 0..str2.length) {
            if (s == element) count++
            if (count > 1) return false
        }
    }
    return true
}
*/

fun sameStr(s1 : String, s2: String) : Boolean {
    if (s1.length != s2.length) return false
    s1.ifEmpty { return false }
    s2.ifEmpty { return false }
}