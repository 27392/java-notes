package cn.haohaoli.book.java8.chapter11;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.*;

/**
 * @author lwh
 */
public class CompletableFutureApiTest {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        // 创建对象
//        create();

        // 结果计算完成的处理(可以获取上一步的计算结果和异常信息)
//        whenComplete();

        // 异常处理(仅仅只可以处理异常,且只是在异常发生时才会调用)
//        exceptionally();

        // 处理(可以获取上一步的处理结果包括异常,并返回新的值)
//        handle();

        // 转换(可以获取上一步的处理结果,并返回新的值).类似`map`
//        thenApply();

        // 消费(可以获取上一步的处理结果,无返回值)
//        thenAccept();

        // 消费(无法获取上一步的处理结果,无返回值)
//        thenRun();

        // 组合
//        thenCompose();

        // 组合(在组合[两个]处理完成后调用.可以获取上一步组合的处理结果,有返回值)
//        thenCombine();

        // 组合(在组合[两个]处理完成后调用.可以获取上一步组合的处理结果,无返回值)
//        thenAcceptBoth();

        // 组合(在组合[两个]处理完成后调用.无法获取上一步的处理结果,无返回值)
//        runAfterBoth();

        // 组合(在组合[两个]任意一个处理完成后调用.可以获取上一步先完成处理结果,有返回值)
//        applyToEither();

        // 组合(在组合[两个]任意一个处理完成后调用.可以获取上一步先完成处理结果,无返回值)
//        acceptEither();

        // 组合(在组合[两个]任意一个处理完成后调用.无法获取上一步先完成处理结果,无返回值)
//        runAfterEither();

        executorService.shutdown();
    }

    /**
     * 创建`CompletableFuture`
     * `supplyAsync`有返回值,`runAsync`无返回值
     *
     * @see CompletableFuture#supplyAsync(Supplier)
     * @see CompletableFuture#supplyAsync(Supplier, Executor)
     * @see CompletableFuture#runAsync(Runnable)
     * @see CompletableFuture#runAsync(Runnable, Executor)
     */
    public static void create() {

        // 有返回值
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "1";
        });

        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync + executor: " + Thread.currentThread().getName());
            return "1";
        }, executorService);

        // 无返回值
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync: " + Thread.currentThread().getName());
            System.out.println("1");
        });
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync + executor: " + Thread.currentThread().getName());
            System.out.println("1");
        }, executorService);
    }

    /**
     * 结果计算完成的处理(同时可以处理异常)
     *
     * @see CompletableFuture#whenComplete(BiConsumer)
     * @see CompletableFuture#whenCompleteAsync(BiConsumer) 异步
     * @see CompletableFuture#whenCompleteAsync(BiConsumer, Executor) 异步
     */
    public static void whenComplete() {
        CompletableFuture<String> exceptionally = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            int i = 1 / 0;
            return "1";
        }).whenComplete((r, e) -> {
            System.out.println("whenComplete: " + Thread.currentThread().getName());
            System.out.println("result: " + r);
            System.out.println("exception: " + e);
        }).whenCompleteAsync((r, e) -> {
            System.out.println("whenCompleteAsync: " + Thread.currentThread().getName());
            System.out.println("result: " + r);
            System.out.println("exception: " + e);
        }).whenCompleteAsync((r, e) -> {
            System.out.println("whenCompleteAsync + executor: " + Thread.currentThread().getName());
            System.out.println("result: " + r);
            System.out.println("exception: " + e);
        }, executorService).exceptionally(e -> {
            System.out.println("exceptionally: " + Thread.currentThread().getName());
            return "2";
        });

        System.out.println(exceptionally.join());
    }

    /**
     * 处理异常
     * 注意: 该方法在未发生异常的情况的话并不会调用
     * 可以把它理解为是一个事件,一个订阅了异常的事件
     *
     * @see CompletableFuture#exceptionally(Function)
     */
    public static void exceptionally() {

        // 发生异常的情况
        CompletableFuture<String> exceptionally1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            int i = 1 / 0;
            return "1";
        }).exceptionally(e -> {
            System.out.println("exceptionally: " + Thread.currentThread().getName());
            System.out.println("exception: " + e);
            // 返回默认值
            return "10";
        });
        System.out.println("exceptionally1 result: " + exceptionally1.join());  // 10

        // 未发生异常的情况
        CompletableFuture<String> exceptionally2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "1";
        }).exceptionally(e -> {
            System.out.println("exceptionally: " + Thread.currentThread().getName());
            System.out.println("exception: " + e);
            // 返回默认值
            return "10";
        });

        System.out.println("exceptionally2 result: " + exceptionally2.join());  // 1
    }

    /**
     * 处理(异常与转换)
     * 该方法与{@link CompletableFuture#exceptionally(Function)}的区别是:无论发生异常与否都调用`handle`方法
     * 在发生异常时;可以获取异常信息
     * 在为发生异常时;可以获取到上一步的返回值
     *
     * @see CompletableFuture#handle(BiFunction)
     * @see CompletableFuture#handleAsync(BiFunction) 异步
     * @see CompletableFuture#handleAsync(BiFunction, Executor) 异步
     */
    public static void handle() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            int i = 1 / 0;
            return "1";
        }).handle((r, e) -> {
            System.out.println("handle: " + Thread.currentThread().getName());
            System.out.println("result: " + r);
            System.out.println("exception: " + e);
            return Objects.nonNull(e) ? "10" : r + "_";
        }).handleAsync((r, e) -> {
            System.out.println("handleAsync: " + Thread.currentThread().getName());
            System.out.println("result: " + r);
            System.out.println("exception: " + e);
            return Objects.nonNull(e) ? "20" : r + "_";
        }).handleAsync((r, e) -> {
            System.out.println("handleAsync + executor: " + Thread.currentThread().getName());
            System.out.println("result: " + r);
            System.out.println("exception: " + e);
            return Objects.nonNull(e) ? "30" : r + "_";
        }, executorService);

        System.out.println("result: " + stringCompletableFuture.join());
    }

    // 串行化~

    /**
     * 转换(接收上一步的值,有返回值)
     * 类似`map`操作
     *
     * @see CompletableFuture#thenApply(Function)
     * @see CompletableFuture#thenApplyAsync(Function) 异步
     * @see CompletableFuture#thenApplyAsync(Function, Executor) 异步
     */
    public static void thenApply() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            int i = 1 / 0;
            return "1";
        }).exceptionally((e) -> {
            System.out.println("exceptionally: " + Thread.currentThread().getName());
            return "10";
        }).thenApply(r -> {
            System.out.println("thenApply: " + Thread.currentThread().getName());
            return r + "0";
        }).thenApplyAsync(r -> {
            System.out.println("thenApply: " + Thread.currentThread().getName());
            return r + "0";
        }).thenApplyAsync(r -> {
            System.out.println("thenApply: " + Thread.currentThread().getName());
            return r + "0";
        }, executorService);

        System.out.println(stringCompletableFuture.join());
    }

    /**
     * 消费(接收上一步的值,无返回值)
     *
     * @see CompletableFuture#thenAccept(Consumer)
     * @see CompletableFuture#thenAcceptAsync(Consumer) 异步
     * @see CompletableFuture#thenAcceptAsync(Consumer, Executor)   异步
     */
    public static void thenAccept() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "1";
        }).thenAcceptAsync(r -> {
            System.out.println("thenAccept: " + Thread.currentThread().getName());
            System.out.println("r: " + r);
        }, executorService);
    }

    /**
     * 消费(无法接受上一步的值,无返回值)
     *
     * @see CompletableFuture#thenRun(Runnable)
     * @see CompletableFuture#thenRunAsync(Runnable)
     * @see CompletableFuture#thenRunAsync(Runnable, Executor)
     */
    public static void thenRun() {
        CompletableFuture.runAsync(() -> {
            System.out.println("runAsync: " + Thread.currentThread().getName());
        }).thenRun(() -> {
            System.out.println("thenRun: " + Thread.currentThread().getName());
        }).thenRunAsync(() -> {
            System.out.println("thenRunAsync: " + Thread.currentThread().getName());
        }).thenRunAsync(() -> {
            System.out.println("thenRunAsync + executor: " + Thread.currentThread().getName());
        }, executorService);
    }

    /**
     * 转换(与`thenApply`一致)
     * 类似于`flatMap`
     *
     * @see CompletableFuture#thenCompose(Function)
     * @see CompletableFuture#thenComposeAsync(Function)
     * @see CompletableFuture#thenComposeAsync(Function, Executor)
     */
    public static void thenCompose() {
        CompletableFuture<Integer> integerFuture = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<String>  stringFuture  = CompletableFuture.supplyAsync(() -> "20");

        // map
        CompletableFuture<CompletableFuture<String>> mapFuture = integerFuture.thenApply((t) -> stringFuture);

        // flatMap
        CompletableFuture<String> flatMapFuture = integerFuture.thenCompose((t) -> stringFuture);

        System.out.println(mapFuture.join().join());
        System.out.println(flatMapFuture.join());
    }

    // 组合~

    /**
     * 组合(组合两个`CompletableFuture`两个都要完成[接收两个的返回值,并返回当前任务的返回值])
     *
     * @see CompletableFuture#thenCombine(CompletionStage, BiFunction)
     * @see CompletableFuture#thenCombineAsync(CompletionStage, BiFunction)
     * @see CompletableFuture#thenCombineAsync(CompletionStage, BiFunction, Executor)
     */
    public static void thenCombine() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "10";
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "20";
        }), (r1, r2) -> {
            System.out.println("thenCombine: " + Thread.currentThread().getName());
            System.out.println("r1: " + r1);
            System.out.println("r2: " + r2);
            return r1 + r2;
        }, executorService);

        System.out.println(stringCompletableFuture.join());
    }

    /**
     * 组合(组合两个`CompletableFuture`进行消费两个都要完成[接收两个的返回值,无返回值])
     *
     * @see CompletableFuture#thenAcceptBoth(CompletionStage, BiConsumer)
     * @see CompletableFuture#thenAcceptBothAsync(CompletionStage, BiConsumer)  异步
     * @see CompletableFuture#thenAcceptBothAsync(CompletionStage, BiConsumer, Executor)    异步
     */
    public static void thenAcceptBoth() {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "1";
        }).thenAcceptBothAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "2";
        }), (r1, r2) -> {
            // 两个都完成后才会到此方法
            System.out.println("r1: " + r1);
            System.out.println("r2: " + r2);
        }, executorService);

        completableFuture.join();
    }

    /**
     * 组合(组合两个`CompletableFuture`两个都要完成[无法接收上一步返回值,无返回值])
     *
     * @see CompletableFuture#runAfterBoth(CompletionStage, Runnable)
     * @see CompletableFuture#runAfterBothAsync(CompletionStage, Runnable)
     * @see CompletableFuture#runAfterBothAsync(CompletionStage, Runnable, Executor)
     */
    public static void runAfterBoth() {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "10";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            return "20";
        }), () -> {
            System.out.println("runAfterBoth: " + Thread.currentThread().getName());
        }, executorService);
    }

    /**
     * 任何一个完成(可以获取的返回值,处理任务并返回新的返回值)
     *
     * @see CompletableFuture#applyToEither(CompletionStage, Function)
     * @see CompletableFuture#applyToEitherAsync(CompletionStage, Function)
     * @see CompletableFuture#applyToEitherAsync(CompletionStage, Function, Executor)
     */
    public static void applyToEither() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "10";
        }).applyToEitherAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "20";
        }), (r) -> {
            System.out.println("runAfterBoth: " + Thread.currentThread().getName());
            return "_" + r + "_";
        }, executorService);

        System.out.println(stringCompletableFuture.join());
    }

    /**
     * 任何一个完成(可以获取的返回值,无返回值)
     *
     * @see CompletableFuture#acceptEither(CompletionStage, Consumer)
     * @see CompletableFuture#acceptEitherAsync(CompletionStage, Consumer)
     * @see CompletableFuture#acceptEitherAsync(CompletionStage, Consumer, Executor)
     */
    public static void acceptEither() {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "10";
        }).acceptEitherAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "20";
        }), (r) -> {
            System.out.println("runAfterBoth: " + Thread.currentThread().getName());
            System.out.println("result: " + r);
        }, executorService);

        completableFuture.join();
    }

    /**
     * 任何一个完成(无法获取的返回值,无返回值)
     *
     * @see CompletableFuture#runAfterEither(CompletionStage, Runnable)
     * @see CompletableFuture#runAfterEitherAsync(CompletionStage, Runnable)
     * @see CompletableFuture#runAfterEitherAsync(CompletionStage, Runnable, Executor)
     */
    public static void runAfterEither() {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "10";
        }).runAfterEitherAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "20";
        }), () -> {
            System.out.println("runAfterBoth: " + Thread.currentThread().getName());
        }, executorService);

        completableFuture.join();
    }

}
