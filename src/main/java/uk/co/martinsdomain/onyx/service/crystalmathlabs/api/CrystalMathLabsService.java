package uk.co.martinsdomain.onyx.service.crystalmathlabs.api;

import org.springframework.lang.Nullable;
import uk.co.martinsdomain.onyx.domain.Skill;
import uk.co.martinsdomain.onyx.domain.User;

import java.time.Instant;

public interface CrystalMathLabsService {

    @Nullable
    User searchUser(String username);

    String previousUsername(String username);

    void updateUser(String username);

    Instant userLastChecked(String username);

    Instant userLastChanged(String username);

    void getPlayerRecords(String username);

    void trackUser(String username, Instant time);

    void getCompetitionTotalBySkill(int compId, Skill skill);

    void getCompetitionRankingsBySkill(int compId, Skill skill);

    void getRecordsBySkill(Skill skill, Instant timePeriod);

    void getCurrentTopBySkill(Skill skill, Instant timePeriod);

    void getUserStats(String username);

    void getTimeToMaxForUser(String username);

    void getVirtualHiScoresForUser(String username);
}
