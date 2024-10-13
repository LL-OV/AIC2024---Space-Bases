package nullplayer;

import aic2024.user.Direction;
import aic2024.user.UnitController;

public class UnitPlayer {
    Direction[] directions = Direction.values();

    @SuppressWarnings("InfiniteLoopStatement")
    public void run(UnitController uc) {
        // Code to be executed only at the beginning of the unit's lifespan

        //noinspection InfiniteLoopStatement
        while (true) {
            // Code to be executed every round
        }
    }
}
