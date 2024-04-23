package com.example.microone.kafka;

import com.example.microone.dto.MessageDTO;
import com.example.microone.dto.TransactionalDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
@Slf4j
public class KafkaConsumer {
    private final HashMap<UUID, HashMap<Integer, UUID>> userTransactions = new HashMap<>();
    private final HashMap<UUID, Integer> transactionCounts = new HashMap<>();
    private static final int TRANSACTION_THRESHOLD = 5;
    @KafkaListener(topics = "message") // Интересная ошибка, отправка/прослушивание
    // на кафку должна происходить таким же обьектом
    public void listen(MessageDTO requestMessage) {
        log.warn("Received Message in group 'consumer': " + requestMessage);
    }
    @KafkaListener(topics = "transaction")
    public void listenTransaction(TransactionalDTO transactionRequest) {
        log.warn("Received Message in group 'consumerTransaction': " + transactionRequest.toString());
        if (transactionRequest.getAmount() > 1000){
            log.warn("Amount is more than 100");
        }
        UUID userId = transactionRequest.getCurrentUserId();
        // Получаем карту транзакций пользователя
        HashMap<Integer, UUID> userTransactionMap = userTransactions.getOrDefault(userId, new HashMap<>());

        // Добавляем текущую транзакцию в карту
        userTransactionMap.put(transactionRequest.hashCode(), transactionRequest.getCurrentUserId());

        // Помещаем обновленную карту транзакций обратно в общую карту
        userTransactions.put(userId, userTransactionMap);

        // Обновляем количество транзакций для данного пользователя
        int transactionCount = transactionCounts.getOrDefault(userId, 0) + 1;
        transactionCounts.put(userId, transactionCount);

        // Проверяем количество транзакций у данного пользователя
        if (transactionCount > TRANSACTION_THRESHOLD) {
            System.out.println("User " + userId + " has more than " + TRANSACTION_THRESHOLD + " transactions.");
        }
        log.debug(userTransactions.toString());
        log.debug(transactionCounts.toString());
        log.debug(userTransactions.get(userId).toString());
    }
}
