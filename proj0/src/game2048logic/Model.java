package game2048logic;

import game2048rendering.Board;
import game2048rendering.Side;
import game2048rendering.Tile;

import java.util.Formatter;


/** The state of a game of 2048.
 *  @author P. N. Hilfinger + Josh Hug
 */
public class Model {
    /** 板的当前内容。*/
    private final Board board;
    /** 当前分数。*/
    private int score;

    /* 坐标系：x列，板的y行（其中x = 0，
     * y = 0 是板的左下角）将对应
     * 更改为 board.tile（x， y）。 小心！
     */
    /** 最大件价值。*/
    public static final int MAX_PIECE = 2048;

    /** 一个新的 2048 游戏，在大小为 SIZE 的棋盘上，没有棋子
    * 和得分 0。*/
    public Model(int size) {
        board = new Board(size);
        score = 0;
    }

    /** 一个新的 2048 游戏，其中 RAWVALUES 包含图块的值
     * （如果为 null，则为 0）。VALUES 由 （x， y） 索引，其中 （0， 0） 对应
     * 到左下角。用于测试目的。*/
    public Model(int[][] rawValues, int score) {
        board = new Board(rawValues);
        this.score = score;
    }

    /** 返回 (x, y) 处的当前 Tile，其中 0 <= x < size()，
   * 0 <= y < 大小()。如果那里没有图块，则返回 null。
   * 用于测试。 */
    public Tile tile(int x, int y) {
        return board.tile(x, y);
    }

    /** Return the number of squares on one side of the board. */
    public int size() {
        return board.size();
    }

    /** 返回当前分数。*/
    public int score() {
        return score;
    }


    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        board.clear();
    }

    /** 将 TILE 添加到板中。当前
     * 相同位置。*/
    public void addTile(Tile tile) {
        board.addTile(tile);
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        return maxTileExists() || !atLeastOneMoveExists();
    }

    /** Returns this Model's board. */
    public Board getBoard() {
        return board;
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public boolean emptySpaceExists() {
        // TODO: Task 2. Fill in this function.
        int size = size();
        boolean empty = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tile(i, j) == null) empty = true;
            }
        }
        return empty;
    }

    /**
     * 如果任何图块等于最大有效值，则返回 true。
     * 最大有效值由此给出。MAX_PIECE。请注意，
     * 给定一个 Tile 对象 t，我们使用 t.value（） 获取其值。
     */
    public boolean maxTileExists() {
        // TODO: Task 3. Fill in this function.
        int size = size();
        boolean maxTile = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tile(i, j) == null) continue;
                if (tile(i, j).value() == MAX_PIECE) maxTile = true;
            }
        }
        return maxTile;


    }

    /**
     * 如果棋盘上有任何有效的移动，则返回 true。
     * 有两种方法可以有有效的移动：
     * 1.棋盘上至少有一个空白区域。
     * 2.有两个具有相同值的相邻图块。
     */
    public boolean atLeastOneMoveExists() {
        // TODO: Fill in this function.
        if (emptySpaceExists()) return true;
        int size = size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size; j++) {
                if (tile(i, j) == null) continue;
                if (tile(i, j).value() == tile(i + 1, j).value()) return true;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (tile(i, j) == null) continue;
                if (tile(i, j).value() == tile(i, j + 1).value()) return true;
            }
        }
        return false;
    }

    /**
     * 将位置 （x， y） 处的图块尽可能向上移动。
     *
     * Tilt 规则：
     * 1.如果两个瓦片在运动方向上相邻，并且具有
     * 相同的值，它们被合并为一个 Tile 是原始值的两倍
     * 值，并将该新值添加到 SCORE 实例变量中
     * 2.作为合并结果的瓦片不会在该
     *倾斜。因此，每一步、每张图块最多只会成为 1 的一部分
     * merge （可能为零）。
     * 3.当运动方向上的三个相邻图块具有相同的
     * 值，则运动方向上的前两个图块合并，
     * 和尾随图块则不会。
     */
    public void moveTileUpAsFarAsPossible(int x, int y) {
        Tile currTile = board.tile(x, y);
        int myValue = currTile.value();
        int targetY = y;
        for (int j = y + 1; j < board.size(); j++) {
            if (tile(x, j) == null) { //空
                targetY = j;
            } else if (tile(x, j).value() == currTile.value() && (!tile(x, j).wasMerged())) { //同
                targetY = j;
                score += tile(x, j).value()  * 2;
                break;
            } else {
                break;
            }
        }
        board.move(x, targetY, currTile);
        // TODO: Tasks 5, 6, and 10. Fill in this function.
    }

    /** 处理电路板 x 列的倾斜运动
     * 通过将列中的每个图块尽可能向上移动。
     * 已设置观看视角，
     * 因此，我们将此列中的图块向上倾斜。
     * */
    public void tiltColumn(int x) {
        // TODO: Task 7. Fill in this function.
        for (int i = board.size() - 2; i >= 0; i--) {
            if (tile(x, i) != null) moveTileUpAsFarAsPossible(x, i);
        }

    }

    public void tilt(Side side) {
        // TODO: Tasks 8 and 9. Fill in this function.
        board.setViewingPerspective(side);
        for (int i = 0; i < board.size(); i++) {
            tiltColumn(i);
        }
        board.setViewingPerspective(Side.NORTH);
    }

    /** 将电路板的每一列都向 SIDE 倾斜。
     */
    public void tiltWrapper(Side side) {
        board.resetMerged();
        tilt(side);
    }


    @Override
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int y = size() - 1; y >= 0; y -= 1) {
            for (int x = 0; x < size(); x += 1) {
                if (tile(x, y) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(x, y).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (game is %s) %n", score(), over);
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Model m) && this.toString().equals(m.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
