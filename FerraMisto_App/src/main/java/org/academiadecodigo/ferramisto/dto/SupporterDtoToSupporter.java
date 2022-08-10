package org.academiadecodigo.ferramisto.dto;


import org.academiadecodigo.ferramisto.persistence.models.Supporters;
import org.academiadecodigo.ferramisto.services.SupporterService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SupporterDtoToSupporter implements Converter<SupporterDto, Supporters> {
    private SupporterService supporterService;
    @Override
    public Supporters convert(SupporterDto supporterDto) {
        Supporters supporter = (supporterDto.getId() != null ? supporterService.get(supporterDto.getId()) : new Supporters());
        supporter.setFirstName(supporterDto.getFirstName());
        supporter.setLastName(supporterDto.getLastName());
        supporter.setEmail(supporterDto.getEmail());
        supporter.setPhone(supporterDto.getPhone());
        supporter.setMessage(supporterDto.getMessage());

        return supporter;
    }
}
