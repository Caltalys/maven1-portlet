package org.springframework.data.rest.example;

import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path="profiles")
public interface ProfileRepository extends BaseRepository<Profile, Long> {
}
