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
})

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