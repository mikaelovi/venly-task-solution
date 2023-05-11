package com.venly.task.service;

import com.venly.task.entity.Relation;
import com.venly.task.entity.dto.RelationDto;
import com.venly.task.repository.RelationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<RelationDto> findAll() {
        final var allWordRelations = relationRepository.findAll();

        return allWordRelations.stream().map(Relation::toDto).toList();
    }
}
