package com.venly.task.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.venly.task.common.enumeration.RelationType;
import com.venly.task.entity.Relation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Data
@Validated
public class RelationDto {
    @NotBlank(message = "Word One may not be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]", message = "Only upper and lower cased letters and spaces are allowed")
    String wordOne;

    @NotBlank(message = "Word Two may not be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]", message = "Only upper and lower cased letters and spaces are allowed")
    String wordTwo;

    @NotBlank(message = "Relation Type may not be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]", message = "Only upper and lower cased letters and spaces are allowed")
    String relationType;

    String inverse;

    public Relation toRelation() {
        return new Relation(this.wordOne, this.wordTwo, RelationType.valueOf(relationType));
    }

    public RelationDto invertRelation(boolean invert) {
        if (invert) return new RelationDto(this.wordTwo, this.wordOne, this.relationType, "yes");

        return new RelationDto(this.wordTwo, this.wordOne, this.relationType, "no");
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
