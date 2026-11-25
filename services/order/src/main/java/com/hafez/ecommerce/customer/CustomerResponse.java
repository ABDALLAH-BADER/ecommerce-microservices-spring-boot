package com.hafez.ecommerce.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerResponse(
        String id,

        @JsonProperty("firstname")
        String firstName,

        @JsonProperty("lastname")
        String lastName,

        String email
) {
}