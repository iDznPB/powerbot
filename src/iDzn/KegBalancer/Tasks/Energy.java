package iDzn.KegBalancer.Tasks;

import iDzn.KegBalancer.Task;
import org.powerbot.script.*;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class Energy extends Task {
    private final Area KegArea = new Area(new Tile(2866, 3538, 0), new Tile(2867, 3540, 0));
    final GameObject Keg = ctx.objects.select().id(15668).nearest().poll();
    private final int TotalHP = ctx.skills.realLevel(Constants.SKILLS_HITPOINTS);
    private final int HPpercent = (ctx.combat.health() / TotalHP)*100;

    public Energy(ClientContext ctx) {
        super(ctx);
    }


    @Override
    public boolean activate() {
        return !Equipment.Slot.HEAD.name().isEmpty();
    }

    @Override
    public void execute() {
        Item ENERGY4 = ctx.inventory.select().id(3008).poll();
        Item ENERGY3 = ctx.inventory.select().id(3010).poll();
        Item ENERGY2 = ctx.inventory.select().id(3012).poll();
        Item ENERGY1 = ctx.inventory.select().id(3014).poll();
        Item TUNA = ctx.inventory.select().id(361).poll();
        Item SHARK = ctx.inventory.select().id(385).poll();

        if (HPpercent <50){
            SHARK.interact("Eat");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return (HPpercent >50);
                }
            }, 200, 25);
        }

        if (ctx.inventory.select().id(3014).count() > 0 && ctx.movement.energyLevel() < 99) {
            ENERGY1.interact("Drink", "Energy potion(1)");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return (ctx.players.local().animation() == -1);
                }
            }, 600, 25);
        } else {

            if (ctx.inventory.select().id(3012).count() > 0 && ctx.movement.energyLevel() < 99) {
                ENERGY2.interact("Drink", "Energy potion(2)");
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return (ctx.players.local().animation() == -1);
                    }
                }, 600, 25);
            } else {

                if (ctx.inventory.select().id(3010).count() > 0 && ctx.movement.energyLevel() < 99) {
                    ENERGY3.interact("Drink", "Energy potion(3)");
                    Condition.wait(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            return (ctx.players.local().animation() == -1);
                        }
                    }, 600, 25);

                } else {
                    if (ctx.inventory.select().id(3008).count() > 0 && ctx.movement.energyLevel() < 99) {
                        ENERGY4.interact("Drink", "Energy potion(4)");
                        Condition.wait(new Callable<Boolean>() {
                            @Override
                            public Boolean call() throws Exception {
                                return (ctx.players.local().animation() == -1);
                            }
                        }, 600, 25);
                    }

                }
            }
        }
    }
}