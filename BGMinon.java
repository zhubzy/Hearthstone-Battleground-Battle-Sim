public class BGMinon  {

    public static Minon acolyte_of_cthun(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "acolye of cthun", buffs);
        a.setOriginal(2, 2, new Buff[]{Buff.REBORN, Buff.TAUNT} );
        return a;
    }


    public static Minon acolyte_of_cthun_golden(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "golden acolye of cthun", buffs);
        a.setOriginal(4, 4, new Buff[]{Buff.REBORN, Buff.TAUNT} );
        return a;
    }

    public static Minon Icky_Imp(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "Icky Imp", buffs);
        a.setOriginal(1, 1, new Buff[]{});
        a.setDeathRattle(Deathrattles.SUMMON_TWO_IMPS);
        a.tribe = Tribe.Deamon;
        return a;
    }
    public static Minon Imprisoner(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "Imprisoner", buffs);
        a.setOriginal(1, 1, new Buff[]{Buff.TAUNT});
        a.setDeathRattle(Deathrattles.SUMMON_ONE_IMP);
        a.tribe = Tribe.Deamon;
        return a;
    }
    public static Minon Harvest_Golem(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "Harvest Golem", buffs);
        a.setOriginal(2, 1, new Buff[]{});
        a.setDeathRattle(Deathrattles.SUMMON_GOLEM);
        a.tribe = Tribe.Mech;
        return a;
    }
    public static Minon Baron_Rivenddare(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "Baron Rivenddare", buffs);
        a.setOriginal(1, 7, new Buff[]{});
        a.setAura(Aura.DEATHRATTLE_TWICE);        
        return a;
    }
    public static Minon Baron_Rivenddare_Golden(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "Baron Rivenddare", buffs);
        a.setOriginal(2, 14, new Buff[]{});
        a.setAura(Aura.DEATHRATTLE_THREE_TIMES);        
        return a;
    }
    public static Minon Micro_Mummy(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "Micro Mummy", buffs);
        a.setOriginal(1, 2, new Buff[]{Buff.REBORN});
        a.tribe = Tribe.Mech;
        return a;
    }

    public static Minon spawn_of_nzoth(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "Spawn of N'Zoth", buffs);
        a.setDeathRattle(Deathrattles.BUFF_1_1_EVERYTHING);
        a.setOriginal(2, 2, new Buff[]{});
        return a;
    }

    public static Minon glypth_guardian(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "Glypth Guardian", buffs);
        a.setOriginal(2, 4, new Buff[]{});
        a.setAura(Aura.DOUBLE_ATTACK);
        a.tribe = Tribe.Dragon;
        return a;
    }

    public static Minon egg(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "egg", buffs);
        a.setOriginal(0, 5, new Buff[]{});
        a.setDeathRattle(Deathrattles.SUMMON_EGG);
        a.tribe = Tribe.Mech;
        return a;
    }


    public static Minon ghoul(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "ghoul", buffs);
        a.setOriginal(1, 3, new Buff[]{Buff.TAUNT});
        a.setDeathRattle(Deathrattles.ONE_DAMAGE_ALL);
        return a;
    }
    public static Minon ghoul_golden(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "ghoul", buffs);
        a.setOriginal(2, 6, new Buff[]{Buff.TAUNT});
        a.setDeathRattle(Deathrattles.ONE_DAMAGE_ALL_TWICE);
        return a;
    }
    public static Minon hymane(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "hymane", buffs);
        a.setOriginal(6, 5, new Buff[]{});
        a.setDeathRattle(Deathrattles.SUMMON_TWO_HYENA);
        a.tribe = Tribe.Beast;
        return a;
    }

    public static Minon seweer_rat(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "sewer rat", buffs);
        a.setOriginal(3, 2, new Buff[]{});
        a.setDeathRattle(Deathrattles.SUMMON_RAT_TAUNT);
        a.tribe = Tribe.Beast;
        return a;
    }



    public static Minon Kaboom_Bot(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "Kaboom bot", buffs);
        a.setOriginal(2, 2, new Buff[]{});
        a.setDeathRattle(Deathrattles.BOMB);
        a.tribe = Tribe.Mech;
        return a;
    }

    public static Minon Kaboom_Bot_golden(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "Golen Kaboom bot", buffs);
        a.setOriginal(4, 4, new Buff[]{});
        a.setDeathRattle(Deathrattles.BOMB_TWICE);
        a.tribe = Tribe.Mech;
        return a;
    }

    public static Minon Kangroo_Apprentice(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "Kangroo Aprentice", buffs);
        a.setOriginal(3, 6, new Buff[]{});
        a.setDeathRattle(Deathrattles.SUMMON_TWO_MECH_DIED);
        return a;
    }


    public static Minon Omega_Busteer(int attack, int health, Buff[] buffs){
        Minon a = new Minon(attack, health, "Omega Buster", buffs);
        a.setOriginal(6, 6, new Buff[]{});
        a.setDeathRattle(Deathrattles.SUMMON_AND_BUFF_MECH_6_6);
        a.tribe = Tribe.Mech;
        return a;
    }
}
