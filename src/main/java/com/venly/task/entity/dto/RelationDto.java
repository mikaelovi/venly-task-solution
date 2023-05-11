package com.venly.task.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

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
}
