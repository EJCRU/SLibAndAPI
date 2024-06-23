package org.api.spoofer.slibandapi.inventory;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Setter
public class InventoryBuilder {

    private static final Map<Inventory , InventoryBuilder> builders = new HashMap<>();

    public static final InventoryBuilder defaults = new InventoryBuilder("" , 9);


    @Getter
    private final Inventory inventory;


    public InventoryBuilder(String name , int size) {
        builders.put((inventory = Bukkit.createInventory(null , size , Component.text(name))), this);
    }

    private Consumer<InventoryCloseEvent> eventClose = (e) -> {};

    private Consumer<InventoryOpenEvent> eventOpen = (e) -> {};

    private Consumer<InventoryClickEvent> eventClick = (e) -> {};


    public void onClose(InventoryCloseEvent event) {
        eventClose.accept(event);
    }

    public void onOpen(InventoryOpenEvent event) {
        eventOpen.accept(event);
    }

    public void onClick(InventoryClickEvent event) {
        eventClick.accept(event);
    }

    public ItemStack[] generator(String url , Map<String , ItemStack> itemStackMap) {
        final int size = inventory.getSize();
        String[] ch = url.split(" ");
        ItemStack[] items = new ItemStack[size];
        for (int i = 0; i < size; i++) {
            if(ch.length < i) {
                items[i] = null;
            }else {
                items[i] = itemStackMap.get(ch[i]);
            }
        }
        return items;
    }

    public static InventoryBuilder getInventoryBuilder(Inventory inventory) {
        return builders.getOrDefault(inventory , defaults);
    }
}
