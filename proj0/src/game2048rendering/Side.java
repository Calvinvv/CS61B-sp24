package game2048rendering;

/** Symbolic names for the four sides of a board.
 *  @author P. N. Hilfinger */
public enum Side {
    /** 每个
     * 以下符号指示 D 应解释如下：
     * 板的标准方向为板的顶部
     * 替换为 NORTH，并且行和列（请参阅模型）已编号
     * 从其左下角。考虑面向板
     * 因此，板的 D 侧离您最远。然后
     * * （COL0*s， ROW0*s） 是
     * 重定向板的左下角（其中 S 是
     * 板大小）和
     * * 如果 （x， y） 是某个
     * square，然后 （x+DCOL， y+DROW）
     * 是正方形的标准坐标
     * 在重新定位的板上。
     * 解决这个麻烦背后的想法是，通过使用
     * x（） 和 y（） 方法从 reoriented 转换为
     * 标准坐标，可以安排使用完全相同的代码
     * 计算在任何特定位置倾斜板的结果
     *方向。*/

    NORTH(0, 0, 0, 1),
    EAST(0, 1, 1, 0),
    SOUTH(1, 1, 0, -1),
    WEST(1, 0, -1, 0);

    /** The side that is in the direction (DCOL, DROW) from any square
     *  of the board.  Here, "direction (DCOL, DROW) means that to
     *  move one space in the direction of this Side increases the row
     *  by DROW and the colunn by DCOL.  (COL0, ROW0) are the row and
     *  column of the lower-left square when sitting at the board facing
     *  towards this Side. */
    Side(int col0, int row0, int dcol, int drow) {
        this._row0 = row0;
        this._col0 = col0;
        this._drow = drow;
        this._dcol = dcol;
    }

    /** Return the standard x-coordinate for square (x, y) on a board
     *  of size SIZE oriented with this Side on top. */
    int x(int x, int y, int size) {
        return _col0 * (size - 1) + x * _drow + y * _dcol;
    }

    /** Return the standard y-coordinate for square (x, y) on a board
     *  of size SIZE oriented with this Side on top. */
    int y(int x, int y, int size) {
        return _row0 * (size - 1) - x * _dcol + y * _drow;
    }

    /** Parameters describing this Side, as documented in the comment at the
     *  start of this class. */
    private final int _row0, _col0, _drow, _dcol;
}
