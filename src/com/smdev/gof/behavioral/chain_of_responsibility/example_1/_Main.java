package com.smdev.gof.behavioral.chain_of_responsibility.example_1;

public class _Main {

    public static void main(String[] args) {
        CoinHandler handler = new CoinHandler1d();
        handler.setNextHandler(new CoinHandler50c()).setNextHandler(new CoinHandler25c());

        Coin coin25c = new Coin(1.75, 5.67); // 25 cents
        put(coin25c, handler);

        Coin coin1d = new Coin(2.58, 22.68); // 1 dollar
        put(coin1d, handler);

        Coin coin10c = new Coin(1.35, 2.268); // 10 cents
        put(coin10c, handler);

        Coin coin50c = new Coin(2.15, 11.34); // 50 cents
        put(coin50c, handler);
    }

    private static void put(Coin coin, CoinHandler handler){
        boolean accepted = handler.put(coin);

        if(!accepted){
            System.out.println(coin.toString() + " not accepted! Please, insert another coin!");
        }
    }
}
