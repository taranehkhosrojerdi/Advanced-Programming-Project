package models.units;

import java.util.ArrayList;

import controllers.GameController;
import enums.UnitType;
import models.Player;
import models.maprelated.Hex;

public class Civilian extends Unit
{
    private static ArrayList<Civilian> civilians=new ArrayList<Civilian>();
    private boolean isWorking;

    @Override
    public void build()
    {
      GameController.getCurrentPlayer().decreaseGold(cost);
      currentHex.setCivilianUnit(this);
      GameController.getCurrentPlayer().addToCivilians(this);
      GameController.getCurrentPlayer().addUnit(this);
      GameController.addALlCivilians(this);
      
    }
    
    public Civilian(String name,Hex currentHex, Player owner)
    {
      super(name, currentHex, owner);
      this.isWorking=false;
      
      civilians.add(this);
    } 

    public static ArrayList<Civilian> geiCivilians()
    {
        return civilians;
    }
    public boolean getIsWorking()
    {
        return isWorking;
    }
    public Boolean isMovementPossible(Hex destination, Unit source)
    {
        return false;
    }
}
