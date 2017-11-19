import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.specs.FunSpec

class MyTests : FunSpec() {
    init {
        test("Pair comparison") {
            Pair<Int, Int>(0,0) shouldBe Pair<Int, Int>(0,0)
            Pair<Int, Int>(1,0) shouldBe Pair<Int, Int>(1,0)
            Pair<Int, Int>(0,1) shouldBe Pair<Int, Int>(0,1)
            Pair<Int, Int>(1,1) shouldBe Pair<Int, Int>(1,1)
            Pair<Int, Int>(0,2) shouldBe Pair<Int, Int>(0,2)
            Pair<Int, Int>(0,-2) shouldNotBe Pair<Int, Int>(0,-1)
        }
    }
}