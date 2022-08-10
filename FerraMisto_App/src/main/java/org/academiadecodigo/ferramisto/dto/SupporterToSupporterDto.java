package org.academiadecodigo.ferramisto.dto;

import org.academiadecodigo.ferramisto.persistence.models.Supporters;
import org.springframework.stereotype.Component;

@Component
public class SupporterToSupporterDto extends AbstractConverter<Supporters, SupporterDto>{

    @Override
    public SupporterDto convert(Supporters supporter) {
        SupporterDto supporterDto = new SupporterDto();
        supporterDto.setId(supporter.getId());
        supporterDto.setFirstName(supporter.getFirstName());
        supporterDto.setLastName(supporter.getLastName());
        supporterDto.setEmail(supporter.getEmail());
        supporterDto.setPhone(supporter.getEmail());
        supporterDto.setMessage(supporter.getMessage());
        return supporterDto;
    }
}
