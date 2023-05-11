package com.venly.task.repository;

import com.venly.task.common.enumeration.RelationType;
import com.venly.task.entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Long> {
    List<Relation> findAllByRelation(RelationType relationType);

}
