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

import org.superfuntime.chatty.Core;
import org.superfuntime.chatty.chat.Chat;
import org.superfuntime.chatty.events.CommandEvent;

/**
 * User: Bogeyman
 * Date: 17.10.13
 * Time: 18:01
 */
public class CmdInfo implements CommandHandler {
    @Override
    public void onCommand(CommandEvent e) {
        Chat chat = e.getMessage().getChat();
        chat.send("Chatty by bogeymanEST:",
                  "Running bots: " + Core.getBotCount(),
                  "Running plugins: " + Core.getPluginCount());
    }
}
