package com.fawry.kafka.events;

import com.fawry.notificationapi.model.TransactionTyp;

public record BankEvent(
        long transactionId,
        long userId,
        double amount,
        TransactionTyp transactionTyp
) {
}
