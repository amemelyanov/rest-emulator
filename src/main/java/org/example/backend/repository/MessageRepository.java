package org.example.backend.repository;

import org.example.backend.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий по работе с сообщениями с использованием Redis
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}
