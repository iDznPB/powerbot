package iDzn.KegBalancer.Tasks;

import iDzn.KegBalancer.Task;
import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Locatable;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;

import java.util.concurrent.Callable;


public class KegMe  extends Task {
    private final Area KegArea = new Area(new Tile(2866, 3538, 0), new Tile(2867,3540, 0));
    final GameObject Keg = ctx.objects.select().id(15668).nearest().poll();
    public KegMe(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {

        return KegArea.contains (ctx.players.local()) && !ctx.objects.select().id(Keg).isEmpty();
    }

    @Override
    public void execute() {
        if (ctx.inventory.select().count() < 27) ;

        if (Keg.valid())
            if (!Keg.inViewport()) {
                ctx.camera.turnTo(Keg);
                ctx.camera.pitch(0);
            }
        Keg.interact("Pick-up", "Keg");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return KegArea.contains(ctx.players.local());
            }
        }, 500, 30);
    }
}