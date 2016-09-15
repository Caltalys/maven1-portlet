package org.springframework.data.rest.example;

import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path="persons")
public interface PersonRepository extends BaseRepository<Person, Long> {
}
