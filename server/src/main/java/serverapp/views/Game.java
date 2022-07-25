package serverapp.views;

import com.google.gson.Gson;
import org.json.JSONObject;
import serverapp.controllers.*;
import serverapp.enums.Actions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Game {
    public static String run(String command, JSONObject jsonObject) {
        if(command.equals(Actions.SENDINITATION.getCharacter())){
            return InvitationController.CreateInvitation(jsonObject.getString("UUID"),
                    jsonObject.getString("usernames"),jsonObject.getString("hexInHeight"),jsonObject.getString("hexInWidth"));
        }if(command.equals(Actions.ACCEPTINVITATION.getCharacter())){
            return InvitationController.acceptInvitation(jsonObject.getString("UUID"),jsonObject.getString("GameUUID"));
        }if(command.equals(Actions.REJECTINVITATION.getCharacter())){
            return InvitationController.rejectInvitation(jsonObject.getString("GameUUID"));
        }if(command.equals(Actions.GETHexInHeight.getCharacter())){
            return GameController.getHexInHeight();
        }if(command.equals(Actions.SELECTUNIT.getCharacter())){
            return UnitController.selectUnit(jsonObject.getInt("i"), jsonObject.getInt("j"),jsonObject.getString("type"));
        }if(command.equals(Actions.GETHexInWidth.getCharacter())){
            return GameController.getHexInWidth();
        }if(command.equals(Actions.GetTerrainNames.getCharacter())){
            return GameController.getTerrainNames();
        }if(command.equals(Actions.GetHexDetails.getCharacter())){
            return GameController.getHexDetails(jsonObject.getInt("i"), jsonObject.getInt("j"));
        }if(command.equals(Actions.GETUNITINFORMATION.getCharacter())){
            return GameController.getUnitInformation();
        }if(command.equals(Actions.SELECTILE.getCharacter())){
            return GameController.setSelectedHex(jsonObject.getInt("i"), jsonObject.getInt("j"));
        }if(command.equals(Actions.GETPANEDETAILS.getCharacter())){
            return GameController.GetPaneDetails(jsonObject);
        }if(command.equals(Actions.GETAVAILABLEWORKS.getCharacter())){
            return GameController.getAvailableWorks();
        }if(command.equals(Actions.ATTACKUNIT.getCharacter())){
            return CombatController.attackUnit(jsonObject.getInt("i"), jsonObject.getInt("j"));
        }if(command.equals(Actions.STARTMOVEMENT.getCharacter())){
            return UnitController.startMovement(jsonObject.getInt("i"), jsonObject.getInt("j"));
        }if(command.equals(Actions.SETUPFORRANGEATTACK.getCharacter())){
            return UnitController.setUpSiegeForRangeAttack();
        }if(command.equals(Actions.FORTIFY.getCharacter())){
            return UnitController.fortify();
        }if(command.equals(Actions.SLEEPUNIT.getCharacter())){
            return UnitController.sleepUnit();
        }if(command.equals(Actions.ALERT.getCharacter())){
            return UnitController.alert();
        }if(command.equals(Actions.PILLAGE.getCharacter())){
            return UnitController.pillage();
        }if(command.equals(Actions.DELETEUNIT.getCharacter())){
            return UnitController.deleteUnitAction();
        }if(command.equals(Actions.WAKEDUP.getCharacter())){
            return UnitController.wakeUpUnit();
        }if(command.equals(Actions.GARRISON.getCharacter())){
            return UnitController.garrison();
        }if(command.equals(Actions.FORTIFYUNTILLHEAL.getCharacter())){
            return UnitController.fortify();
        }if(command.equals(Actions.BUILDCITY.getCharacter())){
            return CityController.buildCity();
        }if(command.equals(Actions.UNITLISTPANEL.getCharacter())){
            return GameController.unitsPanel();
        }if(command.equals(Actions.changeResearch.getCharacter())){
            return GameController.changeResearch(jsonObject.getString("techName"));
        }if(command.equals(Actions.getAvailableTechsArray.getCharacter())){
            return GameController.getAvailableTechs();
        }if(command.equals(Actions.getLastTechnology.getCharacter())){
            return GameController.getLastTechnology();
        }if(command.equals(Actions.ISANACHIVEDTECK.getCharacter())){
            return GameController.isAchieved(jsonObject.getString("techName"));
        }if(command.equals(Actions.getNotifications.getCharacter())){
            return GameController.getNotifications();
        }if(command.equals(Actions.getNotificationsTurns.getCharacter())){
            return GameController.getNotificationsTurns();
        }if(command.equals(Actions.militaryPanel.getCharacter())){
            return GameController.militaryPanel();
        }if(command.equals(Actions.demographicScreen.getCharacter())){
            return GameController.demographicScreen();
        }if(command.equals(Actions.cityScreen.getCharacter())){
            return GameController.cityScreen(jsonObject.getString("cityName"));
        }if(command.equals(Actions.getPlayerCitiesNames.getCharacter())){
            return GameController.getPlayerCitiesNames();
        }if(command.equals(Actions.setSelectedCity.getCharacter())){
            return GameController.setSelectedCity(CityController.getCityWithName(jsonObject.getString("cityName")));
        }if(command.equals(Actions.presaleTiles.getCharacter())){
            return CityController.presaleTiles();
        }if(command.equals(Actions.buyHex.getCharacter())){
            return CityController.buyHex(jsonObject.getInt("count"));
        }if(command.equals(Actions.citiesPanel.getCharacter())){
            return GameController.citiesPanel();
        }if(command.equals(Actions.orderToWorker.getCharacter())){
            return GameController.orderToWorker(jsonObject.getString("order"));
        }if(command.equals(Actions.handelFogOfWarRemoverButton.getCharacter())){
            return GameController.handelFogOfWarRemoverButton();
        }if(command.equals(Actions.changeTurn.getCharacter())){
            return GameController.changeTurn();
        }
        return "bad request format";
    }
}