/*
 * An easily extendable chat bot for any chat service.
 * Copyright (C) 2013 bogeymanEST
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package org.superfuntime.coreplugin;

import org.superfuntime.chatty.Plugin;
import org.superfuntime.chatty.arguments.Argument;
import org.superfuntime.chatty.chat.Chat;
import org.superfuntime.chatty.commands.CommandInfo;
import org.superfuntime.chatty.commands.CommandManager;
import org.superfuntime.chatty.events.CommandEvent;
import org.superfuntime.chatty.events.EventManager;
import org.superfuntime.chatty.events.Listener;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Bogeyman
 * Date: 11.10.13
 * Time: 20:31
 */
public class Main extends Plugin {
    private Map<CommandInfo, CommandHandler> commands = new HashMap<CommandInfo, CommandHandler>();

    @Override
    public void start() {
        EventManager.addListener(this);
        commands.put(CommandManager.addCommand(
                new CommandInfo("help", "Shows help for a command")
                        .arg("command", "The name of the command", Argument.Type.STRING, false)), new CmdHelp());
        commands.put(CommandManager.addCommand(new CommandInfo("login", "Log in to your acount")
                                                       .arg("username", "Your username", Argument.Type.STRING)
                                                       .arg("password", "Your password", Argument.Type.STRING)
                                                       .setChatType(Chat.Type.PM)),
                     new CmdLogin());
        commands.put(CommandManager.addCommand(
                new CommandInfo("register", "Registers a new account")
                        .arg("username", "Username", Argument.Type.STRING)
                        .arg("password", "Password", Argument.Type.STRING)
                        .setChatType(Chat.Type.PM)), new CmdRegister());
        commands.put(CommandManager.addCommand(new CommandInfo("info", "Displays general information about the bot.")),
                     new CmdInfo());
    }

    @Listener
    public void onChatmessageReceived(CommandEvent e) {
        CommandHandler handler = commands.get(e.getCommand());
        if (handler == null) return;
        handler.onCommand(e);
    }
}
