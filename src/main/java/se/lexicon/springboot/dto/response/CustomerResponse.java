package se.lexicon.springboot.dto.response;

import se.lexicon.springboot.entity.Address;

public record CustomerResponse(
    Long id,
    String fullName,
    String email,
    String addressResponse){

}
