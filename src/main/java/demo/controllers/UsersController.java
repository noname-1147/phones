package demo.controllers;

import demo.ports.FindUserPort;
import demo.responses.UserItem;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.reactivestreams.Publisher;

import java.util.Optional;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/users")
public class UsersController {
    private final FindUserPort findUserPort;

    public UsersController(FindUserPort findUserPort) {
        this.findUserPort = findUserPort;
    }

    @Operation(summary = "Return list of users with optional match by name")
    @Get(uri="/", produces="application/json")
    public Publisher<UserItem> findUsers(@QueryValue @Parameter(description = "Optional name part to search by") Optional<String> username) {
        return findUserPort.findUsers(username);
    }

}