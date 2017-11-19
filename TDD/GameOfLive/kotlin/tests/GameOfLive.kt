import io.kotlintest.specs.FeatureSpec
import io.kotlintest.matchers.shouldBe

class GameOfLiveRules : FeatureSpec() {
    init {
        feature("Live cell with fewer than two live neighbours dies") {
            scenario("fewer than two neighbours") {
                cellAlive(alive, 0) shouldBe dead
                cellAlive(alive, 1) shouldBe dead
            }
        }
        feature("Live cell with two or three live neighbours lives on") {
            scenario("two neighbours") {
                cellAlive(alive, 2) shouldBe alive
            }
            scenario("thee neighbours") {
                cellAlive(alive, 3) shouldBe alive
            }
        }
        feature("live cell with more than three live neighbours dies") {
            scenario("four neighbours") {
                cellAlive(alive, 4) shouldBe dead
            }
            scenario("more neighbours") {
                cellAlive(alive, 5) shouldBe dead
                cellAlive(alive, 6) shouldBe dead
                cellAlive(alive, 7) shouldBe dead
                cellAlive(alive, 8) shouldBe dead
                cellAlive(alive, 9) shouldBe dead
            }
        }
        feature("dead cell with exactly three live neighbours becomes a live"){
            scenario("less than three neighbours") {
                cellAlive(dead, 0) shouldBe dead
                cellAlive(dead, 1) shouldBe dead
                cellAlive(dead, 2) shouldBe dead
            }
            scenario("three neighbours"){
                cellAlive(dead, 3) shouldBe alive
            }
            scenario("more than three neighbours"){
                cellAlive(dead, 4) shouldBe dead
                cellAlive(dead, 5) shouldBe dead
                cellAlive(dead, 6) shouldBe dead
                cellAlive(dead, 7) shouldBe dead
                cellAlive(dead, 8) shouldBe dead
                cellAlive(dead, 9) shouldBe dead
            }
        }
    }
}