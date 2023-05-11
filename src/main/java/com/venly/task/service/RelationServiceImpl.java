package com.venly.task.service;

import com.venly.task.entity.dto.RelationDto;
import com.venly.task.repository.RelationRepository;
import org.springframework.stereotype.Service;

@Service
public class RelationServiceImpl implements RelationService {

    private final RelationRepository relationRepository;

    public RelationServiceImpl(RelationRepository relationRepository) {
        this.relationRepository = relationRepository;
    }

    @Override
    public RelationDto create(RelationDto wordRelation) {
        final var savedRelation = relationRepository.save(wordRelation.toRelation());

        return savedRelation.toDto();
    }
}
