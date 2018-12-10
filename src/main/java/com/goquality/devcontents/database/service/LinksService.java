package com.goquality.devcontents.database.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goquality.devcontents.database.domain.LinksRepository;
import com.goquality.devcontents.database.dto.links.LinksMainResponseDTO;
import com.goquality.devcontents.database.dto.links.LinksSaveRequestDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LinksService {

    private LinksRepository linksRepository;

    @Transactional
    public Long save(LinksSaveRequestDTO dto){
        return linksRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<LinksMainResponseDTO> findAllDesc() {
        return linksRepository.findAllDesc()
                .map(LinksMainResponseDTO::new)
                .collect(Collectors.toList());
    }

}
