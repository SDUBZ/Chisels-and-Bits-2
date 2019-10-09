package nl.dgoossens.chiselsandbits2.api;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import nl.dgoossens.chiselsandbits2.ChiselsAndBits2;
import nl.dgoossens.chiselsandbits2.common.chiseledblock.voxel.VoxelBlob;
import nl.dgoossens.chiselsandbits2.common.utils.ModUtil;

import java.awt.*;

public class SelectedItemMode implements IItemMode {
    public static final SelectedItemMode NONE_BAG = new SelectedItemMode(null, false);
    public static final SelectedItemMode NONE_BEAKER = new SelectedItemMode(null, true);
    public static final SelectedItemMode NONE_BOOKMARK = new SelectedItemMode(0);

    private ResourceLocation key;
    private boolean fluid;
    private boolean isColor = false;
    private int color;

    private SelectedItemMode(final ResourceLocation key, final boolean fluid) {
        this.key = key;
        this.fluid = fluid;
    }

    private SelectedItemMode(final int color) {
        this.color = color;
        isColor = true;
    }

    public static SelectedItemMode fromName(final String key, final boolean fluid) {
        if (key.equalsIgnoreCase("null")) return fluid ? NONE_BEAKER : NONE_BAG;
        return new SelectedItemMode(ResourceLocation.create(key, ':'), fluid);
    }

    public static SelectedItemMode fromBlock(final Block blk) {
        return new SelectedItemMode(blk.getRegistryName(), false);
    }

    public static SelectedItemMode fromFluid(final Fluid fluid) {
        return new SelectedItemMode(fluid.getRegistryName(), true);
    }

    public static SelectedItemMode fromColour(final Color color) {
        return new SelectedItemMode(color.hashCode());
    }

    public Block getBlock() {
        return fluid ? Blocks.AIR : ForgeRegistries.BLOCKS.getValue(key);
    }

    public Fluid getFluid() {
        return !fluid ? Fluids.EMPTY : ForgeRegistries.FLUIDS.getValue(key);
    }

    public ItemStack getStack() {
        return !fluid ? new ItemStack(ForgeRegistries.BLOCKS.getValue(key)) : new ItemStack(ForgeRegistries.FLUIDS.getValue(key).getFilledBucket());
    }

    public Color getColour() {
        return new Color(color, true);
    }

    public String getLocalizedName() {
        Color c = getColour();
        return isColor ? "("+c.getRed()+","+c.getGreen()+","+c.getBlue()+")" : key == null ? I18n.format("general." + ChiselsAndBits2.MOD_ID + ".empty_slot") : I18n.format("block" + "." + key.getNamespace() + "." + key.getPath());
    }

    public String getTypelessName() {
        return getName();
    }

    public String getName() {
        return isColor ? String.valueOf(color) : key == null ? "null" : key.toString();
    }

    public ItemModeType getType() {
        return isColor ? ItemModeType.SELECTED_BOOKMARK : fluid ? ItemModeType.SELECTED_FLUID : ItemModeType.SELECTED_BLOCK;
    }

    public int getBitId() {
        //If this is NULL_BAG, NULL_BEAKER or NULL_BOOKMARK
        if(color == 0 && key == null)
            return VoxelBlob.AIR_BIT;

        if(isColor)
            return ModUtil.getColourId(getColour());
        if(fluid)
            return ModUtil.getFluidId(getFluid().getDefaultState());

        return ModUtil.getStateId(getBlock().getDefaultState());
    }

    @Override
    public String toString() {
        return getLocalizedName();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SelectedItemMode) {
            SelectedItemMode s = (SelectedItemMode) obj;
            if(s.getType() != getType()) return false;
            switch(getType()) {
                case SELECTED_BLOCK:
                case SELECTED_FLUID:
                    if(key == null) return s.key == null;
                    return key.equals(s.key);
                case SELECTED_BOOKMARK:
                    return color == s.color;
            }
        }
        return super.equals(obj);
    }

    public static boolean isNone(IItemMode i) {
        return i != null && (i.equals(NONE_BAG) || i.equals(NONE_BOOKMARK) || i.equals(NONE_BEAKER));
    }
}
