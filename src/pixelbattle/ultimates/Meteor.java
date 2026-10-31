package pixelbattle.ultimates;

import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

public class Meteor implements Ultimate {
    @Override
    public String getName() {
        return "Метеорит";
    }

    @Override
    public String getDescription() {
        return "Падіння метеорита на всіх ворогів";
    }

    @Override
    public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
        for (Fighter enemy : enemies) {
            if (enemy.isAlive()) {
                enemy.takeDamage(Fighter.random(20, 30));
            }
        }
        return user.getName() + " призиває Метеорит на голови ворогів!";
    }
}