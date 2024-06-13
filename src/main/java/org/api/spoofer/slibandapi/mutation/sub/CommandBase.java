package org.api.spoofer.slibandapi.mutation.sub;

import lombok.Getter;
import lombok.Setter;
import org.api.spoofer.slibandapi.mutation.param.CommandParam;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class CommandBase extends Command {


    private TabExecutor executor;
    private CommandExecutor commandExecutor;


    public CommandBase(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        return commandExecutor.onCommand(commandSender , this , s , strings);
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return executor != null ? executor.onTabComplete(sender, this, alias, args) : Collections.emptyList();
    }

    public void register() {
        CommandParam.commandMap.register(getName() , this);
    }
}
