package org.springframework.data.rest.example;

import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path="addresses")
public interface AddressRepository extends BaseRepository<Address, Long> {
}
