package com.venly.task.entity;

import com.venly.task.common.enumeration.RelationType;
import com.venly.task.entity.dto.RelationDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "relation")
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id_generator", sequenceName = "id_seq")
    private Long id;

    @NonNull
    @Column(name = "word_one")
    private String wordOne;

    @NonNull
    @Column(name = "word_two")
    private String wordTwo;

    @NonNull
    @Enumerated(EnumType.STRING)
    private RelationType relationType;


    public RelationDto toDto() {
        return new RelationDto(this.wordOne, this.wordTwo, this.relationType.name());
    }

}
