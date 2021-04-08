package uk.co.martinsdomain.onyx.service.rsapi.api;

import reactor.core.publisher.Mono;
import uk.co.martinsdomain.onyx.domain.User;

public interface RsApiService {

    Mono<User> getUser(String name);
}
