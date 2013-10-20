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

import org.superfuntime.chatty.arguments.Argument;
import org.superfuntime.chatty.chat.Chat;
import org.superfuntime.chatty.commands.CommandInfo;
import org.superfuntime.chatty.commands.CommandManager;
import org.superfuntime.chatty.events.CommandEvent;
import org.superfuntime.chatty.events.Listener;

import java.util.List;

/**
 * User: Bogeyman
 * Date: 29.09.13
 * Time: 17:15
 */
public class CmdHelp implements CommandHandler {
    @Listener
    public void onCommand(CommandEvent e) {
        Chat chat = e.getMessage().getChat();
        Object value = e.getArgument("command").getValue();
        if (value == null) {
            StringBuilder sb = new StringBuilder();
            for (CommandInfo cmd : CommandManager.getCommands().values()) {
                if (cmd.isHidden()) continue;
                if (sb.length() != 0)
                    sb.append(", ");
                sb.append(CommandManager.COMMAND_PREFIX).append(cmd.getName());
            }
            chat.send("Available commands: " + sb.toString());
            return;
        }
        String cmdName = value.toString();
        if (cmdName.startsWith(CommandManager.COMMAND_PREFIX)) {
            cmdName = cmdName.substring(1);
        }
        CommandInfo cmd = CommandManager.getCommand(cmdName);
        if (cmd == null) {
            chat.send("Unable to find the command!");
            return;
        }
        chat.send(String.format("Showing help for: %s", cmd.getUsage()));
        chat.send("> " + cmd.getDescription());
        final List<Argument> arguments = cmd.getArguments();
        if (arguments.size() == 0) {
            chat.send("> There are no arguments");
        } else {
            chat.send("> Arguments:");
            for (Argument arg : arguments) {
                chat.send("> " + arg.getName() + " - " + arg.getDescription() + (arg.isRequired()
                        ? ""
                        : " (not required)"));
            }
        }
    }
}
