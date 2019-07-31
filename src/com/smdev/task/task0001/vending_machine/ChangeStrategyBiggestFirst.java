package com.smdev.task.task0001.vending_machine;

import java.math.BigDecimal;
import java.util.*;

public class ChangeStrategyBiggestFirst implements ChangeStrategy {
    @Override
    public Map<CoinType, Integer> convertInCoins(Map<CoinType, Integer> availableCoins, BigDecimal amount) throws Exception {
        Map<CoinType, Integer> amountInCoins = new HashMap<>();

        List<CoinType> coinTypes = new ArrayList<>(availableCoins.keySet());
        coinTypes.sort(Comparator.comparing(CoinType::getNominal).reversed());

        for (CoinType c : coinTypes) {
            if (amount.doubleValue() == 0) {
                break;
            }
            while (amount.doubleValue() >= c.getNominal().doubleValue() && availableCoins.getOrDefault(c, 0) > 0) {
                amountInCoins.put(c, amountInCoins.getOrDefault(c, 0) + 1);
                availableCoins.put(c, availableCoins.get(c) - 1);
                amount = amount.subtract(c.getNominal());
            }
        }

        if (amount.doubleValue() > 0) {
            throw  new Exception("This should not be possible!");
        }
        return amountInCoins;
    }
}
