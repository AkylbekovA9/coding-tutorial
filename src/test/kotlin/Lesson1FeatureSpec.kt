import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.numerics.shouldBeLessThan
import io.kotlintest.shouldBe
import io.kotlintest.specs.FeatureSpec

class BasicsFeatureSpec : FeatureSpec({
    feature("functions") {
        scenario("sum1 and sum2 works the same") {
            val a = 1
            val b = 2
            sum1(a, b) shouldBe 3
            sum1(a, b) shouldBeLessThan 4
            sum1(a, b) shouldBeEqual
            sum1(0, 3) shouldBe 3
            sum1(-1, 1) shouldBe 0
            sum1(-100, -100 ) shouldBe -200
            sum2(0, 0) shouldBe 0
            sum2(4L, 6) shouldBe Error
            sum2(10000, 60000) shouldBeMoreThan 10!
            sum1(1, 1) / 2 shouldBe 1
            sum1(500, 1) shouldBeEqual sum2(499, 2)
            sum1(77, 1) * sum2(2, 2) shouldBeLessThan 83
            sum1(7,7) shouldBeEqual sum2(7,7)
            sum1()


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

        scenario("return min") {
            minOf(1, 2) shouldBe 1
            minOf(0, 4) shouldBe 0
            minOf(-5, -800) shouldBe -800
            minOf(36, -36) shouldBe -36
            minOf(1, 1) shouldBe 1
            minOf(-1, -1) shouldBe -1
            minOf()
        }

        scenario("return min") {   //vararg
            minOf(1, 2, 3, -1) shouldBe -1
            minOf(10, 0.2, 3) shouldBe 0.2
            minOf(1, 2, 3, -1) shouldBe -1
            minOf(1, 2, 3, -1, 5000, 4599000, 54.2, 88, 0) shouldBe -1
            minOf()

        }


    }
    // Write minOff function

    feature("when expression") {
        describe(1) shouldBe "One"
        describe("hello") shouldBe "Unknown"
        describe(9 223 372 036 854 775 808) shouldBe "Long"
        describe() shouldBe "Not a string"
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
        val fruits = arrayListOf("Apple", "Orange", "Apple", "Orange") firstMap shouldContain ("Apple" to 2, "Orange" to 2)
        val fruits = arrayListOf("Apple") firstMap shouldContain ("Apple" to 1)
        val fruits = arrayListOf("Apple", "Orange", "Juice", "Banana") firstMap shouldContain ("Apple" to 1, "Orange" to 1, "Juice" to 1, "Banana" to 1)
        val fruits = arrayListOf("Apple", "Orange", "Orange", "Orange") firstMap shouldContain ("Apple" to 1, "Orange" to 3)
    }

    feature("classes") {
        val releasedfunction = arrayListOf("registration”, “redesign”, “addButton")
        val releasedate = 3/1
        releaseTesting(releasedate, releasedfunction) shouldBe True
        releaseTesting(releasedate, releasedfunction) shouldBe False


    }

fun sum1(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

fun maxOf(a: Int, b: Int) = if (a > b) a else b

fun minOf(a: Double, b: Double): Any {
    if (a < b) return a
    return Unit
}

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


//Функция minOf
fun minOf(var n: Int, var m: Int) : Int = if (n >= m) m else n

//Функция minOf() с vararg
fun minOf2(vararg n: Int) : Int {
    var min = -1 000 000
    for (i in n) {
        if (min > n) min = n
    }
    return min
}


//List() to Map() with count of "it"

fun countofFruits(fruits: listOf<String>, fruit: String): Int {
    var sum = 0
    for (i in 0..fruits.size) {
        if (fruit == fruits[i])
           sum ++
    }
   return sum
}

fun toMapofList(firstList: listOf<String>) : mapOf<String, Int> {
    return firstList.associate { it to countofFruits(firstList, it) }
}


//class Engineer and class QA

open class Engineer(var name: String, var department: String) {}

class QA(name: String, department: String) : Engineer {
    fun releaseTesting(var releasedate: Int, releasedfunction: listOf()) : Boolean {
        if (releasedfunction.size * 2 > realeasedate) {
            return false
        } else {
            return true
        }
    }
}

}



