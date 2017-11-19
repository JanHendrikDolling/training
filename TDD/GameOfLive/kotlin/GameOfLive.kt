import com.google.common.collect.ImmutableSet
import com.google.common.collect.ImmutableMap

val alive = true
val dead = false

val identity = arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))
val rotate90degree = arrayOf(intArrayOf(0, 1), intArrayOf(-1, 0))
val rotate180degree = arrayOf(intArrayOf(-1, 0), intArrayOf(0, -1))
val rotate270degree = arrayOf(intArrayOf(0, -1), intArrayOf(1, 0))

fun cellAlive(cell: Boolean, neighbours_alive: Int) : Boolean = if (neighbours_alive in 2..3) if (neighbours_alive == 3) alive else cell else dead

//matrix vector multiplication (helper function for for 2D transformation)
fun transform(x: Int, y: Int, matrix: Array<IntArray>) = Pair(matrix[0][0] * x + matrix[0][1] * y, matrix[1][0] * x + matrix[1][1] * y)


class Board(private val grid: ImmutableMap<Pair<Int,Int>, Boolean> = ImmutableMap.Builder<Pair<Int,Int>, Boolean>().build()) {
    fun doTimeStep() : Board {
        var builder = ImmutableMap.Builder<Pair<Int,Int>, Boolean>()
        var testCells = HashSet<Pair<Int, Int>>()
        for (cell in this.grid){
            if (cellAlive(cell.value, this.getNeighboursCount(cell.key.first, cell.key.second))){
                builder.put(Pair(cell.key.first, cell.key.second), alive)
            }
            testCells.addAll(this.getInactiveNeighbours(cell.key.first, cell.key.second))
        }
        for (cell in testCells){
            if (cellAlive(dead, this.getNeighboursCount(cell.first, cell.second))){
                builder.put(Pair(cell.first, cell.second), alive)
            }
        }
        return Board(builder.build())
    }
    fun place(x: Int, y: Int): Board = Board(ImmutableMap.Builder<Pair<Int,Int>, Boolean>().putAll(grid).put(Pair(x,y), alive).build())
    fun placeAll(board: Board): Board = Board(ImmutableMap.Builder<Pair<Int,Int>, Boolean>().putAll(grid).putAll(board.grid).build())
    fun activeCells(): Int = grid.size
    override fun equals(other: Any?): Boolean {
        if (other !is Board) return false
        if (grid.size != other.grid.size) return false
        for (entry: MutableMap.MutableEntry<Pair<Int, Int>, Boolean> in grid) if (entry.key !in other.grid) return false
        return true
    }
    fun getNeighbours(x: Int, y: Int): ImmutableSet<Pair<Int, Int>> {
        var builder: ImmutableSet.Builder<Pair<Int, Int>> = ImmutableSet.Builder<Pair<Int, Int>>()
        for (i in (x-1)..(x+1))
            for (j in (y-1)..(y+1)) {
                if (this.grid.containsKey(Pair(i, j)) && !(i == x && j == y)) {
                    builder.add(Pair(i, j))
                }
            }
        return builder.build()
    }
    fun getNeighboursCount(x: Int, y: Int): Int = this.getNeighbours(x,y).size
    fun getInactiveNeighbours(x: Int, y: Int): ImmutableSet<Pair<Int, Int>> {
        var builder: ImmutableSet.Builder<Pair<Int, Int>> = ImmutableSet.Builder<Pair<Int, Int>>()
        for (i in (x-2)..(x+2))
            for (j in (y-2)..(y+2)) {
                if (!this.grid.containsKey(Pair(i, j)) && !(i == x && j == y)) {
                    builder.add(Pair(i, j))
                }
            }
        return builder.build()
    }
    fun remove(x: Int, y:Int): Board {
        var builder = ImmutableMap.Builder<Pair<Int,Int>, Boolean>()
        for (entry in this.grid)
            if (entry.key != Pair(x,y))
                builder.put(entry.key, entry.value)
        return Board(builder.build())
    }
    fun transform(rotationMatrix: Array<IntArray> = identity) : Board {
        var builder = ImmutableMap.Builder<Pair<Int,Int>, Boolean>()
        for (cell in this.grid){
            builder.put(transform(cell.key.first, cell.key.second, rotationMatrix), cell.value)
        }
        return Board(builder.build())
    }
}