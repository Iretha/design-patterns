package com.smdev.task.task0001.vending_machine;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class CoinStrategySimple implements CoinStrategy{
    @Override
    public Map<Coin, Integer> convertInCoins(Map<Coin, Integer> availableCoins, BigDecimal amount) {
        Set<Coin> coinTypes = availableCoins.keySet();
        
        return null;
    }
}
