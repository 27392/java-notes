package cn.haohaoli.book.boast.simplefactory;

import java.util.Optional;

/**
 * 算法工厂
 * @author LiWenHao
 * @date 2019/2/25 11:29
 */
public class AlgorithmFactory {

    public static Algorithm createAlgorithm(String operator) {
        AlgorithmType algorithmType;
        switch (operator){
            case "+":
                algorithmType = AlgorithmType.ADDITION;
                break;
            case "-":
                algorithmType = AlgorithmType.SUBTRACTION;
                break;
            case "*":
                algorithmType = AlgorithmType.MULTIPLICATION;
                break;
            case "/":
                algorithmType = AlgorithmType.DIVISION;
                break;
            default:
                algorithmType = null;
                break;
        }
        Optional.ofNullable(algorithmType).orElseThrow(() -> new RuntimeException("运算符错误！"));
        return algorithmType.getAlgorithmSupplier().get();
    }
}
