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
package com.ToXicVibezz.ToxicMusic.commands.owner;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.ToXicVibezz.ToxicMusic.Bot;
import com.ToXicVibezz.ToxicMusic.commands.OwnerCommand;
import net.dv8tion.jda.api.exceptions.RateLimitedException;

/**
 * Above import dependencies
 * Below is the set name command
 */
public class SetnameCmd extends OwnerCommand
{
    public SetnameCmd(Bot bot)
    {
        this.name = "setname";
        this.help = "changes the name of Zero-Two";
        this.arguments = "<name>";
        this.aliases = bot.getConfig().getAliases(this.name);
        this.guildOnly = false;
    }
    
    @Override
    protected void execute(CommandEvent event) 
    {
        try 
        {
            String oldname = event.getSelfUser().getName();
            event.getSelfUser().getManager().setName(event.getArgs()).complete(false);
            event.reply(event.getClient().getSuccess()+" Name changed from `"+oldname+"` to `"+event.getArgs()+"`");
        } 
        catch(RateLimitedException e) 
        {
            event.reply(event.getClient().getError()+" Bot names can only be changed twice per hour!");
        }
        catch(Exception e) 
        {
            event.reply(event.getClient().getError()+" That name is not valid!");
        }
    }
}
