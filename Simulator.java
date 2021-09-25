import java.lang.System.Logger;
import java.util.*;

public class Simulator {


    ArrayList<Minon> myOriginalBoard;
    ArrayList<Minon> enemyOriginalBoard;
    ArrayList<Minon> myboard;
    ArrayList<Minon> enemyboard;
    ArrayList<Minon> myGraveYard;
    ArrayList<Minon> enemyGraveYard;

    public static Random rand = new Random();


    public enum result {
        WIN,
        LOSE,
        TIE
    }

    //If myboard is currently about to attack
    boolean myturn;

    public Simulator(ArrayList<Minon> myboard, ArrayList<Minon> enemyboard){
        myOriginalBoard = myboard;
        enemyOriginalBoard = enemyboard;
        resetBoard();
    }

    public result run(){

        while(!myboard.isEmpty() && !enemyboard.isEmpty()){
            combat();
        }
        if(myboard.isEmpty() && enemyboard.isEmpty()){
           // System.out.println("It is a tie");
            return result.TIE;
        } else if (myboard.isEmpty()){
          //  System.out.println("Lost");
            return result.LOSE;
        } else {
          //  System.out.println("Won");
            return result.WIN;
        }

    }


    public void simulate(int times){

        int wins = 0;
        int loses = 0;
        int ties = 0;
        for(int i = 0; i < times; i ++){
            resetBoard();
            switch(run()){
                case WIN:
                    wins++;
                    test.LOGGER.info("win");
                    break;
                case LOSE:
                    loses++;
                    test.LOGGER.info("lost");
                    break;
                case TIE:
                    ties++;
                    test.LOGGER.info("tie");
                    break;                
            }

        }

        System.out.println("Win: " + wins/1.0/times * 100 + "%");
        System.out.println("Lose: " + loses/1.0/times * 100 + "%");
        System.out.println("Tie: " + ties/1.0/times * 100 + "%");

    }


    private void resetBoard(){

        this.enemyboard = new ArrayList<Minon>();
        this.myboard = new ArrayList<Minon>();


        for (Minon minon : enemyOriginalBoard) {
            enemyboard.add(Minon.copy(minon));

        }

        for (Minon minon : myOriginalBoard) {
            myboard.add(Minon.copy(minon));

        }
        myGraveYard = new ArrayList<Minon>();
        enemyGraveYard = new ArrayList<Minon>();
        if(myboard.size() > enemyboard.size()){
            myturn = true;
        } else if (myboard.size() < enemyboard.size()){
            myturn = false;
        } else {
            myturn = Math.random() < 0.5;
        }
    }

    public void combat(){



        if(myturn){
            //Choose a random minion 
            //Choose the first minion that has not attacked on our turn
            attack(myboard, enemyboard);
            

        } else {

            attack(enemyboard, myboard);

        }

        resolve();
        myturn = !myturn;


    }
    public void attack(ArrayList<Minon> boardAttacking,ArrayList<Minon> boardDefending ){
        Minon attacker = findAttcker(boardAttacking);
        switch(attacker.aura){
            case DOUBLE_ATTACK:
                attacker.attack *= 2;
                break;
            default:
                break;
            
        }
        
        attacker.attack(findAttackTarget(boardDefending));
        //Choose a random minion on enemy board to attack
        resolve();
        if(attacker.windfury && !boardDefending.isEmpty() && attacker.health > 0){
            attacker.attack(findAttackTarget(boardDefending));
        }

    }

    public void resolve(){

        checkDeath(myboard, enemyboard);
        checkDeath(enemyboard, myboard);


    }

    public Minon findAttackTarget(ArrayList<Minon> board){


        //First choose a ran
        ListIterator<Minon> myMinions = board.listIterator();
        ArrayList<Minon> tauntMinions = new ArrayList<Minon>();
        while (myMinions.hasNext()) {
            Minon current = myMinions.next(); // must be called before you can call i.remove()
            if(current.taunt)
                tauntMinions.add(current);
        }
        if(tauntMinions.isEmpty()){
            int attackPosition = rand.nextInt(board.size());

            return board.get(attackPosition);
        } else {
            int attackPosition = rand.nextInt(tauntMinions.size());
            return tauntMinions.get(attackPosition);

        }

    }

    public ArrayList<Minon> removeAllDied(ArrayList<Minon> board){
        ListIterator<Minon> myMinions = board.listIterator();
        ArrayList<Minon> currentGraveYard = new ArrayList<Minon>();
        while (myMinions.hasNext()) {
            int index = myMinions.nextIndex();
            Minon current = myMinions.next(); // must be called before you can call i.remove()
            if(current.health <= 0){
                current.deadPosition = index;
                currentGraveYard.add(current);
                if(board == myboard){
                    myGraveYard.add(current);
                } else {
                    enemyGraveYard.add(current);
                }
                myMinions.remove();
            }
        }
        return currentGraveYard;
    }
    

    public void checkDeath(ArrayList<Minon> board, ArrayList<Minon> opposingBoard){

        
        ListIterator<Minon> deadMinons = removeAllDied(board).listIterator();
        ArrayList<Minon> graveyardPool;
        if(board == myboard){
            graveyardPool  = myGraveYard;
        } else {
            graveyardPool = enemyGraveYard;
        }

        boolean deathDealsDamage = false;

        while (deadMinons.hasNext()) {
            Minon current = deadMinons.next(); // must be called before you can call i.remove()
                int deathMultiplier = 1;
                //Check any deathrattle aura
                for (Minon a : board) {
                    switch(a.aura){
                        case NONE:
                            break;
                        case DEATHRATTLE_TWICE:
                            deathMultiplier = 2;
                            break;
                        case DEATHRATTLE_THREE_TIMES:
                            deathMultiplier = 3;
                            break; 
                    }
                }
                

                //Trigger any deathrattle
                    for (Deathrattles death : current.deathrattles) {
                        for(int i = 0; i < deathMultiplier; i ++){
                        switch(death){
                            case NONE:
                                break;
                            case SUMMON_TWO_IMPS:
                                summon( board, current.deadPosition, new Minon(1, 1, "Imp", Tribe.Deamon));
                                summon(board, current.deadPosition, new Minon(1, 1, "Imp", Tribe.Deamon));
                                break;   
                            case SUMMON_ONE_IMP:
                                summon(board, current.deadPosition, new Minon(1, 1, "Imp", Tribe.Deamon));
                                break;   
                            case SUMMON_GOLEM:
                                summon( board, current.deadPosition, new Minon(2, 1, "Golem", Tribe.Mech));
                                break;
                            case BUFF_1_1_EVERYTHING:
                                for (int index = 0; index < board.size(); index++) {
                                    board.get(index).health++;
                                    board.get(index).attack++;
                                }
                                break;
                            case SUMMON_EGG:
                                summon( board, current.deadPosition, new Minon(8, 8, "Egg dinsour"));
                                break;
                            case SUMMON_TWO_HYENA:
                                summon( board, current.deadPosition, new Minon(2, 2, "Hyena"));
                                summon(board, current.deadPosition, new Minon(2, 2, "Hyena"));
                                break;
                            case SUMMON_RAT_TAUNT:
                                summon( board, current.deadPosition, new Minon(2, 3, "Taunted Rat", new Buff[]{Buff.TAUNT}));
                                break;
                            case SUMMON_TWO_MECH_DIED:
                                int summonedMech = 0;
                                for (Minon a : graveyardPool ) {
                                    if(a.tribe == Tribe.Mech && summonedMech < 2){
                                        summon(board, current.deadPosition, a.original());
                                    }
                                }
                                break;
                            case SUMMON_AND_BUFF_MECH_6_6:
                                for (int index = 0; index < 6; index++) {
                                    if(board.size() < 7){
                                        Minon microbots = new Minon(1,1,"Micro bots");
                                        microbots.tribe = Tribe.Mech;
                                        summon(board, current.deadPosition, microbots);
                                    } else {
                                        for (int n = 0; n < board.size(); n++) {
                                            if(board.get(n).tribe == Tribe.Mech){
                                                board.get(n).health++;
                                                board.get(n).attack++;
                                            }
                                        }

                                    }
                                }
                                break;
                            case ONE_DAMAGE_ALL:
                                for (int index = 0; index < myboard.size(); index++) {
                                    board.get(index).takeDamage(current,1);
                                }
                                for (int index = 0; index < enemyboard.size(); index++) {
                                    enemyboard.get(index).takeDamage(current,1);
                                }
                                deathDealsDamage = true;
                                break;
                            case ONE_DAMAGE_ALL_TWICE:
                                for (int index = 0; index < myboard.size(); index++) {
                                    board.get(index).takeDamage(current,1);
                                    board.get(index).takeDamage(current,1);
                                }
                                for (int index = 0; index < enemyboard.size(); index++) {
                                    enemyboard.get(index).takeDamage(current,1);
                                    enemyboard.get(index).takeDamage(current,1);
                                }
                                deathDealsDamage = true;
                                break;
                            case BOMB:
                                opposingBoard.get(rand.nextInt(opposingBoard.size())).takeDamage(current, 4);
                                deathDealsDamage = true;
                                break;
                            case BOMB_TWICE:
                                opposingBoard.get(rand.nextInt(opposingBoard.size())).takeDamage(current, 4);
                                opposingBoard.get(rand.nextInt(opposingBoard.size())).takeDamage(current, 4);
                                deathDealsDamage = true;
                                break;
                            default:
                                break;
               
                        }
                    }                    
                    }
                    if(current.reborn){
                        summon(board, current.deadPosition, current.original());
                    }


            }
                        if(deathDealsDamage){
                        resolve();
                    }
        }


    public void summon(ArrayList<Minon> myBoard, int index, Minon minon){
            // int num = 0;
            // for (Minon m : myBoard) {
            //     if(m.health > 0){
            //         num++;
            //     }
            // }
            if(myBoard.size() < 7){
                myBoard.add(index,minon);
            }
    }

    public Minon findAttcker(ArrayList<Minon> board){


        //Get the first minion that hasn't attack
        Iterator<Minon> myMinions = board.iterator();
        while (myMinions.hasNext()) {
            Minon current = myMinions.next(); // must be called before you can call i.remove()
            if(!current.hasAttacked && current.attack > 0){
                return current;
            }
        }



        //Since all minions has attacked, we want to get the first minion to attack again
        myMinions = board.iterator();
        while (myMinions.hasNext()) {
            Minon current = myMinions.next(); // must be called before you can call i.remove()
            current.hasAttacked = false;
        }

        return board.get(0);
    }


}
