package com.goquality.devcontents.database.dto.links;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.goquality.devcontents.database.domain.Links;

import lombok.Getter;

@Getter
public class LinksMainResponseDTO {
    private Long id;
    private String link;
    private String title;
    private String modifiedDate;

    public LinksMainResponseDTO(Links entity) {
        id = entity.getId();
        link = entity.getLink();
        title = entity.getTitle();
        modifiedDate = toStringDateTime(entity.getModifiedDate());
    }

    /**
     * Java 8 버전
     */
    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }

}
