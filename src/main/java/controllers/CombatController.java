package controllers;

import models.Player;
import models.maprelated.City;
import models.maprelated.Hex;
import models.units.Combatable;
import models.units.Ranged;
import models.units.Unit;
import views.GameMenu;

public class CombatController {
    private static Unit selectedUnit = UnitController.getSelectedUnit();
    private static Hex[][] hex = GameController.getWorld().getHex();

    public static Hex[][] getHex() {
        return hex;
    }

    public static String attackUnit(int x, int y) {
        City defenderCity = hex[x][y].getCapital();
        Combatable attacker = selectedUnit;
        Combatable defender;
        if (defenderCity != null) {
            defender = defenderCity;
        } else if (hex[x][y].getMilitaryUnit() != null) {
            defender = hex[x][y].getMilitaryUnit();
        } else if (hex[x][y].getCivilianUnit() != null) {
            defender = hex[x][y].getCivilianUnit();
        } else
            return "there is no city or unit to attack";
        if (defender.getOwner() == attacker.getOwner()) return "you can not attack to your self";

        if(attacker.isInPossibleCombatRange(x, y,0, attacker.getX(), attacker.getY())){
            return "out of sight range";
        }
        attacker.attack(defender);
        defender.defend(attacker);
        return  handelCombatType(defenderCity, x, y);
    }

    private static String handelCombatType(City defenderCity, int x, int y){
        if (selectedUnit instanceof Ranged) {
            if (defenderCity != null) {
                return rangedCityCombat(defenderCity);
            } else if (hex[x][y].getMilitaryUnit() != null) {
                return rangedUnitCombat(x, y);
            } else if (hex[x][y].getCivilianUnit() != null) {
                return RangedToCivilianCombat(x, y);
            }
        } else {
            if (defenderCity!= null) return meleeCityCombat(defenderCity);
            else if(hex[x][y].getMilitaryUnit() != null) {
                return meleeUnitCombat(x, y);
            }else if(hex[x][y].getCivilianUnit() != null) {
                return meleeCivilianCombat(x, y);
            }
        }
        return null;
    }
    private static String meleeCivilianCombat(int x, int y) {
        return null;
    }

    private static String RangedToCivilianCombat(int x, int y) {
        return null;
    }

    private static String meleeCityCombat(City city) {
        //handel tile train and feature
        int unitStrength = selectedUnit.getCombatStrength() * selectedUnit.getCurrentHex().getTerrain().getCombatModifiersPercentage();
        //todo: assarat asib
        //todo : garisson saze defaie tapedivar & tape
        city.decreaseHitPoint(unitStrength);
        selectedUnit.decreaseHealth(city.getMeleeDefensivePower());
        if (selectedUnit.getHealth() <= 0) {
            killUnit();
            return "you lose the battle unit is death";
        }
        if (city.getHitPoint() <= 0) {
            GameMenu.cityCombatMenu(city, selectedUnit.getOwner());
            return "done";
        }
        return "attack is done";
    }

    private static void killUnit() {

    }

    private static String rangedCityCombat(City city) {
        if (city.getHitPoint() == 1) return "you can not attack to this city";
        // handel tile train and feature
        int unitStrength = selectedUnit.getRangedStrength() * selectedUnit.getCurrentHex().getTerrain().getCombatModifiersPercentage();
        //todo: assarat asib
        //todo : garisson saze defaie tape divar padeganNezami and tape??
        city.decreaseHitPoint(unitStrength);
        if (city.getHitPoint() < 1) {
            city.setHitPoint(1);
        }
        return "attack is done";
    }

    private static String meleeUnitCombat(int x, int y) {
        return "";
    }

    private static String rangedUnitCombat(int x, int y) {
        return "";
    }

    public static void addCityToTerritory(City city, Player player) {
        city.setOwner(player);
        //todo: check correction
        city.setHitPoint(0);
    }
}