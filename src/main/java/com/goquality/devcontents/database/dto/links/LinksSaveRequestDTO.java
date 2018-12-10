package com.goquality.devcontents.database.dto.links;

import com.goquality.devcontents.database.domain.Links;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LinksSaveRequestDTO {

	private String link;
    private String title;

    public Links toEntity(){
        return Links.builder()
                .link(link)
                .title(title)
                .build();
    }
}
