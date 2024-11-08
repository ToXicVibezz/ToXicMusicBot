/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ToXicVibezz.ToxicMusic.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.ToXicVibezz.ToxicMusic.Bot;
import com.ToXicVibezz.ToxicMusic.commands.DJCommand;
import com.ToXicVibezz.ToxicMusic.settings.RepeatMode;
import com.ToXicVibezz.ToxicMusic.settings.Settings;

/**
 * Above inport dependencies
 * Below is the command to repeat current song, all songs, or none.
 */
public class RepeatCmd extends DJCommand
{
    public RepeatCmd(Bot bot)
    {
        super(bot);
        this.name = "repeat";
        this.help = "re-adds music to the queue when finished";
        this.arguments = "[off|all|single]";
        this.aliases = bot.getConfig().getAliases(this.name);
        this.guildOnly = true;
    }
    
    // override execute because we don't actually care where this is used
    @Override
    protected void execute(CommandEvent event) 
    {
        String args = event.getArgs();
        RepeatMode value;
        Settings settings = event.getClient().getSettingsFor(event.getGuild());
        if(args.isEmpty())
        {
            if(settings.getRepeatMode() == RepeatMode.OFF)
                value = RepeatMode.ALL;
            else
                value = RepeatMode.OFF;
        }
        else if(args.equalsIgnoreCase("false") || args.equalsIgnoreCase("off"))
        {
            value = RepeatMode.OFF;
        }
        else if(args.equalsIgnoreCase("true") || args.equalsIgnoreCase("on") || args.equalsIgnoreCase("all"))
        {
            value = RepeatMode.ALL;
        }
        else if(args.equalsIgnoreCase("one") || args.equalsIgnoreCase("single"))
        {
            value = RepeatMode.SINGLE;
        }
        else
        {
            event.replyError("Valid options are `off`, `all` or `single` (or leave empty to toggle between `off` and `all`)");
            return;
        }
        settings.setRepeatMode(value);
        event.replySuccess("Repeat mode is now `"+value.getUserFriendlyName()+"`");
    }

    @Override
    public void doCommand(CommandEvent event) { /* Intentionally Empty */ }
}
