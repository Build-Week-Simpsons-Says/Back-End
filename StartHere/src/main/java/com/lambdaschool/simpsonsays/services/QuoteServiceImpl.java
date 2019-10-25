package com.lambdaschool.simpsonsays.services;

import com.lambdaschool.simpsonsays.exceptions.ResourceNotFoundException;
import com.lambdaschool.simpsonsays.models.Quotes;
import com.lambdaschool.simpsonsays.models.User;
import com.lambdaschool.simpsonsays.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "quoteService")
public class QuoteServiceImpl implements QuotesService
{
    @Autowired
    private QuoteRepository quoterepos;

    @Override
    public List<Quotes> findAll()
    {
        List<Quotes> list = new ArrayList<>();
        quoterepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Quotes findQuoteById(long id)
    {
        return quoterepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (quoterepos.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (quoterepos.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                quoterepos.deleteById(id);
            } else
            {
                throw new ResourceNotFoundException(id + " " + authentication.getName());
            }
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Quotes save(Quotes quote, User user)
    {
        Quotes newQuotes = new Quotes();
        newQuotes.setQuote(quote.getQuote());
        newQuotes.setUser(user);

        return quoterepos.save(newQuotes);
    }

    @Override
    public List<Quotes> findByUserName(String username)
    {
        List<Quotes> list = new ArrayList<>();
        quoterepos.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }

    @Override
    public Quotes update(long Quotesid,
                            String quotes,
                            boolean isAdmin)
    {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (quoterepos.findById(Quotesid)
                .isPresent())
        {
            if (quoterepos.findById(Quotesid)
                    .get()
                    .getUser()
                    .getUsername()
                    .equalsIgnoreCase(authentication.getName()) || isAdmin)
            {
                Quotes useremail = findQuoteById(Quotesid);
                useremail.setQuote(quotes);
                return quoterepos.save(useremail);
            } else
            {
                throw new ResourceNotFoundException(authentication.getName() + " not authorized to make change");
            }
        } else
        {
            throw new ResourceNotFoundException("Useremail with id " + Quotesid + " Not Found!");
        }
    }
}