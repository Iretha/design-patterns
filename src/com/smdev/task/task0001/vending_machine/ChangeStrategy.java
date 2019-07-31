package com.smdev.task.task0001.vending_machine;

import java.math.BigDecimal;
import java.util.Map;

public interface ChangeStrategy {

    Map<CoinType, Integer> convertInCoins(Map<CoinType, Integer> availableCoins, BigDecimal amount) throws Exception;
}
