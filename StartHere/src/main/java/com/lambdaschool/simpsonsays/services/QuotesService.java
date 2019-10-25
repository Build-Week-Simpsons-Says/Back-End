package com.lambdaschool.simpsonsays.services;

import com.lambdaschool.simpsonsays.models.Quotes;
import com.lambdaschool.simpsonsays.models.User;

import java.util.List;

public interface QuotesService
{
    List<Quotes> findAll();

    Quotes findQuoteById(long id);

    List<Quotes> findByUserName(String username);

    void delete(long id);

    Quotes update(long Quotesid,
                     String quotes,
                     boolean isAdmin);

    Quotes save(Quotes quotes, User user);
}