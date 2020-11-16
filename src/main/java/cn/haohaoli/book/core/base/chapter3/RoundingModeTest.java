package cn.haohaoli.book.core.base.chapter3;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.RoundingMode.*;
import static java.math.RoundingMode.HALF_EVEN;

/**
 * @author lwh
 */
public class RoundingModeTest {

    public static void main(String[] args) {

        // 远离零方向舍入的舍入模式。始终对非零舍弃部分前面的数字加 1。注意，此舍入模式始终不会减少计算值的绝对值
        up();

        // 向零方向舍入的舍入模式。从不对舍弃部分前面的数字加 1（即截尾）。注意，此舍入模式始终不会增加计算值的绝对值。
        down();

        // 向正无限大方向舍入的舍入模式。如果结果为正，则舍入行为类似于 RoundingMode.UP；如果结果为负，则舍入行为类似于 RoundingMode.DOWN。注意，此舍入模式始终不会减少计算值。
        ceiling();

        // 向负无限大方向舍入的舍入模式。如果结果为正，则舍入行为类似于 RoundingMode.DOWN；如果结果为负，则舍入行为类似于 RoundingMode.UP。注意，此舍入模式始终不会增加计算值。
        floor();

        // 向最接近数字方向舍入的舍入模式，如果与两个相邻数字的距离相等，则向上舍入。如果被舍弃部分 >= 0.5，则舍入行为同 RoundingMode.UP；否则舍入行为同 RoundingMode.DOWN。注意，此舍入模式就是通常学校里讲的四舍五入
        halfUp();

        // 向最接近数字方向舍入的舍入模式，如果与两个相邻数字的距离相等，则向下舍入。如果被舍弃部分 > 0.5，则舍入行为同 RoundingMode.UP；否则舍入行为同 RoundingMode.DOWN。
        halfDown();

        // 向最接近数字方向舍入的舍入模式，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。如果舍弃部分左边的数字为奇数，则舍入行为同 RoundingMode.HALF_UP；如果为偶数，则舍入行为同 RoundingMode.HALF_DOWN。注意，在重复进行一系列计算时，此舍入模式可以在统计上将累加错误减到最小。此舍入模式也称为“银行家舍入法”，主要在美国使用。此舍入模式类似于 Java 中对 float 和 double 算法使用的舍入策略。
        halfEven();
    }

    /**
     * 远离零方向舍入的舍入模式。始终对非零舍弃部分前面的数字加 1。注意，此舍入模式始终不会减少计算值的绝对值
     *
     * @see RoundingMode#UP
     */
    public static void up() {
        assertEquals(new BigDecimal("2.5").setScale(0, UP), BigDecimal.valueOf(3));
        assertEquals(new BigDecimal("1.6").setScale(0, UP), BigDecimal.valueOf(2));
        assertEquals(new BigDecimal("2.53").setScale(1, UP), BigDecimal.valueOf(2.6));
        assertEquals(new BigDecimal("1.64").setScale(1, UP), BigDecimal.valueOf(1.7));
        assertEquals(new BigDecimal("1.16").setScale(1, UP), BigDecimal.valueOf(1.2));
        assertEquals(new BigDecimal("1.00").setScale(1, UP), BigDecimal.valueOf(1.0));

        assertEquals(new BigDecimal("-1.0").setScale(0, UP), BigDecimal.valueOf(-1));
        assertEquals(new BigDecimal("-1.11").setScale(1, UP), BigDecimal.valueOf(-1.2));
        assertEquals(new BigDecimal("-1.66").setScale(1, UP), BigDecimal.valueOf(-1.7));
        assertEquals(new BigDecimal("-2.58").setScale(1, UP), BigDecimal.valueOf(-2.6));
        assertEquals(new BigDecimal("-5.55").setScale(1, UP), BigDecimal.valueOf(-5.6));

    }

    /**
     * 向零方向舍入的舍入模式.从不对舍弃部分前面的数字加 1（即截尾）.注意,此舍入模式始终不会增加计算值的绝对值.
     *
     * @see RoundingMode#DOWN
     */
    public static void down() {

        assertEquals(new BigDecimal("2.9").setScale(0, DOWN), BigDecimal.valueOf(2));
        assertEquals(new BigDecimal("1.2").setScale(0, DOWN), BigDecimal.valueOf(1));
        assertEquals(new BigDecimal("2.51").setScale(1, DOWN), BigDecimal.valueOf(2.5));
        assertEquals(new BigDecimal("1.66").setScale(1, DOWN), BigDecimal.valueOf(1.6));
        assertEquals(new BigDecimal("1.16").setScale(1, DOWN), BigDecimal.valueOf(1.1));
        assertEquals(new BigDecimal("1.00").setScale(1, DOWN), BigDecimal.valueOf(1.0));

        assertEquals(new BigDecimal("-1.0").setScale(0, DOWN), BigDecimal.valueOf(-1));
        assertEquals(new BigDecimal("-1.12").setScale(1, DOWN), BigDecimal.valueOf(-1.1));
        assertEquals(new BigDecimal("-1.66").setScale(1, DOWN), BigDecimal.valueOf(-1.6));
        assertEquals(new BigDecimal("-2.58").setScale(1, DOWN), BigDecimal.valueOf(-2.5));
        assertEquals(new BigDecimal("-5.55").setScale(1, DOWN), BigDecimal.valueOf(-5.5));
    }

    /**
     * 向正无限大方向舍入的舍入模式。如果结果为正，则舍入行为类似于 RoundingMode.UP；如果结果为负，则舍入行为类似于 RoundingMode.DOWN。注意，此舍入模式始终不会减少计算值。
     *
     * @see RoundingMode#CEILING
     */
    public static void ceiling() {
        assertEquals(new BigDecimal("2.8").setScale(0, CEILING), BigDecimal.valueOf(3));
        assertEquals(new BigDecimal("1.1").setScale(0, CEILING), BigDecimal.valueOf(2));
        assertEquals(new BigDecimal("2.59").setScale(1, CEILING), BigDecimal.valueOf(2.6));
        assertEquals(new BigDecimal("1.62").setScale(1, CEILING), BigDecimal.valueOf(1.7));
        assertEquals(new BigDecimal("1.15").setScale(1, CEILING), BigDecimal.valueOf(1.2));
        assertEquals(new BigDecimal("1.00").setScale(1, CEILING), BigDecimal.valueOf(1.0));

        assertEquals(new BigDecimal("-1.0").setScale(0, CEILING), BigDecimal.valueOf(-1));
        assertEquals(new BigDecimal("-1.11").setScale(1, CEILING), BigDecimal.valueOf(-1.1));
        assertEquals(new BigDecimal("-1.66").setScale(1, CEILING), BigDecimal.valueOf(-1.6));
        assertEquals(new BigDecimal("-2.58").setScale(1, CEILING), BigDecimal.valueOf(-2.5));
        assertEquals(new BigDecimal("-5.55").setScale(1, CEILING), BigDecimal.valueOf(-5.5));
    }

    /**
     * 向负无限大方向舍入的舍入模式。如果结果为正，则舍入行为类似于 RoundingMode.DOWN；如果结果为负，则舍入行为类似于 RoundingMode.UP。注意，此舍入模式始终不会增加计算值。
     *
     * @see RoundingMode#FLOOR
     */
    public static void floor() {
        assertEquals(new BigDecimal("2.5").setScale(0, FLOOR), BigDecimal.valueOf(2));
        assertEquals(new BigDecimal("1.6").setScale(0, FLOOR), BigDecimal.valueOf(1));
        assertEquals(new BigDecimal("2.53").setScale(1, FLOOR), BigDecimal.valueOf(2.5));
        assertEquals(new BigDecimal("1.64").setScale(1, FLOOR), BigDecimal.valueOf(1.6));
        assertEquals(new BigDecimal("1.16").setScale(1, FLOOR), BigDecimal.valueOf(1.1));
        assertEquals(new BigDecimal("1.00").setScale(1, FLOOR), BigDecimal.valueOf(1.0));

        assertEquals(new BigDecimal("-1.0").setScale(0, FLOOR), BigDecimal.valueOf(-1));
        assertEquals(new BigDecimal("-1.18").setScale(1, FLOOR), BigDecimal.valueOf(-1.2));
        assertEquals(new BigDecimal("-1.66").setScale(1, FLOOR), BigDecimal.valueOf(-1.7));
        assertEquals(new BigDecimal("-2.58").setScale(1, FLOOR), BigDecimal.valueOf(-2.6));
        assertEquals(new BigDecimal("-5.55").setScale(1, FLOOR), BigDecimal.valueOf(-5.6));
    }

    /**
     * 向最接近数字方向舍入的舍入模式，如果与两个相邻数字的距离相等，则向上舍入。如果被舍弃部分 >= 0.5，则舍入行为同 RoundingMode.UP；否则舍入行为同 RoundingMode.DOWN。注意，此舍入模式就是通常学校里讲的四舍五入
     * <p>
     * 四舍五入
     *
     * @see RoundingMode#HALF_UP
     */
    public static void halfUp() {
        assertEquals(new BigDecimal("2.5").setScale(0, HALF_UP), BigDecimal.valueOf(3));
        assertEquals(new BigDecimal("1.6").setScale(0, HALF_UP), BigDecimal.valueOf(2));
        assertEquals(new BigDecimal("2.54").setScale(1, HALF_UP), BigDecimal.valueOf(2.5));
        assertEquals(new BigDecimal("1.69").setScale(1, HALF_UP), BigDecimal.valueOf(1.7));
        assertEquals(new BigDecimal("1.13").setScale(1, HALF_UP), BigDecimal.valueOf(1.1));
        assertEquals(new BigDecimal("1.00").setScale(1, HALF_UP), BigDecimal.valueOf(1.0));

        assertEquals(new BigDecimal("-1.0").setScale(0, HALF_UP), BigDecimal.valueOf(-1));
        assertEquals(new BigDecimal("-1.11").setScale(1, HALF_UP), BigDecimal.valueOf(-1.1));
        assertEquals(new BigDecimal("-1.66").setScale(1, HALF_UP), BigDecimal.valueOf(-1.7));
        assertEquals(new BigDecimal("-2.58").setScale(1, HALF_UP), BigDecimal.valueOf(-2.6));
        assertEquals(new BigDecimal("-5.55").setScale(1, HALF_UP), BigDecimal.valueOf(-5.6));
    }

    /**
     * 向最接近数字方向舍入的舍入模式，如果与两个相邻数字的距离相等，则向下舍入。如果被舍弃部分 > 0.5,则舍入行为同 RoundingMode.UP;否则舍入行为同 RoundingMode.DOWN。
     * <p>
     * 五舍六入
     * @see RoundingMode#HALF_DOWN
     */
    public static void halfDown() {
        assertEquals(new BigDecimal("2.5").setScale(0, HALF_DOWN), BigDecimal.valueOf(2));
        assertEquals(new BigDecimal("1.6").setScale(0, HALF_DOWN), BigDecimal.valueOf(2));
        assertEquals(new BigDecimal("2.55").setScale(1, HALF_DOWN), BigDecimal.valueOf(2.5));
        assertEquals(new BigDecimal("1.64").setScale(1, HALF_DOWN), BigDecimal.valueOf(1.6));
        assertEquals(new BigDecimal("1.16").setScale(1, HALF_DOWN), BigDecimal.valueOf(1.2));
        assertEquals(new BigDecimal("1.00").setScale(1, HALF_DOWN), BigDecimal.valueOf(1.0));

        assertEquals(new BigDecimal("-1.0").setScale(0, HALF_DOWN), BigDecimal.valueOf(-1));
        assertEquals(new BigDecimal("-1.11").setScale(1, HALF_DOWN), BigDecimal.valueOf(-1.1));
        assertEquals(new BigDecimal("-1.66").setScale(1, HALF_DOWN), BigDecimal.valueOf(-1.7));
        assertEquals(new BigDecimal("-2.58").setScale(1, HALF_DOWN), BigDecimal.valueOf(-2.6));
        assertEquals(new BigDecimal("-5.55").setScale(1, HALF_DOWN), BigDecimal.valueOf(-5.5));
    }

    /**
     * 向最接近数字方向舍入的舍入模式，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。如果舍弃部分左边的数字为奇数，则舍入行为同 RoundingMode.HALF_UP；如果为偶数，则舍入行为同 RoundingMode.HALF_DOWN。注意，在重复进行一系列计算时，此舍入模式可以在统计上将累加错误减到最小。此舍入模式也称为“银行家舍入法”，主要在美国使用。此舍入模式类似于 Java 中对 float 和 double 算法使用的舍入策略。
     * <p>
     * 目标是偶数，可以使用四舍五入，也可以适用五舍六入
     *
     * @see RoundingMode#HALF_EVEN
     */
    public static void halfEven() {
        assertEquals(new BigDecimal("9.5").setScale(0, HALF_EVEN), BigDecimal.valueOf(10));
        assertEquals(new BigDecimal("1.6").setScale(0, HALF_EVEN), BigDecimal.valueOf(2));
        assertEquals(new BigDecimal("2.53").setScale(1, HALF_EVEN), BigDecimal.valueOf(2.5));
        assertEquals(new BigDecimal("1.64").setScale(1, HALF_EVEN), BigDecimal.valueOf(1.6));
        assertEquals(new BigDecimal("2.16").setScale(1, HALF_EVEN), BigDecimal.valueOf(2.2));
        assertEquals(new BigDecimal("1.00").setScale(1, HALF_EVEN), BigDecimal.valueOf(1.0));

        assertEquals(new BigDecimal("-1.15").setScale(1, HALF_EVEN), BigDecimal.valueOf(-1.2));
        assertEquals(new BigDecimal("-2.25").setScale(1, HALF_EVEN), BigDecimal.valueOf(-2.2));
        assertEquals(new BigDecimal("-3.65").setScale(1, HALF_EVEN), BigDecimal.valueOf(-3.6));
        assertEquals(new BigDecimal("-4.95").setScale(1, HALF_EVEN), BigDecimal.valueOf(-5.0));
        assertEquals(new BigDecimal("-5.35").setScale(1, HALF_EVEN), BigDecimal.valueOf(-5.4));
    }

    public static void assertEquals(BigDecimal o, BigDecimal o1) {
        if (o.equals(o1)) {
            return;
        }
        throw new RuntimeException("o: " + o + ", " + "o1: " + o1);
    }
}
