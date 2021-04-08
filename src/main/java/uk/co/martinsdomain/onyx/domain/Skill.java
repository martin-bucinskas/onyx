package uk.co.martinsdomain.onyx.domain;

public class Skill {

    private final double rank;
    private final int level;
    private final double xp;
    private final SkillConst skill;

    public Skill(double rank, int level, double xp, SkillConst skill) {
        this.rank = rank;
        this.level = level;
        this.xp = xp;
        this.skill = skill;
    }

    public double getRank() {
        return rank;
    }

    public int getLevel() {
        return level;
    }

    public double getXp() {
        return xp;
    }

    public SkillConst getSkill() {
        return skill;
    }
}
