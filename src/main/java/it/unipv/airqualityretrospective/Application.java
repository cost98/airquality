package it.unipv.airqualityretrospective;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
    info = @Info(
            title = "airquality",
            version = "0.0"
    )
)
@SecurityScheme(name = "openid",
        type = SecuritySchemeType.OAUTH2,
        scheme = "bearer",
        bearerFormat = "jwt",
        flows = @OAuthFlows(
                clientCredentials = @OAuthFlow(
                        tokenUrl = "http://localhost:8888/auth/realms/airqualityVueJs/protocol/openid-connect/token",
                        refreshUrl = "http://localhost:8888/auth/realms/airqualityVueJs/protocol/openid-connect/token",
                        scopes = @OAuthScope(name = "offline_access", description = "OpenID role")
                )
        )
)
public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
