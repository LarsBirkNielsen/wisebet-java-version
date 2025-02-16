package com.example.wisebet.domain.transformer;


import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BaseTransformer extends Transformer {
    public BaseTransformer() {
        super(MatchingStrategies.STRICT);

        // Add custom mapping for PredictionEntity to PredictionModel
//        modelMapper.addMappings(new PropertyMap<PredictionEntity, PredictionModel>() {
//            @Override
//            protected void configure() {
//                map().setHomeTeamName(source.getHomeTeam().getTeamName());
//                map().setAwayTeamName(source.getAwayTeam().getTeamName());
//                map().setLeagueId(source.getLeague().getId()); // Assuming you have leagueId in PredictionModel
//                map().setLeagueName(source.getLeague().getLeagueName());
//                // Add additional mappings here if needed
//            }
//        });

        modelMapper.addConverter(stringConverterToUUID);
    }

    @Override
    public <S, U> U transform(S sourceClass, Class<U> uClass) {
        return modelMapper.map(sourceClass, uClass);
    }


    public <S, U> List<U> transformList(List<S> sourceList, Class<U> destinationClass) {
        if (sourceList == null) {
            return Collections.emptyList();
        }
        return sourceList.stream()
                .map(source -> transform(source, destinationClass))
                .collect(Collectors.toList());
    }

    Converter<String, UUID> stringConverterToUUID = new AbstractConverter<>() {
        @Override
        protected UUID convert(String source) {
            if (source == null || source.isEmpty()) {
                return null;
            } else {
                return UUID.fromString(source);
            }
        }
    };
}
