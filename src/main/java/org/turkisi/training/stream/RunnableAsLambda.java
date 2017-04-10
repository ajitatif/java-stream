package org.turkisi.training.stream;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class RunnableAsLambda {

    public static void main(String[] args) {

        // this one
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 20; i++) {
                    System.out.println("Hello #" + i);
                }
            }
        }).start();

        // is literally same as this
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("Hello #" + i);
            }
        }).start();
    }
}
