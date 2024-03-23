package cloudwalk.quakeparser.domain.enumeration;

public enum DeathCauseType {
    MOD_UNKNOWN(0),
    MOD_SHOTGUN(1),
    MOD_GAUNTLET(2),
    MOD_MACHINEGUN(3),
    MOD_GRENADE(4),
    MOD_GRENADE_SPLASH(5),
    MOD_ROCKET(6),
    MOD_ROCKET_SPLASH(7),
    MOD_PLASMA(8),
    MOD_PLASMA_SPLASH(9),
    MOD_RAILGUN(12),
    MOD_LIGHTNING(13),
    MOD_BFG(14),
    MOD_BFG_SPLASH(14),
    MOD_WATER(15),
    MOD_SLIME(16),
    MOD_LAVA(17),
    MOD_CRUSH(18),
    MOD_TELEFRAG(19),
    MOD_FALLING(20),
    MOD_SUICIDE(21),
    MOD_TARGET_LASER(22),
    MOD_TRIGGER_HURT(23),
    MOD_NAIL(24),
    MOD_CHAINGUN(25),
    MOD_PROXIMITY_MINE(26),
    MOD_KAMIKAZE(27),
    MOD_JUICED(28),
    MOD_GRAPPLE(29);

    private final int id;

    DeathCauseType(final int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
    public static DeathCauseType getType(final int value) {
        for (final DeathCauseType deathType : values()) {
            if (Integer.compare(value, deathType.getId()) == 0) {
                return deathType;
            }
        }

        return null;
    }
}
