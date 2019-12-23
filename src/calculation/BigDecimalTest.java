package calculation;

import java.math.BigDecimal;

//对于高精度的数值计算， 一般选择用BigDecimal类，使用传字符串的构造函数，对于本身属于测量值而不是精确值的数值可以用double来表示
//字符串表示的数值计算没有误差，但速度较慢，只适用于表示精确值，不适合大量运算的场景。

/** round mode:
 * ROUND_CEILING    向正无穷方向舍入
 * ROUND_DOWN       向零方向舍入
 * ROUND_FLOOR      向负无穷方向舍入
 * ROUND_HALF_DOWN  向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向下舍入, 例如1.55 保留一位小数结果为1.5
 * ROUND_HALF_EVEN  向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，如果保留位数是奇数，使用ROUND_HALF_UP，如果是偶数，使用ROUND_HALF_DOWN
 * ROUND_HALF_UP    向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向上舍入, 1.55保留一位小数结果为1.6
 * ROUND_UNNECESSARY    //计算结果是精确的，不需要舍入模式
 * ROUND_UP    //向远离0的方向舍入
 */
public class BigDecimalTest {
    /**
     * public BigDecimal(char[] in);
     * public BigDecimal(String val);
     * public BigDecimal(BigInteger val);
     * public BigDecimal(int val);
     * public BigDecimal(long val);
     * public BigDecimal(double val);
     */
    public static void main(String[] args) {
        test1();
    }

    /** Output:
     * 11.1
     * 11.0999999999999996447286321199499070644378662109375
     */
    public static void test1() {
        BigDecimal o = new BigDecimal("11.1");
        BigDecimal p = new BigDecimal(11.1);
        System.out.println(o);
        System.out.println(p);
    }

}
