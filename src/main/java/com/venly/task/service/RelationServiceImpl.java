package com.venly.task.service;

import com.venly.task.common.enumeration.RelationType;
import com.venly.task.entity.Relation;
import com.venly.task.entity.dto.RelationDto;
import com.venly.task.repository.RelationRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

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
    public List<RelationDto> findAllByOptionalFilters(String relation) {
        var allWordRelations = relationRepository.findAll();

        if (Objects.nonNull(relation) && StringUtils.hasLength(relation)) {
            allWordRelations = filterByRelation(relation);
        }

        return allWordRelations.stream().map(Relation::toDto).toList();
    }


    @Override
    public List<Relation> filterByRelation(String relation) {
        final var relationType = RelationType.valueOf(relation);

        return relationRepository.findAllByRelation(relationType);
    }
}
