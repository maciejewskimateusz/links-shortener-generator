package com.example.linksshortenergenerator.service;

import com.example.linksshortenergenerator.dto.link.LinkCreateDto;
import com.example.linksshortenergenerator.dto.link.LinkResponseDto;
import com.example.linksshortenergenerator.exception.AliasAlreadyExistException;
import com.example.linksshortenergenerator.mapper.LinkResponseMapper;
import com.example.linksshortenergenerator.model.link.Link;
import com.example.linksshortenergenerator.repository.link.LinkRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LinkService {

    public static final int LINK_PERIOD_TIME = 10;
    private final LinkRepository linkRepository;
    private final LinkResponseMapper linkResponseMapper;


    public LinkResponseDto shortenLink(LinkCreateDto link) {

        if (link.getAlias() == null) {
            return shortenLinksWithRandomAlias(link);
        } else {
            return shortenLinksWithGivenAlias(link);
        }
    }

    public Optional<LinkResponseDto> findLink(String id) {
        return linkRepository.findById(id)
                .map(linkResponseMapper::map);
    }

    public void deleteLink(String id) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean admin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(System.out::println);
        linkRepository.findById(id).map(Link::getCreatedBy).ifPresent(link -> {
            if (name.equals(link) || admin) {
                linkRepository.deleteById(id);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Link can be deleted only by creator");
            }
        });
    }

    private LinkResponseDto shortenLinksWithGivenAlias(LinkCreateDto link) {
        if (linkRepository.existsById(link.getAlias())) {
            throw new AliasAlreadyExistException();
        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Link newLink = new Link(link.getAlias(), link.getUrl(), LocalDateTime.now(), name);
        return saveAndMap(newLink);
    }

    private LinkResponseDto shortenLinksWithRandomAlias(LinkCreateDto link) {
        String idGenerator;

        do {
            idGenerator = generateId();
        } while (linkRepository.existsById(idGenerator));

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Link newLink = new Link(idGenerator, link.getUrl(), LocalDateTime.now(), name);
        return saveAndMap(newLink);
    }

    private LinkResponseDto saveAndMap(Link newLink) {
        Link save = linkRepository.save(newLink);
        return linkResponseMapper.map(save);
    }


    private String generateId() {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();
        return randomStringGenerator.generate(12);
    }

    @Scheduled(fixedRate = 10000)
    @Transactional
    public void deleteOldLinks() {
        LocalDateTime time = LocalDateTime.now().minusMinutes(LINK_PERIOD_TIME);
        linkRepository.deleteOldLinks(time);
    }
}
