package com.lambdaschool.simpsonsays.repository;

import com.lambdaschool.simpsonsays.models.Quotes;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quotes, Long>
{

}