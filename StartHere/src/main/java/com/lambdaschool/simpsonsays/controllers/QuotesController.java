package com.lambdaschool.simpsonsays.controllers;

import com.lambdaschool.simpsonsays.models.Quotes;
import com.lambdaschool.simpsonsays.services.QuotesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuotesController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    QuotesService quotesService;

    @GetMapping(value = "/quotes",
            produces = {"application/json"})
    public ResponseEntity<?> listAllQuotes(HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Quotes> allQuotes = quotesService.findAll();
        return new ResponseEntity<>(allQuotes, HttpStatus.OK);
    }


    @GetMapping(value = "/quote/{quoteId}",
            produces = {"application/json"})
    public ResponseEntity<?> getQuote(HttpServletRequest request,
                                      @PathVariable
                                              Long quoteId)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        Quotes q = quotesService.findQuoteById(quoteId);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}",
            produces = {"application/json"})
    public ResponseEntity<?> findQuoteByUserName(HttpServletRequest request,
                                                 @PathVariable
                                                         String userName)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Quotes> theQuotes = quotesService.findByUserName(userName);
        return new ResponseEntity<>(theQuotes, HttpStatus.OK);
    }


    @PostMapping(value = "/quote")
    public ResponseEntity<?> addNewQuote(HttpServletRequest request, @Valid
    @RequestBody
            Quotes newQuote) throws URISyntaxException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        newQuote = quotesService.save(newQuote);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newQuoteURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{quoteid}").buildAndExpand(newQuote.getQuotesid()).toUri();
        responseHeaders.setLocation(newQuoteURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping("/quote/{id}")
    public ResponseEntity<?> deleteQuoteById(HttpServletRequest request,
                                             @PathVariable
                                                     long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        quotesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
