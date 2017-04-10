package org.turkisi.training.stream;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class AnonymousClass {

    public static void main(String[] args) {


        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 20; i++) {
                    System.out.println("Hello #" + i);
                }
            }
        }).start();
    }
}
