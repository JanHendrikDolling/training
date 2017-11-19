import com.google.common.collect.ImmutableSet
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec
import io.kotlintest.specs.FunSpec

val emptyBoard = Board()
val singleCellBoard = Board().place(0,0)
val boardWithTwo = Board().place(0,0).place(1,1)
val diagonalLineNegativeXPositiveY = Board().place(-1,1).place(-2,2).place(-3,3)
val diagonalLinePositiveXPositiveY = Board().place(1,1).place(2,2).place(3,3)
val diagonalLinePositiveXNegativeY = Board().place(1,-1).place(2,-2).place(3,-3)
val diagonalLineNegativeXNegativeY = Board().place(-1,-1).place(-2,-2).place(-3,-3)

class BoardMethods : ShouldSpec() {
    init {
        "Board operation" {
            should("lead to empty board if removing the cell from a single cell Board"){
                singleCellBoard.remove(0,0) shouldBe emptyBoard
            }
            should("transformation with identity 1"){
                diagonalLineNegativeXPositiveY.transform() shouldBe diagonalLineNegativeXPositiveY
            }
            should("transformation with identity 2"){
                diagonalLinePositiveXPositiveY.transform() shouldBe diagonalLinePositiveXPositiveY
            }
            should("transformation with identity 3"){
                diagonalLinePositiveXNegativeY.transform() shouldBe diagonalLinePositiveXNegativeY
            }
            should("transformation with identity 4"){
                diagonalLineNegativeXNegativeY.transform() shouldBe diagonalLineNegativeXNegativeY
            }
            should("transformation - rotate 90 degree"){
                diagonalLineNegativeXPositiveY.transform(rotate90degree) shouldBe diagonalLinePositiveXPositiveY
            }
            should("transformation - rotate 180 degree"){
                diagonalLineNegativeXPositiveY.transform(rotate180degree) shouldBe diagonalLinePositiveXNegativeY
            }
            should("transformation - rotate 270 degree"){
                diagonalLineNegativeXPositiveY.transform(rotate270degree) shouldBe diagonalLineNegativeXNegativeY
            }
        }
        "empty Board" {
            should("be empty after each time step") {
                emptyBoard.doTimeStep() shouldBe emptyBoard
            }
            should("contain no active cell"){
                emptyBoard.activeCells() shouldBe 0
            }
            should("No neighbours available"){
                emptyBoard.getNeighbours(0,0) shouldBe ImmutableSet.Builder<Pair<Int, Int>>().build()
            }
            should("cell at (0,0) should has no neighbour"){
                emptyBoard.getNeighboursCount(0,0) shouldBe 0
            }
            should("have no active neighbours"){
                emptyBoard.getInactiveNeighbours(0,0) shouldBe ImmutableSet.Builder<Pair<Int, Int>>()
                        .add(Pair<Int,Int>(-2,-2))
                        .add(Pair<Int,Int>(-2,-1))
                        .add(Pair<Int,Int>(-2,0))
                        .add(Pair<Int,Int>(-2,1))
                        .add(Pair<Int,Int>(-2,2))
                        .add(Pair<Int,Int>(-1,-2))
                        .add(Pair<Int,Int>(-1,-1))
                        .add(Pair<Int,Int>(-1,0))
                        .add(Pair<Int,Int>(-1,1))
                        .add(Pair<Int,Int>(-1,2))
                        .add(Pair<Int,Int>(0,2))
                        .add(Pair<Int,Int>(0,1))
                        .add(Pair<Int,Int>(0,-1))
                        .add(Pair<Int,Int>(0,-2))
                        .add(Pair<Int,Int>(1,-2))
                        .add(Pair<Int,Int>(1,-1))
                        .add(Pair<Int,Int>(1,0))
                        .add(Pair<Int,Int>(1,1))
                        .add(Pair<Int,Int>(1,2))
                        .add(Pair<Int,Int>(2,-2))
                        .add(Pair<Int,Int>(2,-1))
                        .add(Pair<Int,Int>(2,0))
                        .add(Pair<Int,Int>(2,1))
                        .add(Pair<Int,Int>(2,2))
                        .build()
            }
        }
        "single cell at (0,0) Board" {
            should("contain one active cell") {
                singleCellBoard.activeCells() shouldBe 1
            }
            should("be empty after one time step"){
                singleCellBoard.doTimeStep() shouldBe emptyBoard
            }
            should("No neighbours available"){
                emptyBoard.getNeighbours(0,0) shouldBe ImmutableSet.Builder<Pair<Int, Int>>().build()
            }
            should("cell at (0,0) should has no neighbour"){
                emptyBoard.getNeighboursCount(0,0) shouldBe 0
            }
        }
        "Board with cell at (0,0) and (1,1)"{
            should("cell at (0,0) should contain neighbour (1,1)"){
                boardWithTwo.getNeighbours(0,0) shouldBe ImmutableSet.Builder<Pair<Int, Int>>().add(Pair(1,1)).build()
            }
            should("cell at (0,0) should has one neighbour"){
                boardWithTwo.getNeighboursCount(0,0) shouldBe 1
            }
            should("cell at (1,1) should has one neighbour"){
                boardWithTwo.getNeighboursCount(1,1) shouldBe 1
            }
            should("cell at (2,2) should has one neighbour"){
                boardWithTwo.getNeighboursCount(2,2) shouldBe 1
            }
            should("cell at (3,3) should has no neighbour"){
                boardWithTwo.getNeighboursCount(3,3) shouldBe 0
            }
            should("be empty after one time step"){
                boardWithTwo.doTimeStep() shouldBe emptyBoard
            }
            should("contain one active cell") {
                boardWithTwo.activeCells() shouldBe 2
            }
            should("have no active neighbours expect cell at (1,1)"){
                boardWithTwo.getInactiveNeighbours(0,0) shouldBe ImmutableSet.Builder<Pair<Int, Int>>()
                        .add(Pair<Int,Int>(-2,-2))
                        .add(Pair<Int,Int>(-2,-1))
                        .add(Pair<Int,Int>(-2,0))
                        .add(Pair<Int,Int>(-2,1))
                        .add(Pair<Int,Int>(-2,2))
                        .add(Pair<Int,Int>(-1,-2))
                        .add(Pair<Int,Int>(-1,-1))
                        .add(Pair<Int,Int>(-1,0))
                        .add(Pair<Int,Int>(-1,1))
                        .add(Pair<Int,Int>(-1,2))
                        .add(Pair<Int,Int>(0,2))
                        .add(Pair<Int,Int>(0,1))
                        .add(Pair<Int,Int>(0,-1))
                        .add(Pair<Int,Int>(0,-2))
                        .add(Pair<Int,Int>(1,-2))
                        .add(Pair<Int,Int>(1,-1))
                        .add(Pair<Int,Int>(1,0))
                        .add(Pair<Int,Int>(1,2))
                        .add(Pair<Int,Int>(2,-2))
                        .add(Pair<Int,Int>(2,-1))
                        .add(Pair<Int,Int>(2,0))
                        .add(Pair<Int,Int>(2,1))
                        .add(Pair<Int,Int>(2,2))
                        .build()
            }
        }
    }
}



