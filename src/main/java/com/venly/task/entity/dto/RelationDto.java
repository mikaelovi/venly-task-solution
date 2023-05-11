package com.venly.task.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.venly.task.common.enumeration.RelationType;
import com.venly.task.entity.Relation;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Data
public class RelationDto {
    @NotBlank(message = "Word One may not be blank")
    String wordOne;

    @NotBlank(message = "Word Two may not be blank")
    String wordTwo;

    @NotBlank(message = "Relation Type may not be blank")
    String relationType;

    public Relation toRelation() {
        return new Relation(this.wordOne, this.wordTwo, RelationType.valueOf(relationType));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelationDto that)) return false;
        return getWordOne().equals(that.getWordOne()) && getWordTwo().equals(that.getWordTwo()) && getRelationType().equals(that.getRelationType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWordOne(), getWordTwo(), getRelationType());
    }
}
