package com.venly.task.service;

import com.venly.task.entity.Relation;
import com.venly.task.entity.dto.RelationDto;

import java.util.List;

public interface RelationService {
    RelationDto create(RelationDto wordRelation);
    List<RelationDto> findAllByOptionalFilters(String relation, boolean showInverse);

    List<Relation> filterByRelation(String relation);

    String findPath(String source, String target);

}
