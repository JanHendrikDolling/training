import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableSet
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.specs.ShouldSpec

fun pulsarQuatuorGenerator(): Board {
    var builder = ImmutableMap.Builder<Pair<Int,Int>, Boolean>()
    //gen upper left board
    for (x in (-4)..(-2)){
        builder.put(Pair(x, 1), alive)
        builder.put(Pair(x, 6), alive)
    }
    for (y in 2..4){
        builder.put(Pair(-1,y), alive)
        builder.put(Pair(-6,y), alive)
    }
    return Board(builder.build())
}

fun pulsarGenerator(): Board {
    var upperLeft = pulsarQuatuorGenerator()
    var upperRight = upperLeft.transform(rotate90degree)
    var downRight = upperRight.transform(rotate90degree)
    var downLeft = downRight.transform(rotate90degree)
    return upperLeft.placeAll(upperRight).placeAll(downRight).placeAll(downLeft)
}

fun pentadecathlonGenertor(): Board {
    var b1 = ImmutableMap.Builder<Pair<Int,Int>, Boolean>()
    for(x in 0..9){
        if (x == 2 || x == 7) continue
        b1.put(Pair(x,0), alive)
    }
    b1.put(Pair(2,1), alive)
    b1.put(Pair(2,-1), alive)
    b1.put(Pair(7,1), alive)
    b1.put(Pair(7,-1), alive)
    return Board(b1.build())
}


val block = Board().place(0,0).place(1,1).place(0,1).place(1,0)
var beaconStep1 = Board().place(0,0).place(1,1).place(0,1).place(1,0).place(2,-1).place(2,-2).place(3,-1).place(3,-2)
var beaconStep2 = beaconStep1.remove(1,0).remove(2,-1)
var pulsar = pulsarGenerator()
var pentadecathlon = pentadecathlonGenertor()

class GameOfLivePatterns  : ShouldSpec() {
    init{
        "Block"{
            should("contain four active cell"){
                block.activeCells() shouldBe 4
            }
            should("cell at (0,0) should has four neighbour"){
                block.getNeighboursCount(0,0) shouldBe 3
            }
            should("cell at (0,0) should contain neighbour (1,1), (0,1) and (1,0)"){
                block.getNeighbours(0,0) shouldBe ImmutableSet.Builder<Pair<Int, Int>>()
                        .add(Pair(1,1))
                        .add(Pair(0,1))
                        .add(Pair(1,0))
                        .build()
            }
            should("still life after one iteration"){
                block.doTimeStep() shouldBe block
            }
        }
        "Beacon"{
            should("at step one contain eight active cell"){
                beaconStep1.activeCells() shouldBe 8
            }
            should("at step two contain eight active cell"){
                beaconStep2.activeCells() shouldBe 6
            }
            should("perform one step starting at step one"){
                beaconStep1.doTimeStep() shouldBe beaconStep2
            }
            should("perform one step starting at step two"){
                beaconStep2.doTimeStep() shouldBe beaconStep1
            }
        }
        "Pulsar" {
            should("have 12 active cells if only a quatuor is considered"){
                pulsarQuatuorGenerator().activeCells() shouldBe 12
            }
            should("have 48 active cells"){
                pulsar.activeCells() shouldBe 48
            }
            should("have period 3 Oscillators"){
                pulsar.doTimeStep().doTimeStep().doTimeStep() shouldBe pulsar
            }
        }
        "Pentadecathlon"{
            should("change after one step"){
                pentadecathlon.doTimeStep() shouldNotBe pentadecathlon
                pentadecathlon.doTimeStep().activeCells() shouldBe 22
            }
            should("have 12 active cells"){
                pentadecathlon.activeCells() shouldBe 12
            }
            should("have period 15 Oscillators"){
                pentadecathlon.doTimeStep()
                        .doTimeStep()
                        .doTimeStep()
                        .doTimeStep()
                        .doTimeStep()
                        .doTimeStep()
                        .doTimeStep()
                        .doTimeStep()
                        .doTimeStep()
                        .doTimeStep()
                        .doTimeStep()
                        .doTimeStep()
                        .doTimeStep()
                        .doTimeStep()
                        .doTimeStep() shouldBe pentadecathlon
            }
        }
    }
}