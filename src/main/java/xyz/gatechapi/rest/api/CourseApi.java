package xyz.gatechapi.rest.api;

import com.google.api.server.spi.auth.EspAuthenticator;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiIssuer;
import com.google.api.server.spi.config.ApiIssuerAudience;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.UnauthorizedException;
import xyz.gatechapi.rest.dao.Email;


@Api(
        name = "courseapi",
        version = "v1",
        namespace = @ApiNamespace(ownerDomain = "gatech-api.xyz", ownerName = "gatech-api.xyz"),
        issuers = {
                @ApiIssuer(
                        name = "firebase",
                        issuer = "https://securetoken.google.com/course-gen",
                        jwksUri = "https://www.googleapis.com/service_accounts/v1/metadata/x509/securetoken@system.gserviceaccount.com"
                )
        }
)
public class CourseApi {

    @ApiMethod(
            name = "course_api_key",
            path = "course_api_key",
            apiKeyRequired = AnnotationBoolean.TRUE
    )
    public String courseApiKey(@Named("n") @Nullable Integer n) {
        return "auth successful";
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            authenticators = {EspAuthenticator.class},
            audiences = {"681084638848-b4nvv7pa3ujkftu97gjmevtn8danj2s3.apps.googleusercontent.com"},
            clientIds = {"681084638848-b4nvv7pa3ujkftu97gjmevtn8danj2s3.apps.googleusercontent.com"}
    )
    public Email getUserEmail(User user) throws UnauthorizedException {
        if (user == null) {
            throw new UnauthorizedException("Invalid credentials");
        }

        Email response = new Email();
        response.setEmail(user.getEmail());
        return response;
    }

    @ApiMethod(
            path = "firebase_user",
            httpMethod = ApiMethod.HttpMethod.GET,
            authenticators = {EspAuthenticator.class},
            issuerAudiences = {
                    @ApiIssuerAudience(
                            name = "firebase",
                            audiences = {"YOUR-PROJECT-ID"}
                    )
            }
    )
    public Email getUserEmailFirebase(User user) throws UnauthorizedException {
        if (user == null) {
            throw new UnauthorizedException("Invalid credentials");
        }

        Email response = new Email();
        response.setEmail(user.getEmail());
        return response;
    }
}
