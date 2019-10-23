package com.lambdaschool.simpsonsays.services;

import com.lambdaschool.simpsonsays.models.Quotes;

import java.util.List;

public interface QuotesService
{
    List<Quotes> findAll();

    Quotes findQuoteById(long id);

    List<Quotes> findByUserName(String username);

    void delete(long id);

    Quotes save(Quotes quote);
}