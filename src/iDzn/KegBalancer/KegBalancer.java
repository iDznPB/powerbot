package iDzn.KegBalancer;

import iDzn.KegBalancer.Tasks.*;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name= "iDzn/KegBalancer", description="Trains with Kegs in Warriors Guild", properties="client=4; author=iDzn; topic=999;")

public class KegBalancer extends PollingScript<ClientContext> {

    List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        //taskList.add(new Bank(ctx));
        //taskList.add(new WithdrawTuna(ctx));
        //taskList.add(new WithdrawCake(ctx));
        //taskList.add(new WithdrawLobster(ctx));
        //taskList.add(new WithdrawMonk(ctx));
        //taskList.add(new WithdrawShark(ctx));
        //taskList.add(new WithdrawSword(ctx));
        //taskList.add(new Walk(ctx));
        //taskList.add(new KegMe(ctx));
        //taskList.add(new Energy(ctx));
        taskList.add(new Eat(ctx));
            }

    @Override
    public void poll() {

        for(Task task : taskList){
            if (ctx.controller.isStopping()) {
                break;
            }
            if(task.activate());
            task.execute();
            break;

        }
    }
}

