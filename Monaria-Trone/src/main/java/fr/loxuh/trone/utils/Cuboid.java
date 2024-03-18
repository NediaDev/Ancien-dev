package fr.loxuh.trone.utils;

import org.bukkit.configuration.serialization.*;
import org.bukkit.block.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import java.util.*;

public class Cuboid implements ConfigurationSerializable
{
    protected final String worldName;
    protected final int x1;
    protected final int z1;
    protected final int x2;
    protected final int z2;
    protected final int y1;
    protected final int y2;

    public Cuboid(final Location l1, final Location l2) {
        if (!l1.getWorld().equals(l2.getWorld())) {
            throw new IllegalArgumentException("Locations must be on the same world");
        }
        this.worldName = l1.getWorld().getName();
        this.x1 = Math.min(l1.getBlockX(), l2.getBlockX());
        this.z1 = Math.min(l1.getBlockZ(), l2.getBlockZ());
        this.y1 = Math.min(l1.getBlockY(), l2.getBlockY());
        this.x2 = Math.max(l1.getBlockX(), l2.getBlockX());
        this.z2 = Math.max(l1.getBlockZ(), l2.getBlockZ());
        this.y2 = Math.max(l1.getBlockY(), l2.getBlockY());
    }

    public Cuboid(final Location l1) {
        this(l1, l1);
    }

    public Cuboid(final World world, final int x1, final int y1, final int z1, final int x2, final int y2, final int z2) {
        this.worldName = world.getName();
        this.x1 = Math.min(x1, x2);
        this.x2 = Math.max(x1, x2);
        this.z1 = Math.min(z1, z2);
        this.z2 = Math.max(z1, z2);
        this.y1 = Math.min(y1, y2);
        this.y2 = Math.max(y1, y2);
    }

    public Cuboid(final Map<String, Object> map) {
        this.worldName = (String) map.get("worldName");
        this.x1 = (int) map.get("x1");
        this.x2 = (int) map.get("x2");
        this.z1 = (int) map.get("z1");
        this.z2 = (int) map.get("z2");
        this.y1 = (int) map.get("y1");
        this.y2 = (int) map.get("y2");
    }

    public Map<String, Object> serialize() {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("worldName", this.worldName);
        map.put("x1", this.x1);
        map.put("z1", this.z1);
        map.put("x2", this.x2);
        map.put("z2", this.z2);
        map.put("y1", this.y1);
        map.put("y2", this.y2);
        return map;
    }

    public Location getLowerNE() {
        return new Location(this.getWorld(), (double)this.x1, (double)this.y1, (double)this.z1);
    }

    public Location getUpperSW() {
        return new Location(this.getWorld(), (double)this.x2, (double)this.y2, (double)this.z2);
    }

    public World getWorld() {
        final World world = Bukkit.getWorld(this.worldName);
        if (world == null) {
            throw new IllegalStateException("World '" + this.worldName + "' is not loaded");
        }
        return world;
    }

    public int getSizeX() {
        return this.x2 - this.x1 + 1;
    }

    public int getSizeZ() {
        return this.z2 - this.z1 + 1;
    }

    public int getLowerX() {
        return this.x1;
    }

    public int getLowerZ() {
        return this.z1;
    }

    public int getUpperX() {
        return this.x2;
    }

    public int getUpperZ() {
        return this.z2;
    }

    public boolean contains(final int x, final int z, final int y) {
        return x >= this.x1 && x <= this.x2 && z >= this.z1 && z <= this.z2 && y >= this.y1 && y <= this.y2;
    }

    public boolean contains(final Location l) {
        return this.worldName.equals(l.getWorld().getName()) && this.contains(l.getBlockX(), l.getBlockZ(), l.getBlockY());
    }

    public int getSurface() {
        return this.getSizeX() * this.getSizeZ();
    }

    public Block getRelativeBlock(final int x, final int y, final int z) {
        return this.getWorld().getBlockAt(this.x1 + x, y, this.z1 + z);
    }

    public Block getRelativeBlock(final World w, final int x, final int y, final int z) {
        return w.getBlockAt(this.x1 + x, y, this.z1 + z);
    }

    public List<Chunk> getChunks() {
        final List<Chunk> res = new ArrayList<Chunk>();
        final World w = this.getWorld();
        final int x1 = this.getLowerX() & 0xFFFFFFF0;
        final int x2 = this.getUpperX() & 0xFFFFFFF0;
        final int z1 = this.getLowerZ() & 0xFFFFFFF0;
        final int z2 = this.getUpperZ() & 0xFFFFFFF0;
        for (int x3 = x1; x3 <= x2; x3 += 16) {
            for (int z3 = z1; z3 <= z2; z3 += 16) {
                res.add(w.getChunkAt(x3 >> 4, z3 >> 4));
            }
        }
        return res;
    }

    public List<LivingEntity> getLivingEntityInside() {
        final List<LivingEntity> list = new ArrayList<LivingEntity>();
        for (final LivingEntity entity : this.getWorld().getLivingEntities()) {
            if (this.contains(entity.getLocation())) {
                list.add(entity);
            }
        }
        return list;
    }

    public List<Location> getLocations() {
        final List<Location> bL = new ArrayList<Location>();
        final int xMin = Math.min(this.x1, this.x2);
        final int zMin = Math.min(this.z1, this.z2);
        final int xMax = Math.max(this.x1, this.x2);
        final int zMax = Math.max(this.z1, this.z2);
        final int yMax = Math.max(this.y1, this.y2);
        final int yMin = Math.min(this.y1, this.y2);
        for (int x = xMin; x <= xMax; ++x) {
            for (int z = zMin; z <= zMax; ++z) {
                for (int y = yMin; y <= yMax; ++y) {
                    bL.add(new Location(Bukkit.getWorld(this.worldName), (double)x, (double)y, (double)z));
                }
            }
        }
        return bL;
    }

    public class CuboidIterator implements Iterator<Block>
    {
        private World w;
        private int baseX;
        private int baseY;
        private int baseZ;
        private int x;
        private int y;
        private int z;
        private int sizeX;
        private int sizeY;
        private int sizeZ;

        public CuboidIterator(final World w, final int x1, final int y1, final int z1, final int x2, final int y2, final int z2) {
            this.w = w;
            this.baseX = x1;
            this.baseY = y1;
            this.baseZ = z1;
            this.sizeX = Math.abs(x2 - x1) + 1;
            this.sizeY = Math.abs(y2 - y1) + 1;
            this.sizeZ = Math.abs(z2 - z1) + 1;
            final int x3 = 0;
            this.z = x3;
            this.y = x3;
            this.x = x3;
        }

        @Override
        public boolean hasNext() {
            return this.x < this.sizeX && this.y < this.sizeY && this.z < this.sizeZ;
        }

        @Override
        public Block next() {
            final Block b = this.w.getBlockAt(this.baseX + this.x, this.baseY + this.y, this.baseZ + this.z);
            if (++this.x >= this.sizeX) {
                this.x = 0;
                if (++this.y >= this.sizeY) {
                    this.y = 0;
                    ++this.z;
                }
            }
            return b;
        }
    }
}
