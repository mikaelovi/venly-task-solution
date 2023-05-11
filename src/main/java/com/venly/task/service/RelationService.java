package com.venly.task.service;

import com.venly.task.entity.dto.RelationDto;

import java.util.List;

public interface RelationService {
    RelationDto create(RelationDto wordRelation);
    List<RelationDto> findAll();
}
