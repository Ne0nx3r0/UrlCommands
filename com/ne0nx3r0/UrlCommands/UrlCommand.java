package com.ne0nx3r0.UrlCommands;

public class UrlCommand {
    private boolean showPlayerResult = false;
    private String[] params;
    private String resultPlayerCommand;
    private String resultConsoleCommand;
    
    public UrlCommand(boolean showPlayerResult, String[] params,String resultPlayerCommand, String resultConsoleCommand){
        this.showPlayerResult = showPlayerResult;
        this.params = params;
        this.resultPlayerCommand = resultPlayerCommand;
        this.resultConsoleCommand = resultConsoleCommand;
    }   
    
    public int getParamsLength(){
        if(this.params != null)
            return this.params.length;
        return 0;
    }

    boolean showPlayerResult(){
        return this.showPlayerResult;
    }

    boolean hasPlayerCommand() {
        return (this.resultPlayerCommand != null);
    }

    boolean hasConsoleCommand() {
        return (this.resultConsoleCommand != null);
    }

    String getConsoleCommand(){
        return this.resultConsoleCommand;
    }

    String getPlayerCommand() {
        return this.resultPlayerCommand;
    }
}
