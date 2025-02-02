/*
 *     Copyright © 2016 cpw
 *     This file is part of Inventorysorter.
 *
 *     Inventorysorter is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Inventorysorter is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Inventorysorter.  If not, see <http://www.gnu.org/licenses/>.
 */

package cpw.mods.inventorysorter;

import net.minecraft.world.inventory.Slot;
import org.lwjgl.glfw.GLFW;

import java.util.function.*;

/**
 * Created by cpw on 08/01/16.
 */
public enum Action
{
    SORT(SortingHandler.INSTANCE, "key.inventorysorter.sort", GLFW.GLFW_MOUSE_BUTTON_MIDDLE);

    private final Consumer<ContainerContext> worker;
    private final String keyBindingName;
    private final int defaultKeyCode;

    Action(Consumer<ContainerContext> worker, String keyBindingName, int defaultKeyCode)
    {
        this.worker = worker;
        this.keyBindingName = keyBindingName;
        this.defaultKeyCode = defaultKeyCode;
    }

    public String getKeyBindingName() {
        return keyBindingName;
    }

    public Network.ActionMessage message(Slot slot)
    {
        return new Network.ActionMessage(this, slot.index);
    }

    public void execute(ContainerContext context)
    {
        this.worker.accept(context);
    }

    public boolean isActive()
    {
        return true;
    }

    public int getDefaultKeyCode() {
        return defaultKeyCode;
    }

}
