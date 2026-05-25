package com.utility.utility.dto.response;

import com.utility.utility.enums.UserRole;

public record CustomerResponseDTO(

        Long id,
        String name,
        String email,
        UserRole role

) {
}