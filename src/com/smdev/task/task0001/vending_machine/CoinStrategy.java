package com.smdev.task.task0001.vending_machine;

import java.math.BigDecimal;
import java.util.Map;

public interface CoinStrategy {

    Map<Coin, Integer> convertInCoins(Map<Coin, Integer> availableCoins, BigDecimal amount);
}
