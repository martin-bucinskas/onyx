package uk.co.martinsdomain.onyx.domain;

import lombok.Data;

import java.util.Map;

@Data
public class User {
    private String username;
    private Map<SkillConst, Skill> skillMap;
}
