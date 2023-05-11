package com.venly.task.service;

import com.venly.task.common.enumeration.RelationType;
import com.venly.task.common.exception.BadRequestException;
import com.venly.task.common.exception.PathNotFoundException;
import com.venly.task.entity.Relation;
import com.venly.task.entity.dto.RelationDto;
import com.venly.task.repository.RelationRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class RelationServiceImpl implements RelationService {

    private final RelationRepository relationRepository;

    public RelationServiceImpl(RelationRepository relationRepository) {
        this.relationRepository = relationRepository;
    }

    @Override
    public RelationDto create(RelationDto wordRelation) {

        if (relationRepository.existsWordRelationByWordAndAnotherWordAndRelation(wordRelation.getWordOne(),
                wordRelation.getWordTwo(), RelationType.valueOf(wordRelation.getRelationType()))
                || relationRepository.existsWordRelationByWordAndAnotherWordAndRelation(wordRelation.getWordTwo(),
                wordRelation.getWordOne(), RelationType.valueOf(wordRelation.getRelationType()))) {
            throw new BadRequestException(String.format("An existing relation exists between %s and %s",
                    wordRelation.getWordOne(), wordRelation.getWordTwo()));
        }

        final var savedRelation = relationRepository.save(wordRelation.toRelation());

        return savedRelation.toDto();
    }

    @Override
    public List<RelationDto> findAllByOptionalFilters(String relation, boolean showInverse) {
        var allWordRelations = relationRepository.findAll();

        if (Objects.nonNull(relation) && StringUtils.hasLength(relation)) {
            allWordRelations = filterByRelation(relation);
        }

        return showInverse
                ? showAllRelationsAndIncludeInverseInfo(allWordRelations)
                : allWordRelations.stream().map(Relation::toDto).toList();
    }

    private List<RelationDto> showAllRelationsAndIncludeInverseInfo(List<Relation> allWords) {
        return allWords.stream()
                .flatMap(wordRelation -> Stream.of(
                        wordRelation.toDto().invertRelation(false),
                        wordRelation.toDto().invertRelation(true)))
                .toList();
    }

    @Override
    public List<Relation> filterByRelation(String relation) {
        final var relationType = RelationType.valueOf(relation);

        return relationRepository.findAllByRelation(relationType);
    }

    @Override
    public String findPath(String source, String target) {
        // recursively find a path from source to target, otherwise throw exception
        var path = getPath(source, target, relationRepository.findAll());

        return path.orElseThrow(() -> new PathNotFoundException(String.format("No path found from %s to %s", source, target)));
    }

    Optional<String> getPath(String sourceWord, String targetWord, List<Relation> wordsList) {
        for (Iterator<Relation> iterator = wordsList.iterator(); iterator.hasNext(); ) {
            Relation wordRelation = iterator.next();

            if (wordRelation.getWordOne().equalsIgnoreCase(sourceWord) || wordRelation.getWordTwo().equalsIgnoreCase(sourceWord)) {
                iterator.remove();
                String secondWord = wordRelation.getWordOne().equalsIgnoreCase(sourceWord) ? wordRelation.getWordTwo() : wordRelation.getWordOne();

                return Optional.of(sourceWord
                        .concat("==")
                        .concat("(" + wordRelation.getRelationType().name().toLowerCase() + ")")
                        .concat("=>")
                        .concat(targetWord.equalsIgnoreCase(wordRelation.getWordOne()) || targetWord.equalsIgnoreCase(wordRelation.getWordTwo())
                                ? secondWord : String.valueOf(getPath(secondWord, targetWord, wordsList))));
            }
        }

        return Optional.empty();
    }
}
