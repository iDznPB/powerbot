package iDzn.KegBalancer.Tasks;

import iDzn.KegBalancer.Task;
import org.powerbot.script.*;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Equipment.Slot;

import java.util.concurrent.Callable;

import static org.powerbot.script.rt4.Equipment.Slot.HEAD;

public class Eat extends Task {
    private final Area KegArea = new Area(new Tile(2866, 3538, 0), new Tile(2867, 3540, 0));

    private final int TotalHP = ctx.skills.realLevel(Constants.SKILLS_HITPOINTS);
    private final int HPpercent = (ctx.combat.health() / TotalHP) * 100;


    public Eat(ClientContext ctx) {
        super(ctx);
    }


    @Override
    public boolean activate() {

        return (!ctx.equipment.select().id(8860).isEmpty());
    }

    @Override
    public void execute() {
        Item TUNA = ctx.inventory.select().id(361).poll();
        Item SHARK = ctx.inventory.select().id(385).poll();
        if (HPpercent <= 50) {
            SHARK.interact("Eat");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return (HPpercent > 50);
                }
            }, 200, 25);
        }
    }
}