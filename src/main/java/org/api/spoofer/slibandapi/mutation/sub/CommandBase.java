package org.api.spoofer.slibandapi.mutation.sub;

import lombok.Getter;
import lombok.Setter;
import org.api.spoofer.slibandapi.mutation.param.CommandParam;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@Setter
@Getter
public class CommandBase extends Command {


    private TabCompleter tabCompleter = new TabCompleter() {
        @Override
        public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
            return Collections.emptyList();
        }
    };
    private CommandExecutor commandExecutor = (commandSender, command, s, strings) -> false;


    public CommandBase(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        return commandExecutor.onCommand(commandSender , this , s , strings);
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return tabCompleter.onTabComplete(sender, this, alias, args);
    }

    public void register() {
        CommandParam.commandMap.register(getName() , this);
    }
}
