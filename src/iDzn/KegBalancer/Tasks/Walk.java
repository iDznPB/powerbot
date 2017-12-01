package iDzn.KegBalancer.Tasks;

import iDzn.KegBalancer.Task;
import iDzn.KegBalancer.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class Walk extends Task {
    public static final Tile pathToBank[] = {new Tile(2867, 3539, 1), new Tile(2863, 3541, 1), new Tile(2859, 3542, 1), new Tile(2857, 3546, 1), new Tile(2856, 3550, 1), new Tile(2852, 3551, 1), new Tile(2851, 3547, 1), new Tile(2850, 3543, 1), new Tile(2846, 3540, 1), new Tile(2842, 3538, 1), new Tile(2841, 3538, 0), new Tile(2843, 3543, 0)};
    private static int FOOD []= {361, 1891, 1893, 1895, 379, 373, 7946, 385};
    private static int ENERGY []= { 3008, 3010, 3012, 3014};
    private final Walker walker = new Walker(ctx);


    public Walk(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return (ctx.inventory.select().id(FOOD).count()==0) || (ctx.inventory.select().id(ENERGY).count()==0) || (ctx.inventory.select().count()==28 && pathToBank[0].distanceTo(ctx.players.local())>8);
    }

    @Override
    public void execute() {
        if(!ctx.movement.running() && ctx.movement.energyLevel()> Random.nextInt(35,55)){
            ctx.movement.running(true);
        }
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local())<6) {
            if (ctx.inventory.select().id(ENERGY).count() == 0) {
                walker.walkPath(pathToBank);
            } else {
                walker.walkPathReverse(pathToBank);
                Condition.wait(new Callable<Boolean>() {

                    @Override
                    public Boolean call() throws Exception {
                        return pathToBank[0].distanceTo(ctx.players.local()) >6;

                    }
                }, 250, 20);
            }
        }
    }
}

