package Tiles;

import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import Manager.SpriteManager;

public class Tiles {
    // Type de tuile
    public static final int ROAD = 0;
    public static final int WATER = 1;
    public static final int GRASS = 2;
    public static final int OBSTACLE = 3;

    // Image de la tuile
    private BufferedImage[] spritImages;

    //
    private int type;
    private int id;
    private static int currentId = 0;
    private String name;
    private boolean animated;
    private double rotation = 0.0;

    // Coordonné sur le sprite atlas
    protected Integer x;
    protected Integer y;
    protected Integer x2;
    protected Integer y2;

    public Tiles(int type, String name, Integer x, Integer y) {
        this.type = type;
        this.name = name;
        this.x = x;
        this.y = y;
        this.id = currentId;
        currentId++;
        checkToAnimated();
        getSpriteImg();
    }

    public void checkToAnimated() {
        if (this.type == WATER) {
            this.animated = true;
        } else {
            this.animated = false;
        }
    }

    public void getSpriteImg() {
        SpriteManager<Tiles> spriteManager = new SpriteManager<Tiles>(this);
        spritImages = spriteManager.getSpriteImage();
    }

    public void rotatingImg() {
        if (this.rotation == 270.0) {
            this.rotation = 0;
        } else {
            this.rotation += 90.0;
        }
        getSpriteImg();
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public void rotation(BufferedImage[] img) {
    }

    public final int getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public final int getType() {
        return type;
    }

    public final Integer getX() {
        return x;
    }

    public final Integer getY() {
        return y;
    }

    public Integer getX2() {
        return x2;
    }

    public Integer getY2() {
        return y2;
    }

    public boolean isAnimated() {
        return animated;
    }

    public double getRotation() {
        return rotation;
    }

    public BufferedImage[] getSpritImages() {
        return spritImages;
    }

    public static class ROAD_MIDDLE extends Tiles {

        public ROAD_MIDDLE() {
            super(ROAD, "ROAD_MIDDLE", 1, 6);
        }

    }

    public static class ROAD_SIDE extends Tiles {

        public ROAD_SIDE() {
            super(ROAD, "ROAD_SIDE", 0, 6);
        }

    }

    public static class ROAD_CORNER extends Tiles {

        public ROAD_CORNER() {
            super(ROAD, "ROAD_CORNER", 3, 6);

        }

    }

    public static class ROAD_SMALL extends Tiles {

        public ROAD_SMALL() {
            super(ROAD, "ROAD_SMALL", 2, 6);
        }

    }

    public static class ROAD_SMALL_CORNER extends Tiles {

        public ROAD_SMALL_CORNER() {
            super(ROAD, "ROAD_SMALL", 4, 6);
        }

    }

    public static class OBSTACLE_PANEL extends Tiles {

        public OBSTACLE_PANEL() {
            super(OBSTACLE, "OBSTACLE_PANEL", 0, 5);
        }

    }

    public static class OBSTACLE_CAMP_FIRE extends Tiles {

        public OBSTACLE_CAMP_FIRE() {
            super(OBSTACLE, "OBSTACLE_CAMP_FIRE", 1, 5);
        }

    }

    public static class OBSTACLE_ROCK extends Tiles {

        public OBSTACLE_ROCK() {
            super(OBSTACLE, "OBSTACLE_ROCK", 2, 5);
        }

    }

    public static class WATER extends Tiles {

        public WATER() {
            super(WATER, "WATER", 2, 0);
        }

    }

    public static class WATER_SIDE extends Tiles {

        public WATER_SIDE() {
            super(WATER, "WATER_SIDE", 2, 0);
            this.x2 = 4;
            this.y2 = 1;
        }

    }

    public static class WATER_CORNER extends Tiles {

        public WATER_CORNER() {
            super(WATER, "WATER_CORNER", 2, 0);
            this.x2 = 4;
            this.y2 = 2;
        }

    }

    public static class GRASS extends Tiles {

        public GRASS() {
            super(GRASS, "GRASS", 0, 4);
        }

    }

    public <T extends Tiles> ArrayList<BufferedImage[]> createTile(Class<T> tileClass) {
        T tile = null;
        ArrayList<BufferedImage[]> tileImg = new ArrayList<>();

        try {
            tile = tileClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        if (tile == null) {
            System.out.println("New object creation failed...");
            return null;
        }
        if (tile instanceof WATER || tile instanceof GRASS) {
            tileImg.add(tile.getSpritImages());
        } else {

            for (int x = 0; x < 4; x++) {
                tileImg.add(tile.getSpritImages());
                tile.rotatingImg();
                tile.getSpriteImg();
            }

        }
        return tileImg;

    }

    public ArrayList<BufferedImage[]> tilesList() {
        ArrayList<BufferedImage[]> tilesList = new ArrayList<>();
        tilesList.addAll(createTile(ROAD_MIDDLE.class));
        tilesList.addAll(createTile(ROAD_SIDE.class));
        tilesList.addAll(createTile(ROAD_CORNER.class));
        tilesList.addAll(createTile(ROAD_SMALL.class));
        tilesList.addAll(createTile(ROAD_SMALL_CORNER.class));
        tilesList.addAll(createTile(OBSTACLE_PANEL.class));
        tilesList.addAll(createTile(OBSTACLE_CAMP_FIRE.class));
        tilesList.addAll(createTile(OBSTACLE_ROCK.class));
        tilesList.addAll(createTile(WATER.class));
        tilesList.addAll(createTile(WATER_SIDE.class));
        tilesList.addAll(createTile(WATER_CORNER.class));
        tilesList.addAll(createTile(GRASS.class));
        return tilesList;
    }

}
