package org.example.backend.repository;

import org.example.backend.model.Message;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository  extends KeyValueRepository<Message, Long> {
}
