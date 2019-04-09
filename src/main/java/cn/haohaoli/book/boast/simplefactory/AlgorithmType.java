package cn.haohaoli.book.boast.simplefactory;

import cn.haohaoli.book.boast.simplefactory.impl.Addition;
import cn.haohaoli.book.boast.simplefactory.impl.Division;
import cn.haohaoli.book.boast.simplefactory.impl.Multiplication;
import cn.haohaoli.book.boast.simplefactory.impl.Subtraction;

import java.util.function.Supplier;

/**
 * @author LiWenHao
 * @date 2019/2/25 11:30
 */
public enum AlgorithmType {

    ADDITION(Addition::new),
    SUBTRACTION(Subtraction::new),
    DIVISION(Division::new),
    MULTIPLICATION(Multiplication::new),
    ;

    private Supplier<Algorithm> algorithmSupplier;

    AlgorithmType(Supplier<Algorithm> algorithmSupplier) {
        this.algorithmSupplier = algorithmSupplier;
    }

    public Supplier<Algorithm> getAlgorithmSupplier() {
        return algorithmSupplier;
    }
}
