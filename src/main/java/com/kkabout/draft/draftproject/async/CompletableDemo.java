package com.kkabout.draft.draftproject.async;

import javax.xml.bind.SchemaOutputResolver;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;

/**
 * @author kangjian03@58.com
 * @date 2020/3/16
 **/
public class CompletableDemo {
    public static void main(String[] args) {
        thenAccept();
    }

    static void thenAccept() {
        StringBuilder result = new StringBuilder();
        String aaa = "123";
        CompletableFuture nf = CompletableFuture.completedFuture("nf").thenAcceptAsync(s -> {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture cf = CompletableFuture.completedFuture("method")
                .thenCombine(nf,(s1, s2) -> s1 + s2)
                .thenApply(s -> {
                    result.append(s);
                    System.out.println("bbbbbbbb");
                    return result;
                });
        System.out.println("bbbbbbbb");
        cf.join();
        System.out.println(result);
    }


    static void thenAcceptAsync() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("method").thenAcceptAsync(result::append);
        cf.join();
        System.out.println(result);
    }

    static void completeExceptionallyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync((s -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        }));
        CompletableFuture exceptionHandler = cf.handle((s, th) -> {
            return (th != null) ? "message upon cancel" : "";
        });
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
        try {
            cf.join();
        } catch (CompletionException ex) { // just for testing
            System.out.println("ex : {}" + ex);
        }

        String result = (String) exceptionHandler.join();
        System.out.println(result);
    }
}
