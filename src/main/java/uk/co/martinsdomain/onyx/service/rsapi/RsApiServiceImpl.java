package uk.co.martinsdomain.onyx.service.rsapi;

import com.codepoetics.protonpack.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uk.co.martinsdomain.onyx.domain.Skill;
import uk.co.martinsdomain.onyx.domain.SkillConst;
import uk.co.martinsdomain.onyx.domain.User;
import uk.co.martinsdomain.onyx.service.rsapi.api.RsApiService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RsApiServiceImpl implements RsApiService {

    private final WebClient webClient;

    @Autowired
    public RsApiServiceImpl(@Qualifier("rs-api-client") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @Nullable
    public Mono<User> getUser(String username) {
        // Validate username first, check if it conforms some rules
        if (username.isBlank()) {
            return null;
        }

        var bodySpec = webClient
            .get()
            .uri(uriBuilder ->
                uriBuilder
                    .queryParam("player", username)
                    .build());

        var response = bodySpec.exchangeToMono(clientResponse -> {
            if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                return clientResponse.bodyToMono(String.class);
            } else if (clientResponse.statusCode().is4xxClientError()) {
                return Mono.just("Some error response"); // TODO: Custom exception?
            } else {
                return clientResponse
                    .createException()
                    .flatMap(Mono::error); // TODO: Custom exception?
            }
        });

        // Convert string to object

        response.flatMap(entry -> {


        });

        return null;
    }

    private Map<SkillConst, Skill> parseSkills(List<Integer[]> values) {
        var skills = values.subList(0, 24);
        // This seems a bit shit, but easy way to parse.
        return StreamUtils.zipWithIndex(Arrays.stream(SkillConst.values()))
            .map(i -> {
                var rank = skills.get((int) i.getIndex())[0];
                var level = skills.get((int) i.getIndex())[1];
                var xp = skills.get((int) i.getIndex())[2];

                return new Skill(rank, level, xp, i.getValue());
            }).collect(Collectors.toMap(Skill::getSkill, (skill) -> skill));
    }

    private List<Integer[]> csvToList(String csv) {
        return Arrays
            .stream(csv.split("\n"))
            .map(i -> Arrays
                .stream(i.split(","))
                .map(Integer::parseInt)
                .toArray(Integer[]::new))
            .collect(Collectors.toList());
    }
}
