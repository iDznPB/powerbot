package iDzn.KegBalancer.Tasks;

import iDzn.KegBalancer.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import java.util.concurrent.Callable;

public class WithdrawMonk extends Task {

    public WithdrawMonk(ClientContext ctx) {
        super(ctx);
    }
    private static int MONK = 361;
    private static int ENERGY = 3008;

    @Override
    public boolean activate() {
        return ctx.inventory.select().count()==0 && ctx.bank.opened();

        }

        @Override
    public void execute() {
                 if (ctx.bank.select().id(MONK).isEmpty()) {
                        ctx.controller.stop();
                    } else if (ctx.inventory.select().count()==0 && !ctx.bank.select().id(MONK).isEmpty()) {
                        ctx.bank.withdraw(MONK,2);
                        ctx.bank.withdraw(ENERGY, 26);
                        ctx.bank.close();
                        Condition.wait(new Callable<Boolean>() {

                            @Override
                            public Boolean call() throws Exception {
                                return ctx.bank.opened();
                            }
                        }, 80, 20);
            }    }

    }
