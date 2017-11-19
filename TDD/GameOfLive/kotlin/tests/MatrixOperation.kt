import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.specs.FunSpec

class TransformOperation : FunSpec() {
    init {
        test("identity transformation") {
            transform(1,1, identity) shouldBe Pair<Int, Int>(1,1)
        }
        test("transform 90 degree transformation") {
            transform(1,1, rotate90degree) shouldBe Pair<Int, Int>(1,-1)
        }
        test("transform 180 degree transformation") {
            transform(1,1, rotate180degree) shouldBe Pair<Int, Int>(-1,-1)
        }
        test("transform 270 degree transformation") {
            transform(1,1, rotate270degree) shouldBe Pair<Int, Int>(-1,1)
        }
        test("identity transformation") {
            transform(2,2, identity) shouldBe Pair<Int, Int>(2,2)
        }
        test("transform 90 degree transformation") {
            transform(2,2, rotate90degree) shouldBe Pair<Int, Int>(2,-2)
        }
        test("transform 180 degree transformation") {
            transform(2,2, rotate180degree) shouldBe Pair<Int, Int>(-2,-2)
        }
        test("transform 270 degree transformation") {
            transform(2,2, rotate270degree) shouldBe Pair<Int, Int>(-2,2)
        }
    }
}