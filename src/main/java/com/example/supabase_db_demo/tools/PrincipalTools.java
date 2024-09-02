package com.example.supabase_db_demo.tools;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.security.Principal;
import java.util.UUID;

public class PrincipalTools {

    public static UUID getPrincipalId(Principal principal){
        JwtAuthenticationToken jwt = (JwtAuthenticationToken) principal;
        String idString = (String) jwt.getTokenAttributes().get("sub");
        return UUID.fromString(idString);
    }
}
