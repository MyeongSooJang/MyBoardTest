package com.bs.boot.myboardrest.member.member.member.model.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest (
    @NotBlank String id,

    @NotBlank String pwd
    ){}
