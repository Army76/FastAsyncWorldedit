package com.boydti.fawe.object.pattern;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.Vector2D;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.entity.BaseEntity;
import com.sk89q.worldedit.entity.Entity;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.pattern.AbstractPattern;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldedit.world.biome.BaseBiome;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class PatternExtent extends AbstractPattern implements Extent {
    private final Pattern pattern;
    private BaseBlock block;

    public PatternExtent(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public Vector getMinimumPoint() {
        return new Vector(Integer.MIN_VALUE,0,Integer.MIN_VALUE);
    }

    @Override
    public Vector getMaximumPoint() {
        return new Vector(Integer.MAX_VALUE,255,Integer.MAX_VALUE);
    }

    @Override
    public List<? extends Entity> getEntities(Region region) {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Entity> getEntities() {
        return new ArrayList<>();
    }

    @Nullable
    @Override
    public Entity createEntity(Location location, BaseEntity entity) {
        return null;
    }

    @Override
    public BaseBlock getBlock(Vector position) {
        return block = pattern.apply(position);
    }

    public BaseBlock getAndResetBlock() {
        BaseBlock result = block;
        block = null;
        return result;
    }

    @Override
    public BaseBlock getLazyBlock(Vector position) {
        return getBlock(position);
    }

    @Override
    public BaseBiome getBiome(Vector2D position) {
        return new BaseBiome(0);
    }

    @Override
    public boolean setBlock(Vector position, BaseBlock block) throws WorldEditException {
        return false;
    }

    @Override
    public boolean setBiome(Vector2D position, BaseBiome biome) {
        return false;
    }

    @Nullable
    @Override
    public Operation commit() {
        return null;
    }

    @Override
    public BaseBlock apply(Vector position) {
        return pattern.apply(position);
    }
}