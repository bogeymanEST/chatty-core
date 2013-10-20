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

import org.superfuntime.chatty.chat.Chat;
import org.superfuntime.chatty.chat.User;
import org.superfuntime.chatty.events.CommandEvent;
import org.superfuntime.chatty.permissions.PermissionManager;
import org.superfuntime.chatty.permissions.Profile;

/**
 * User: Bogeyman
 * Date: 17.10.13
 * Time: 17:38
 */
public class CmdLogin implements CommandHandler {
    @Override
    public void onCommand(CommandEvent e) {
        User user = e.getMessage().getSender();
        Chat chat = e.getMessage().getChat();
        if (user.hasProfile()) {
            chat.send("You are already logged in.");
            return;
        }
        String username = e.getArgument("username").toString();
        String password = e.getArgument("password").toString();
        Profile profile = PermissionManager.getUserProfile(username, password);
        if (profile == null) {
            chat.send("Invalid username or password!");
            return;
        }
        profile.ids.add(user.getUniqueIdentifier());
        user.setProfile(profile);
        chat.send("Successfully logged in!");
    }
}
