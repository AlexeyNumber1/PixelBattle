package pixelbattle.heroes;

import pixelbattle.core.Hero;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.core.Ultimate;
import pixelbattle.core.Fighter;
import pixelbattle.engine.Sprite;

/** Лучниця — швидка й спритна. */
public class Archer extends Hero {

    public Archer() {
        super("Лучниця", 80, new Sprite("elf_f_idle_anim", 4, 4));

        addSkill(new Skill("Постріл", Target.ENEMY, 0, (user, target) ->
                user.getName() + " стріляє в " + target.getName() + ": "
                        + target.takeDamage(random(10, 16)) + " шкоди"));

        addSkill(new Skill("Подвійний постріл", Target.ENEMY, 1, (user, target) -> {
            int dmg1 = target.takeDamage(random(7, 11));
            int dmg2 = target.takeDamage(random(7, 11));
            int totalDmg = dmg1 + dmg2;
            return user.getName() + " робить подвійний постріл по " + target.getName() + ": " + totalDmg + " шкоди";
        }));

        setUltimate(new Ultimate() {
            private int arrowCount = 3;

            @Override
            public String getName() {
                return "Злива стріл";
            }

            @Override
            public String getDescription() {
                return "Випускає зливу стріл у ворогів";
            }

            @Override
            public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                for (Fighter enemy : enemies) {
                    if (enemy.isAlive()) {
                        for (int i = 0; i < arrowCount; i++) {
                            enemy.takeDamage(Fighter.random(4, 8));
                        }
                    }
                }
                int currentCount = arrowCount;
                arrowCount++;
                return user.getName() + " випускає зливу з " + currentCount + " стріл у кожного ворога!";
            }
        });
    }

    protected int modifyIncomingDamage(int damage) {
        if (chance(25)) {
            return 0;
        }
        return damage;
    }
}
