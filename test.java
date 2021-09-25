import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
public class test {
    public static final Logger LOGGER = Logger.getLogger(Minon.class.getName());

    
    public static void main(String[] args) {
        //Prints "Hello, World" to the terminal window.
        
        //LOGGER.setLevel(Level.OFF);

        // ArrayList<Minon> myboard = new ArrayList<Minon>(){{


        //     add(BGMinon.ghoul_golden(2, 6, new Buff[]{Buff.TAUNT}));
        //     add(BGMinon.Kaboom_Bot_golden(4, 4, new Buff[]{}));
        //     add(BGMinon.Kaboom_Bot_golden(4, 4, new Buff[]{}));
        //     add(BGMinon.Kangroo_Apprentice(3, 6, new Buff[]{}));
        //     add(BGMinon.ghoul_golden(2, 6, new Buff[]{Buff.TAUNT}));
        //     add(BGMinon.Kangroo_Apprentice(6, 12, new Buff[]{}));
        //     add(BGMinon.Baron_Rivenddare_Golden(2, 14, new Buff[]{}));




        // }};
        
        // ArrayList<Minon> enemyBoard = new ArrayList<Minon>(){{
        //     add(new Minon(39,275,"big"));



        // }};


        ArrayList<Minon> myboard = new ArrayList<Minon>(){{


            add(BGMinon.ghoul_golden(2, 6, new Buff[]{Buff.TAUNT}));
            add(BGMinon.Kangroo_Apprentice(3, 6, new Buff[]{}));
            add(BGMinon.Kangroo_Apprentice(3, 6, new Buff[]{}));
            add(BGMinon.Omega_Busteer(6, 6, new Buff[]{}));
            add(BGMinon.Omega_Busteer(6, 6, new Buff[]{}));
            add(BGMinon.Baron_Rivenddare_Golden(2, 14, new Buff[]{}));




        }};
        
        ArrayList<Minon> enemyBoard = new ArrayList<Minon>(){{
            add(new Minon(39,275,"big"));



        }};

        Simulator a = new Simulator(myboard, enemyBoard);
        a.simulate(10000);



    }

}



