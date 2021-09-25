
import java.util.ArrayList;
import java.util.Arrays;

public class Minon {
    

    int health;
    int attack;

    //For reborn and random spawn calculation
    int originalAttack;
    int originalHealth;
    Buff[] originalBuffs;

    String name;
    Tribe tribe;
    boolean divineShield;
    boolean taunt;
    boolean poison;
    boolean windfury;
    boolean reborn;
    boolean hasAttacked;
    Deathrattles deathrattle;
    Aura aura;
    ArrayList<Deathrattles> deathrattles;
    int deadPosition = 99;

    //Init minion with no effect
    public Minon(int attack, int health, String name){
        this.health = health;
        this.attack = attack;
        this.originalAttack = attack;
        this.originalHealth = health;
        this.name = name;
        hasAttacked = false;
        deathrattles = new ArrayList<Deathrattles>();
        deathrattle = Deathrattles.NONE;
        aura = Aura.NONE;
        tribe = Tribe.NONE;
    }


    public Minon(int attack, int health, String name, Tribe tribe){
        this.health = health;
        this.attack = attack;
        this.originalAttack = attack;
        this.name = name;
        hasAttacked = false;
        deathrattles = new ArrayList<Deathrattles>();
        deathrattle = Deathrattles.NONE;
        aura = Aura.NONE;
        this.tribe = tribe;
    }

    //Init minion with only keyword
    public Minon(int attack, int health, String name, Buff[] buffs){
        this(attack,health,name);
        if(Arrays.asList(buffs).contains(Buff.DIVINE_SHIELD)){
            divineShield = true;
        }
        if(Arrays.asList(buffs).contains(Buff.POISONIOUS)){
            poison = true;
        }
        if(Arrays.asList(buffs).contains(Buff.TAUNT)){
            taunt = true;
        }
        if(Arrays.asList(buffs).contains(Buff.WIND_FURY)){
            windfury = true;
        }

        if(Arrays.asList(buffs).contains(Buff.REBORN)){
            reborn = true;
        }
    }

    //Init minion with only death
    public Minon(int attack, int health, String name, Deathrattles death){
        this.health = health;
        this.attack = attack;
        this.name = name;
        hasAttacked = false;
        setDeathRattle(death);

    }



    //Complete init, use when cloning and creating minion that reborns with different keyword
    public Minon(int attack, int health, String name, boolean divineShield, boolean poison, boolean taunt, boolean windfury, 
                    Deathrattles death, Aura aura, boolean reborn, int originalAttack, int originalHealth, Buff[] originalBuffs, Tribe tribe)
    {
        this.health = health;
        this.attack = attack;
        this.name = name;
        hasAttacked = false;
        this.divineShield = divineShield;
        this.windfury = windfury;
        this.taunt = taunt;
        this.poison = poison;
        this.aura = aura;
        this.tribe = tribe;
        deathrattle = death;

        deathrattles = new ArrayList<Deathrattles>();
        if(deathrattle != Deathrattles.NONE)
            deathrattles.add(deathrattle);
        this.reborn = reborn;
        setOriginal(originalAttack, originalHealth, originalBuffs);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return attack + "/" + health +  " " + name ;
    }

    public void attack(Minon other){
        test.LOGGER.info(this.toString() + " is attacking " + other.toString());
        takeDamage(other);
        other.takeDamage(this);
        hasAttacked = true;
    }

    public void takeDamage(Minon damager){
        if(divineShield){
            divineShield = false;
        } else if (damager.poison){
            this.health = 0;
        }
        else {
            this.health -= damager.attack;
        }
    }

    public void takeDamage(Minon damager, int damage){
        if(divineShield){
            divineShield = false;
        } else if (damager.poison){
            this.health = 0;
        }
        else {
            this.health -= damage;
        }
    }

    public Minon reborn(){
        Minon rebirthed =  copy(this);
        rebirthed.attack = originalAttack;
        rebirthed.health = 1;
        rebirthed.reborn = false;
        return rebirthed;
    }


    public Minon original(){
        Minon original =  copy(this);
        original.attack = originalAttack;
        original.health = originalHealth;
        return original;
    }



    public void setOriginal(int originalAttack, int originalHealth, Buff[] originalBuffs){
        this.originalAttack = originalAttack;
        this.originalHealth = originalHealth;
        this.originalBuffs = originalBuffs;
    }

    public void setDeathRattle(Deathrattles death){
        
        deathrattle = death;
        deathrattles = new ArrayList<Deathrattles>();

        if(deathrattle != Deathrattles.NONE)
            deathrattles.add(deathrattle);

    }

    public void setAura(Aura aura){
        
        this.aura = aura;

    }


    public static Minon copy(Minon minon){

        return new Minon(minon.attack,minon.health,minon.name,minon.divineShield,minon.poison,minon.taunt,minon.windfury,minon.deathrattle, minon.aura, minon.reborn,minon
        .originalAttack,minon.originalHealth, minon.originalBuffs, minon.tribe);
    }

}