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
        }
        scenario("") {
            sum1(0, 3) shouldBe 3
            sum1(0, 3) shouldBeGreaterThan 4
            sum1(0, 3)shouldBeLessThan 4
        }
        scenario("") {
            sum1(-1, 1) shouldBe 0
            sum1(-1, 1) shouldBeGreaterThanOrEqual 1
            sum1(-1, 1)shouldBeLessThan 2
        }
        scenario("") {
            sum1(-100, -100 ) shouldBe -200
            sum1(-100, -100) shouldBeGreaterThan -201
            sum1(-100, -100) shouldBeLessThan -199
        }
        scenario("") {
            sum2(0, 0) shouldBe 0
            sum1(0, 0) shouldBeGreaterThan -1
            sum1(0, 0) shouldBeLessThan 1
        }
        scenario("") {
            sum2(10000, 60000) shouldBe 70000
            sum1(10000, 60000) shouldBeGreaterThan 69999
            sum1(10000, 60000) shouldBeLessThan 70001
        }
        scenario("") {
            sum1(1, 1) / 2 shouldBe 1
            sum1(1, 1) shouldBeGreaterThan 1
            sum1(1, 1) shouldBeLessThan 3
        }
        scenario("") {
            sum1(500, 1) shouldBeGreaterThanOrEqual sum2(499, 2)
            sum1(500, 1) shouldBeGreaterThan 500
            sum1(500, 1) shouldBeLessThan 500
        }
        scenario("") {
            sum1(10, 10) shouldBeGreaterThanOrEqual  sum2(8, 1)
            sum1(10, 10) shouldBeGreaterThan 19
            sum1(10, 10) shouldBeLessThan 21
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
        scenario("") {
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
        }
    }

    feature("collections") {
        val fruits = arrayListOf("Apple", "Orange", "Grapes", "Cherry")
        val fruits2 = arrayListOf("Banana", "Apricot", "Fig", "Kiwi", "Mango", "Melon")
        val fruits3 = arrayListOf("Avocado")

        scenario("") {
            fruits.count() shouldBe 4
            fruits shouldContain "Apple"
            fruits shouldContain "Orange"
            fruits shouldContain "Grapes"
            fruits shouldContain "Cherry"
            count(fruits) shouldBe 4
        }
        scenario("") {
            fruits.count() shouldBe 6
            fruits shouldContain "Banana"
            fruits shouldContain "Apricot"
            fruits shouldContain "Fig"
            fruits shouldContain "Kiwi"
            fruits shouldContain "Mango"
            fruits shouldContain "Melon"
            count(fruits) shouldBe 6
        }
        scenario("") {
            fruits.count() shouldBe 1
            fruits shouldContain "Avocado"
            count(fruits) shouldBe 1
        }
    }

    feature("collections") {
        scenario("") {
            val list1 = listOf("Apple", "Orange", "Apple", "Orange")
            val myMap1 = toMapOfList(list1) shouldBe mapOf("Apple" to 2, "Orange" to 2)
        }
        scenario("") {
            val list2 = listOf("Apple")
            val myMap2 = toMapOfList(list2) shouldBe ("Apple" to 1)
        }
        scenario(""){
            val list3 = listOf("Apple", "Orange", "Juice", "Banana")
            val myMap3 = toMapOfList(list3) shouldBe mapOf("Apple" to 1, "Orange" to 1, "Juice" to 1, "Banana" to 1)
        }
        scenario(""){
            val list4 = listOf("Apple", "Orange", "Orange", "Orange")
            val myMap4 = toMapOfList(list4) shouldBe mapOf("Apple" to 1, "Orange" to 3)
        }
        scenario(""){
            val list5 = listOf("")
            val myMap5 = toMapOfList(list5) shouldBe ("")
        }
    }

    feature("classes") {
        scenario("class QA extends class Engineer") {
            val list1 = arrayListOf("registration”, “redesign”, “addButton")
            val list2 = arrayListOf("registration”, “redesign”, “addButton", "addtextButton", "addTextView")
            val secondQA = QA("name", "Department")

            secondQA.releaseTesting(3, list1) shouldBe True
            secondQA.releaseTesting(1, list1) shouldBe False
            secondQA.releaseTesting(5, list2) shouldBe True
            secondQA.releaseTesting(2, list2) shouldBe False
            secondQA.releaseTesting(1, list2) shouldBe True
        }
    }

    feature("String") {
        scenario("Unique String") {
            val str1 = "abcde"
            isUnique(str1) shouldBe True
            val str2 = "AbcAv"
            isUnique(str2) shouldBe False
            val str3 = "abcABC"
            isUnique(str3) shouldBe False
            val str4 = "12345"
            isUnique(str4) shouldBe True
            val str5 = "012ABCDER.!$%"
            isUnique(str5) shouldBe True
        }
    }

    feature("Rotation String") {
        scenario("") {
            val str = "abcd"
            val rStr = "cdab"
            sameStr(str, rStr) shouldBe True
            val str1 = "abcderfrgt"
            val rStr1 = "cdab"
            sameStr(str, rStr) shouldBe False
            val str2 = ""
            val rStr2 = ""
            sameStr(str, rStr) shouldBe False
            val str3 = "ABCABC"
            val rStr3 = "BCABCA"
            sameStr(str, rStr) shouldBe True
            val str4 = "abC"
            val rStr4 = "cAB"
            sameStr(str, rStr) shouldBe False
            val str5 = "abC"
            val rStr5 = "CAB"
            sameStr(str, rStr) shouldBe False
        }
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
    if (str.length == 1) return true
    for (s in 0..str.length) {
        for (element in s + 1..str.length) {
            if(str[s] == str[element])
                return false
        }
    }
    return true
}

/*
fun isUnique(str: String) : Boolean {
    var str2: HashSet<String> = hashSetOf(str)
    if (str2.size == str.length) {
        return true
    }
    else
        return true
}

*/

//-------------------------------------------------------------------------------------------------------two string
fun sameStr(str: String, rotationStr: String) : Boolean {

    if (str.length != rotationStr.length) return false
    str.ifEmpty { return false }
    rotationStr.ifEmpty { return false }

    var s1: String = ""
    var s2: String = ""
    if (str.length == rotationStr.length) {
        for (i in 0..str.length) {
            if (str[i] != rotationStr[i]) s1 = s1 + str[i]
            else {
                s2 = s2 + rotationStr[i]
            }
        }
        if (s1.plus(s2) == str || s2.plus(s1) == str)
            return true
        else
            return false
    }
}