package project.civilization.enums;
public enum Actions {
    LOGIN("login"),
    SAVEUSERS("saveUsers"),
    REGISTER("register"),
    LOGOUT("logout"),
    SCOREBOARD("scoreBoard"),
    DELETEACOUNT("deleteAcount"),
    CHAGENICKNAME("changeNickName"),
    CHAGEPASSWORD("changePassWord"),
    BROADCASTINVITATION("broadCastInvitation"),
    STARTGAME("startGame"),
    SENDINITATION("sendInvitation"),
    INITIALIZEHEARINGSERVERSOCKET("initializeHearingServerSocket"),
    INVITETOGAME("inviteToGame"),
    UNIQUEUSERNAME("uniqueUsername"),
    ACCEPTINVITATION("acceptInvitation"),
    REJECTINVITATION("REJECTINVITAION"),
    SENDREJECTION("sendRejection");
    private final String character;

    Actions(String color) {
        this.character = color;
    }

    public String getCharacter() {
        return character;
    }
}

