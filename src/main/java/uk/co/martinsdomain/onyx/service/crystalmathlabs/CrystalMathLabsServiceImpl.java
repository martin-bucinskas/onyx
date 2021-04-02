package uk.co.martinsdomain.onyx.service.crystalmathlabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uk.co.martinsdomain.onyx.domain.Skill;
import uk.co.martinsdomain.onyx.domain.User;
import uk.co.martinsdomain.onyx.service.crystalmathlabs.api.CrystalMathLabsService;

import java.time.Instant;

@Service
public class CrystalMathLabsServiceImpl implements CrystalMathLabsService {

    private final WebClient webClient;

    @Autowired
    public CrystalMathLabsServiceImpl(
        @Qualifier("crystal-math-labs-client") WebClient webClient
    ) {
        this.webClient = webClient;
    }

    @Override
    @Nullable
    public User searchUser(String username) {
        // Validate username first, check if it conforms some rules
        if (username.isBlank()) {
            return null;
        }

        var bodySpec = webClient
            .get()
            .uri(uriBuilder ->
                uriBuilder
                    .queryParam("type", "search")
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
        }).block(); // TODO: Make this a non blocking method

        return null;
    }

    @Override
    public String previousUsername(String username) {
        return null;
    }

    @Override
    public void updateUser(String username) {

    }

    @Override
    public Instant userLastChecked(String username) {
        return null;
    }

    @Override
    public Instant userLastChanged(String username) {
        return null;
    }

    @Override
    public void getPlayerRecords(String username) {

    }

    @Override
    public void trackUser(String username, Instant time) {

    }

    @Override
    public void getCompetitionTotalBySkill(int compId, Skill skill) {

    }

    @Override
    public void getCompetitionRankingsBySkill(int compId, Skill skill) {

    }

    @Override
    public void getRecordsBySkill(Skill skill, Instant timePeriod) {

    }

    @Override
    public void getCurrentTopBySkill(Skill skill, Instant timePeriod) {

    }

    @Override
    public void getUserStats(String username) {

    }

    @Override
    public void getTimeToMaxForUser(String username) {

    }

    @Override
    public void getVirtualHiScoresForUser(String username) {

    }
}
