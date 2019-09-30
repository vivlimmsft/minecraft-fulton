package space.vvn.entitySummoning;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import space.vvn.FultonController;
import space.vvn.entityStorage.EntityStorageView;
import space.vvn.entityStorage.StoredEntity;

@AllArgsConstructor
public class SummonableStoredEntity implements SummonableEntity {
    @Getter private StoredEntity storedEntity;
    @Getter private FultonController controller;

    @Override public String getName() {
        return storedEntity.getCustomName();
    }

    @Override public String getOrigin() {
        return "stasis";
    }

    @Override public boolean Summon(Player player, Location destination){
        val me = destination.getWorld().spawnEntity(destination, storedEntity.getEntityType());
        me.setCustomName(storedEntity.getCustomName());

        // Reach into the storage and remove me from it. This is a reasonable place to do this at the moment, but a bad code smell.
        new EntityStorageView(player, destination.getWorld(), controller.getPlugin()).removeStoredEntity(storedEntity);
        return true;
    }
}