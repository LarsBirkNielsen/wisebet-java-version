package com.example.wisebet.domain.transformer;

import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MatchingStrategy;

public abstract class Transformer {

    protected ModelMapper modelMapper = new ModelMapper();

    protected Transformer(MatchingStrategy matchingStrategy){
        modelMapper.getConfiguration().setMatchingStrategy(matchingStrategy);
    }

    public <S,U> U transform(S sourceClass, Class<U> uClass){
        return modelMapper.map(sourceClass, uClass);
    }
}