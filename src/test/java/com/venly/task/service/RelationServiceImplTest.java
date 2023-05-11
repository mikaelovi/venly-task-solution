package com.venly.task.service;

import com.venly.task.common.enumeration.RelationType;
import com.venly.task.entity.Relation;
import com.venly.task.entity.dto.RelationDto;
import com.venly.task.repository.RelationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class RelationServiceImplTest {

    @MockBean
    private RelationRepository relationRepository;

    private RelationService relationService;

    @BeforeEach
    void setup() {
        relationService = new RelationServiceImpl(relationRepository);
    }

    @Test
    void testCreateWordRelation() {
        final var newWordRel = new Relation("son", "daughter", RelationType.RELATED);
        final var request = new RelationDto("son", "daughter", "RELATED", "");

        when(relationRepository.save(any(Relation.class))).thenReturn(newWordRel);

        final var actual = relationService.create(request);

        assertNotNull(actual);
        assertEquals(newWordRel.toDto(), actual);
    }
}